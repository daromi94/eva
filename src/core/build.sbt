ThisBuild / version := "0.1.0"

ThisBuild / scalaVersion := "3.5.2"

lazy val root = (project in file("."))
  .settings(
    name := "eva"
  )

libraryDependencies += "org.scalatest" %% "scalatest-flatspec" % "3.2.19" % "test"
