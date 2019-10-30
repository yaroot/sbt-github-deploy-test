// format: off
name := "sbt-github-deploy-test"
organization := "com.github.yaroot"
scalaVersion := "2.12.10"
crossScalaVersions := Seq("2.12.10", "2.11.12", "2.13.1")
version := "0.0.3"

scalacOptions := Seq(
  "-deprecation",                     // Emit warning and location for usages of deprecated APIs.
  "-encoding", "utf-8",               // Specify character encoding used by source files.
  "-explaintypes",                    // Explain type errors in more detail.
  "-feature"                         // Emit warning and location for usages of features that should be imported explicitly.
)


libraryDependencies ++= {
  Seq(
    "org.typelevel" %% "cats-core" % "2.0.0",
    "org.specs2"    %% "specs2-core" % "4.7.1" % "test"
  )
}

scalafmtOnCompile := true
cancelable in Global := true

