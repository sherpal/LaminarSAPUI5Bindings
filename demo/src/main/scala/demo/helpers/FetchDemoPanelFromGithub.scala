package demo.helpers

import scala.concurrent.Future
import scala.concurrent.ExecutionContext
import org.scalajs.dom.fetch
import org.scalajs.dom
import java.lang.module.ModuleDescriptor.Opens

object FetchDemoPanelFromGithub {

  case class CompleteDemoPanelInfo(
      maybeCommonContents: Option[String],
      demoPanelInfo: Map[String, DemoPanelInfo]
  )

  case class DemoPanelInfo(title: String, contents: String) {
    def stripIndent: String = {
      val lines     = contents.split("\n")
      val minIndent = lines.filter(_.trim.nonEmpty).map(_.takeWhile(_.isSpaceChar).length).minOption.getOrElse(0)
      lines.map(_.drop(minIndent)).mkString("\n")
    }
  }

  trait Parser[T] { self =>
    def parse(str: String): Option[(T, Int, Int)]

    final def map[U](f: T => U): Parser[U] = new Parser[U] {
      def parse(str: String): Option[(U, Int, Int)] =
        self.parse(str).map((t, beginIndex, endIndex) => (f(t), beginIndex, endIndex))
    }

    final def flatMap[U](f: T => Parser[U]): Parser[U] = new Parser[U] {
      def parse(str: String): Option[(U, Int, Int)] = for {
        tResult <- self.parse(str)
        (t, beginIndex, endIndex) = tResult
        remainingStr              = str.drop(endIndex)
        uParser                   = f(t)
        uResult <- uParser.parse(remainingStr)
        (u, _, endUIndex) = uResult
      } yield (u, beginIndex, endUIndex)
    }

    final def orElse(default: T): Parser[T] = new Parser[T] {
      def parse(str: String): Option[(T, Int, Int)] = Some(self.parse(str).getOrElse((default, 0, str.length)))
    }

    final def extract(str: String): Option[T] = parse(str).map(_._1)
  }

  /** [[Parser]] succeeding iff `value` is contained as is in the string. */
  def exactMatch(value: String): Parser[Unit] = new Parser[Unit] {
    def parse(str: String): Option[(Unit, Int, Int)] = str.indexOf(value) match {
      case index if index >= 0 => Some(((), index, index + value.length))
      case _                   => None
    }
  }

  val findCommonExample: Parser[String] = new Parser[String] {
    val beginCommonString = "//-- Begin Common"
    val endCommonString   = "//-- End Common"

    def parse(str: String): Option[(String, Int, Int)] = {
      val lines = str.split("\n")
      for {
        beginLineIndex <- Some(lines.indexWhere(_.trim.startsWith(beginCommonString))).filter(_ >= 0)
        numberOfLines  <- Some(lines.drop(beginLineIndex).indexWhere(_.trim.startsWith(endCommonString))).filter(_ >= 0)
        startIndexInStr = lines.take(beginLineIndex).map(_.length + 1).sum
        endIndexInStr   = lines.take(beginLineIndex + 1 + numberOfLines).map(_.length + 1).sum
      } yield (lines.drop(beginLineIndex).take(numberOfLines).mkString("\n"), startIndexInStr, endIndexInStr)
    }
  }

  val findNextExample: Parser[DemoPanelInfo] = new Parser[DemoPanelInfo] {
    val beginExampleString = "//-- Begin:"
    val endExampleString   = "//-- End"

    def parse(str: String): Option[(DemoPanelInfo, Int, Int)] = {
      val lines = str.split("\n").toVector
      Some(lines.indexWhere(_.trim.startsWith(beginExampleString))).filter(_ >= 0).map { lineIndex =>
        val exampleName     = lines(lineIndex).trim.drop(beginExampleString.length).trim
        val exampleLines    = lines.drop(lineIndex + 1).takeWhile(!_.trim.startsWith(endExampleString))
        val startIndexInStr = lines.take(lineIndex + 1).map(_.length + 1).sum
        val endIndexInStr =
          lines.drop(lineIndex + 1).take(exampleLines.length + 1).map(_.length + 1).sum + startIndexInStr
        (DemoPanelInfo(exampleName, exampleLines.mkString("\n")), startIndexInStr, endIndexInStr)
      }
    }
  }

  def findAll[A](parser: Parser[A]): Parser[List[A]] = new Parser[List[A]] {
    def parse(str: String): Option[(List[A], Int, Int)] =
      parser.parse(str) match {
        case Some((info, beginIndex, endIndex)) =>
          findAll(parser)
            .parse(str.drop(endIndex))
            .map((otherExamples, _, finalIndex) => (info +: otherExamples, beginIndex, endIndex))
        case None => Some((Nil, 0, 0))
      }
  }

  val findAllExamples       = findAll(findNextExample).orElse(Nil)
  val findAllCommonExamples = findAll(findCommonExample).map(_.mkString("\n\n")).map(Some(_)).orElse(None)

  val completeDemoPanelInfoParser = for {
    maybeCommonExamples <- findAllCommonExamples
    demoPanelInfo       <- findAllExamples
  } yield CompleteDemoPanelInfo(maybeCommonExamples, demoPanelInfo.map(info => info.title -> info).toMap)

  private def parseFileContents(content: String): CompleteDemoPanelInfo =
    completeDemoPanelInfoParser.extract(content).getOrElse(CompleteDemoPanelInfo(None, Map.empty))

  def fetchAllDemoPanelInfo(exampleName: String)(using ExecutionContext): Future[CompleteDemoPanelInfo] = (for {
    response <- fetch(
      s"https://raw.githubusercontent.com/sherpal/LaminarSAPUI5Bindings/master/demo/src/main/scala/demo/${exampleName}Example.scala"
    ).toFuture
    content <- response.text().toFuture
    _ <-
      if response.status >= 400 then
        Future.failed(new RuntimeException(s"Non successful http status (${response.status}). Body was: $content."))
      else Future.successful(())
  } yield parseFileContents(content)).recover { case throwable: Throwable =>
    dom.console.error(s"Error while fetching demo panel info: ${throwable.getMessage}")
    throwable.printStackTrace()
    CompleteDemoPanelInfo(None, Map.empty)
  }

}
