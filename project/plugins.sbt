addSbtPlugin("org.scala-js"   % "sbt-scalajs"    % "1.16.0")
addSbtPlugin("com.github.sbt" % "sbt-ci-release" % "1.11.0")

addSbtPlugin("com.armanbilge" % "sbt-bundlemon" % "0.1.3")

libraryDependencies ++= List(
  "com.lihaoyi" %% "os-lib" % "0.9.0"
)
