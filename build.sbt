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

val laminarVersion = "0.14.2"

lazy val `web-components` = project
  .in(file("./web-components"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    scalacOptions ++= usedScalacOptions,
    scalaJSLinkerConfig ~= (_.withModuleKind(ModuleKind.CommonJSModule)),
    libraryDependencies ++= List("com.raquo" %%% "laminar" % laminarVersion % Provided)
  )

lazy val demo = project
  .in(file("./demo"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    scalacOptions ++= usedScalacOptions,
    libraryDependencies ++= List("com.raquo" %%% "laminar" % laminarVersion),
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) },
    scalaJSUseMainModuleInitializer := true
  )
  .dependsOn(`web-components`)

val createReleaseTag = taskKey[java.io.File]("Writes the current release tag in tag.txt file")

createReleaseTag := {
  val file = new java.io.File("tag.txt")

  val currentVersion = (`web-components` / version).value
  val gitHash        = git.gitHeadCommit.value.toRight(new IllegalStateException("Not a git repo!")).toTry.get.take(8)
  IO.write(file, s"RELEASE_TAG=$currentVersion-$gitHash")

  file
}
