object IllustratedMessages {

  private val nonIllustratedMessages = List(
    "AllIllustrations"
  )


  /** Map from the name of the enclosing sub-folder to the list of illustrated messages in that folder. None when it's
    * at the "root".
    */
  private def allIllustratedMessages: Map[Option[String], List[String]] = {
    val enclosingFolder = os.pwd / "demo" / "node_modules" / "@ui5" / "webcomponents-fiori" / "dist" / "illustrations"
    List(None, Some("tnt")).map { subFolder =>
      val folder = subFolder.fold(enclosingFolder)(enclosingFolder / _)
      subFolder -> os
        .list(folder)
        .toList
        .map(path => path.getSegment(path.segmentCount - 1))
        .filter(_.endsWith(".js"))
        .map(_.dropRight(3))
        .filterNot(nonIllustratedMessages.contains[String])
        .sorted
    }.toMap
  }

  private def allIllustratedMessagesImports(
      illustratedMessages: Map[Option[String], List[String]]
  ): Map[Option[String], List[String]] =
    illustratedMessages.map { case (subFolder, names) =>
      val baseFolder = "@ui5/webcomponents-fiori/dist/illustrations"
      subFolder -> names.map(name =>
        CodeGeneration.dummyJSImport(
          subFolder.fold(baseFolder)(baseFolder ++ "/" ++ _),
          name,
          indent = 1 + subFolder.toList.length
        )
      )
    }

  private def allIllustratedMessagesValues(
      illustratedMessages: Map[Option[String], List[String]]
  ): Map[Option[String], List[String]] = illustratedMessages
    .map { case (subFolder, names) =>
      val subFolderObjectPath = subFolder.fold("")("IllustratedMessageTypeImports." ++ _ ++ ".")
      subFolder -> names.map { name =>
        s"""def ${CodeGeneration.wrappedName(
          name
        )}: IllustratedMessageType = _illustratedMessage($subFolderObjectPath${CodeGeneration.importName(
          name
        )}, "$name")"""
      }
    }

  private def generateIllustratedMessageImportsFileContent(
      illustratedMessages: Map[Option[String], List[String]],
      fullPackageName: String
  ) = {
    val packageName = fullPackageName.split('.').last
    val importsString = allIllustratedMessagesImports(illustratedMessages)
      .map {
        case (None, imports) =>
          imports.mkString("\n")
        case (Some(subFolder), imports) =>
          imports.mkString(s"  object $subFolder {\n", "\n", "\n  }")
      }
      .mkString("\n")
    s"""
       |package $fullPackageName
       |
       |// !! This file has been generated, do not edit manually!
       |
       |import scala.scalajs.js
       |import scala.scalajs.js.annotation.JSImport
       |
       |private[$packageName] object IllustratedMessageTypeImports {
       |  @inline def _illustratedMessage(obj: js.Object, name: String): IllustratedMessageType =
       |    name.asInstanceOf[IllustratedMessageType]
       |
       |$importsString
       |}
       |""".stripMargin
  }

  private def generateIllustratedMessageValuesFileContent(
      illustratedMessages: Map[Option[String], List[String]],
      fullPackageName: String
  ) = {
    val valuesString = allIllustratedMessagesValues(illustratedMessages).map {
      case (None, values)            => values.map("  " ++ _).mkString("\n")
      case (Some(subFolder), values) => values.map(("  " * 2) ++ _).mkString(s"  object $subFolder {\n", "\n", "\n  }")
    } mkString "\n"
    s"""
       |package $fullPackageName
       |
       |// !! This file has been generated, do not edit manually!
       |
       |import com.raquo.domtypes.generic.codecs.{Codec, StringAsIsCodec}
       |import $fullPackageName.IllustratedMessageTypeImports.*
       |
       |import scala.scalajs.js
       |
       |@js.native
       |sealed trait IllustratedMessageType extends js.Any
       |
       |//noinspection NoTargetNameAnnotationForOperatorLikeDefinition
       |object IllustratedMessageType {
       |$valuesString
       |
       |  def AsStringCodec: Codec[IllustratedMessageType, String] = new Codec[IllustratedMessageType, String] {
       |    override def encode(scalaValue: IllustratedMessageType): String = scalaValue.asInstanceOf[String]
       |    override def decode(domValue: String): IllustratedMessageType = domValue.asInstanceOf[IllustratedMessageType]
       |  }
       |}
       |""".stripMargin
  }

  private val illustratedMessageImportsFilename = "IllustratedMessageTypeImports.scala"
  private val illustratedMessageTypeFilename    = "IllustratedMessageType.scala"

  def generateIllustratedMessagesFiles(fullPackageName: String, containerFolder: os.Path): List[os.Path] = {
    val illustratedMessages = allIllustratedMessages

    val iconImportsFile = containerFolder / illustratedMessageImportsFilename
    CodeGeneration.overwrite(
      iconImportsFile,
      generateIllustratedMessageImportsFileContent(illustratedMessages, fullPackageName)
    )
    val iconNameFile = containerFolder / illustratedMessageTypeFilename
    CodeGeneration.overwrite(
      iconNameFile,
      generateIllustratedMessageValuesFileContent(illustratedMessages, fullPackageName)
    )

    List(iconImportsFile, iconNameFile)
  }

  def checkIllustratedMessagesFilesUpToDate(fullPackageName: String, containerFolder: os.Path): Either[String, Unit] = {
    val illustratedMessages = allIllustratedMessages

    lazy val iconImportsContentOk = {
      val iconImportsContent = generateIllustratedMessageImportsFileContent(illustratedMessages, fullPackageName)
      val iconImportsFile    = containerFolder / illustratedMessageImportsFilename
      os.exists(iconImportsFile) && os.read(iconImportsFile) == iconImportsContent
    }

    lazy val iconNameContentOk = {
      val iconNameContent = generateIllustratedMessageValuesFileContent(illustratedMessages, fullPackageName)
      val iconNameFile    = containerFolder / illustratedMessageTypeFilename
      os.exists(iconNameFile) && os.read(iconNameFile) == iconNameContent
    }

    if (!iconImportsContentOk) Left("The content of the illustrated messages imports file does not match.")
    else if (!iconNameContentOk) Left("The content of the illustrated messages name file does not match.")
    else Right(())
  }

}
