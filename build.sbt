ThisBuild / version := "1.3.0"

ThisBuild / scalaVersion := "3.1.2"

val usedScalacOptions = Seq(
  "-encoding",
  "utf8",
  "-Xfatal-warnings",
  "-deprecation",
  "-unchecked",
  "-language:higherKinds"
)

lazy val `web-components` = project
  .in(file("./web-components"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    scalacOptions ++= usedScalacOptions,
    scalaJSLinkerConfig ~= (_.withModuleKind(ModuleKind.CommonJSModule)),
    libraryDependencies ++= List(
      "com.raquo" %%% "laminar" % "0.14.2" % Provided
    )
  )

val createReleaseTag = taskKey[java.io.File]("Writes the current release tag in tag.txt file")

createReleaseTag := {
  val file = new java.io.File("tag.txt")

  val currentVersion = (`web-components` / version).value
  val gitHash        = git.gitHeadCommit.value.toRight(new IllegalStateException("Not a git repo!")).toTry.get.take(8)
  IO.write(file, s"RELEASE_TAG=$currentVersion-$gitHash")

  file
}
