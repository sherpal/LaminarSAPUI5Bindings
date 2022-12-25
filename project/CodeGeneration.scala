import sbt.Def

object CodeGeneration {

  def overwrite(target: os.Path, data: os.Source): Unit = {
    if (os.exists(target))
      os.remove(target)
    os.write(target, data, createFolders = true)
  }

  private val nonIcons = List(
    "AllIcons", "AllIcons-static", "Assets", "Assets-static"
  )

  private def allIconNames = os
    .list(os.pwd / "demo" / "node_modules" / "@ui5" / "webcomponents-icons" / "dist")
    .toList
    .map(path => path.getSegment(path.segmentCount - 1))
    .filter(_.endsWith(".js"))
    .map(_.dropRight(3))
    .filterNot(nonIcons.contains[String])
    .sorted

  private def importName(iconName: String) = s"`import-$iconName`"

  private def allIconImports(iconNames: List[String]) = iconNames.map { name =>
    s"""  @js.native
       |  @JSImport("@ui5/webcomponents-icons/dist/$name.js", JSImport.Default)
       |  object ${importName(name)} extends js.Object
       |""".stripMargin
  }

  private def allIconValues(iconNames: List[String]) = iconNames.map { name =>
    val scalaKeywords = List("private", "class", "for") // add more if needed
    val wrappedName = if (name.contains("-") || scalaKeywords.contains(name)) s"`$name`" else name
    s"""def $wrappedName: IconName = _iconName(${importName(name)}, "$name")"""
  }

  private def generateIconImportsFileContent(iconNames: List[String], fullPackageName: String) = {
    val packageName = fullPackageName.split('.').last
    s"""
       |package $fullPackageName
       |
       |// !! This file has been generated, do not edit manually!
       |
       |import scala.scalajs.js
       |import scala.scalajs.js.annotation.JSImport
       |
       |private[$packageName] object IconImports {
       |  @inline def _iconName(obj: js.Object, name: String): IconName =
       |    name.asInstanceOf[IconName]
       |
       |${allIconImports(iconNames).mkString("\n")}
       |}
       |""".stripMargin
  }

  private def generateIconValuesFileContent(iconNames: List[String], fullPackageName: String) =
    s"""
       |package $fullPackageName
       |
       |// !! This file has been generated, do not edit manually!
       |
       |import com.raquo.domtypes.generic.codecs.{Codec, StringAsIsCodec}
       |import $fullPackageName.IconImports._
       |
       |import scala.scalajs.js
       |
       |@js.native
       |sealed trait IconName extends js.Any
       |
       |//noinspection NoTargetNameAnnotationForOperatorLikeDefinition
       |object IconName {
       |${allIconValues(iconNames).map("  " ++ _).mkString("\n")}
       |
       |  def AsStringCodec: Codec[IconName, String] = new Codec[IconName, String] {
       |    override def encode(scalaValue: IconName): String = scalaValue.asInstanceOf[String]
       |    override def decode(domValue: String): IconName = domValue.asInstanceOf[IconName]
       |  }
       |}
       |""".stripMargin
       
  private val iconImportsFilename = "IconImports.scala"
  private val iconNameFilename = "IconName.scala"

  def generateIconsFiles(fullPackageName: String, containerFolder: os.Path): List[os.Path] = {
    val iconNames = allIconNames

    val iconImportsFile = containerFolder / iconImportsFilename
    overwrite(iconImportsFile, generateIconImportsFileContent(iconNames, fullPackageName))
    val iconNameFile = containerFolder / iconNameFilename
    overwrite(iconNameFile, generateIconValuesFileContent(iconNames, fullPackageName))
    
    List(iconImportsFile, iconNameFile)
  }
  
  def checkIconsFilesUpToDate(fullPackageName: String, containerFolder: os.Path): Either[String, Unit] = {
    val iconNames = allIconNames
    
    lazy val iconImportsContentOk = {
      val iconImportsContent = generateIconImportsFileContent(iconNames, fullPackageName)
      val iconImportsFile = containerFolder / iconImportsFilename
      os.exists(iconImportsFile) && os.read(iconImportsFile) == iconImportsContent
    }
    
    lazy val iconNameContentOk = {
      val iconNameContent = generateIconValuesFileContent(iconNames, fullPackageName)
      val iconNameFile = containerFolder / iconNameFilename
      os.exists(iconNameFile) && os.read(iconNameFile) == iconNameContent
    }
    
    if (!iconImportsContentOk) Left("The content of the icon imports file does not match.")
    else if (!iconNameContentOk) Left("The content of the icon name file does not match.")
    else Right(())
  }

}
