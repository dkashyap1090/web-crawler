import sbt.Keys.libraryDependencies
//import scoverage.ScoverageKeys

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.12"

val akkaVersion = "2.6.13"
val akkaHttpVersion = "10.2.4"
val jsoupVersion = "1.14.2"

lazy val root = (project in file("."))
  .settings(
    name := "web_crawler",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-stream" % akkaVersion,
      "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
      "org.jsoup" % "jsoup" % jsoupVersion,
      "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion,
      "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion,
      "org.scalatest" %% "scalatest" % "3.2.11" % "test",
      "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
      "ch.qos.logback" % "logback-classic" % "1.2.11"
    )
  )

//ScoverageKeys.coverageMinimum := 90
//ScoverageKeys.coverageFailOnMinimum := true

