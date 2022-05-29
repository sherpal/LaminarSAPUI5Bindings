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
