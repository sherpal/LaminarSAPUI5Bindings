object Icons { // I know this (and IllustratedMessages) could be abstracted away. I'll do it when/if there is a third use case.

  private val nonIcons = List(
    "AllIcons",
    "AllIcons-static",
    "Assets",
    "Assets-static"
  )

  private def allIconNames = os
    .list(os.pwd / "demo" / "node_modules" / "@ui5" / "webcomponents-icons" / "dist")
    .toList
    .map(path => path.getSegment(path.segmentCount - 1))
    .filter(_.endsWith(".js"))
    .map(_.dropRight(3))
    .filterNot(nonIcons.contains[String])
    .sorted

  private def allIconImports(iconNames: List[String]) =
    iconNames.map(name => CodeGeneration.dummyJSImport("@ui5/webcomponents-icons/dist", name, 1))

  private def allIconValues(iconNames: List[String]) = iconNames.map(name =>
    s"""def ${CodeGeneration.wrappedName(name)}: IconName = _iconName(${CodeGeneration.importName(name)}, "$name")"""
  )

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
       |//noinspection NoTargetNameAnnotationForOperatorLikeDefinition
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
       |import com.raquo.laminar.codecs.{Codec, StringAsIsCodec}
       |import $fullPackageName.IconImports.*
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
  private val iconNameFilename    = "IconName.scala"

  def generateIconsFiles(fullPackageName: String, containerFolder: os.Path): List[os.Path] = {
    val iconNames = allIconNames

    val iconImportsFile = containerFolder / iconImportsFilename
    CodeGeneration.overwrite(iconImportsFile, generateIconImportsFileContent(iconNames, fullPackageName))
    val iconNameFile = containerFolder / iconNameFilename
    CodeGeneration.overwrite(iconNameFile, generateIconValuesFileContent(iconNames, fullPackageName))

    List(iconImportsFile, iconNameFile)
  }

  def checkIconsFilesUpToDate(fullPackageName: String, containerFolder: os.Path): Either[String, Unit] = {
    val iconNames = allIconNames

    lazy val iconImportsContentOk = {
      val iconImportsContent = generateIconImportsFileContent(iconNames, fullPackageName)
      val iconImportsFile    = containerFolder / iconImportsFilename
      os.exists(iconImportsFile) && os.read(iconImportsFile) == iconImportsContent
    }

    lazy val iconNameContentOk = {
      val iconNameContent = generateIconValuesFileContent(iconNames, fullPackageName)
      val iconNameFile    = containerFolder / iconNameFilename
      os.exists(iconNameFile) && os.read(iconNameFile) == iconNameContent
    }

    if (!iconImportsContentOk) Left("The content of the icon imports file does not match.")
    else if (!iconNameContentOk) Left("The content of the icon name file does not match.")
    else Right(())
  }

}
