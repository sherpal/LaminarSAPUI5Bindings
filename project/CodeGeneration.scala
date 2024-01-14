object CodeGeneration {

  def overwrite(target: os.Path, data: os.Source): Unit = {
    if (os.exists(target))
      os.remove(target)
    os.write(target, data, createFolders = true)
  }

  def importName(name: String) = s"`import-$name`"

  def dummyJSImport(folder: String, name: String, indent: Int): String = {
    val tab = "  " * indent
    s"""$tab@js.native @JSImport("$folder/$name.js", JSImport.Namespace)
       |${tab}object ${importName(name)} extends js.Object
       |""".stripMargin
  }

  private val scalaKeywords = List("private", "class", "for") // add more if needed

  def wrappedName(name: String): String = if (name.contains("-") || scalaKeywords.contains(name)) s"`$name`" else name

  def generateIconsFiles(fullPackageName: String, containerFolder: os.Path): List[os.Path] =
    Icons.generateIconsFiles(fullPackageName, containerFolder)

  def generateIllustratedMessagesFiles(fullPackageName: String, containerFolder: os.Path): List[os.Path] =
    IllustratedMessages.generateIllustratedMessagesFiles(fullPackageName, containerFolder)

  def checkFilesAreUpToDate(fullPackageName: String, containerFolder: os.Path): Either[String, Unit] =
    for {
      _ <- Icons.checkIconsFilesUpToDate(fullPackageName, containerFolder)
      _ <- IllustratedMessages.checkIllustratedMessagesFilesUpToDate(fullPackageName, containerFolder)
    } yield ()

}
