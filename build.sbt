// format: off
name := "sbt-github-deploy-test"
organization := "com.github.yaroot"
scalaVersion := "2.13.3"
crossScalaVersions := Seq("2.12.12", "2.13.3")
version := "0.0.5-SNAPSHOTS"

scalacOptions := Seq(
  "-deprecation",                     // Emit warning and location for usages of deprecated APIs.
  "-encoding", "utf-8",               // Specify character encoding used by source files.
  "-explaintypes",                    // Explain type errors in more detail.
  "-feature"                         // Emit warning and location for usages of features that should be imported explicitly.
)


libraryDependencies ++= {
  Seq(
    "org.typelevel" %% "cats-core" % "2.1.1",
    "org.specs2"    %% "specs2-core" % "4.10.3" % "test"
  )
}

scalafmtOnCompile := true
cancelable in Global := true

publishTo := Some("Github Package Registry" at "https://maven.pkg.github.com/yaroot/sbt-github-deploy-test")
credentials += Credentials("GitHub Package Registry", "maven.pkg.github.com", "yaroot", scala.sys.env.getOrElse("GITHUB_TOKEN", ""))
