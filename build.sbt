import java.nio.charset.StandardCharsets
ThisBuild / scalaVersion := "3.2.0"

val usedScalacOptions = Seq(
  "-encoding",
  "utf8",
  "-Xfatal-warnings",
  "-deprecation",
  "-unchecked",
  "-language:higherKinds",
  "-feature",
  "-language:implicitConversions"
)

val laminarVersion = "0.14.5"

inThisBuild(List(
  name := "web-components-ui5",
  organization := "be.doeraene",
  description := "Laminar bindings for the web-component library UI5 from SAP",
  homepage := Some(url("https://github.com/sherpal/LaminarSAPUI5Bindings")),
  licenses := List("MIT" -> url("http://www.opensource.org/licenses/mit-license.php")),
  developers := List(
    Developer(
      "sherpal",
      "Antoine Doeraene",
      "antoine.doeraene@gmail.com",
      url("https://github.com/sherpal")
    )
  ),
))


lazy val `web-components-ui5` = project
  .in(file("./web-components"))
  .enablePlugins(ScalaJSPlugin)
  .settings(name := "web-components-ui5")
  .settings(
    scalacOptions ++= usedScalacOptions,
    scalaJSLinkerConfig ~= (_.withModuleKind(ModuleKind.CommonJSModule)),
    libraryDependencies ++= List("com.raquo" %%% "laminar" % laminarVersion % Provided),
    Compile / doc := new java.io.File("no-doc")
  )

// We need these dummy root for the publishing
lazy val root = project.in(file("."))
  .aggregate(`web-components-ui5`).settings(
    publish / skip := true,
  )

lazy val demo = project
  .in(file("./demo"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    scalacOptions ++= usedScalacOptions,
    libraryDependencies ++= List(
      "com.raquo" %%% "laminar" % laminarVersion
    ),
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) },
    scalaJSUseMainModuleInitializer := true,
    publish / skip := true
  )
  .dependsOn(`web-components-ui5`)

Global / onLoad := {
  val scalaVersionValue = (demo / scalaVersion).value
  val outputFile = baseDirectory.value / "demo" / "scala-metadata.js"
  IO.writeLines(outputFile, s"""
  |const scalaVersion = "$scalaVersionValue"
  |
  |exports.scalaMetadata = {
  |  scalaVersion: scalaVersion
  |}
  |""".stripMargin.split("\n").toList, StandardCharsets.UTF_8)
  (Global / onLoad).value
}
