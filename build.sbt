
/*
 * BASIC INFORMATION
 ********************************************************/


name := "ubirch-deep-check-utils"
version := "0.4.1"
description := "actor and JSON models for the /deepCheck endpoints"
organization := "com.ubirch.util"
homepage := Some(url("http://ubirch.com"))
scalaVersion := "2.11.12"
scalacOptions ++= Seq(
  "-feature"
)
scmInfo := Some(ScmInfo(
  url("https://github.com/ubirch/ubirch-deep-check-utils"),
  "https://github.com/ubirch/ubirch-deep-check-utils.git"
))

/*
 * CREDENTIALS
 ********************************************************/

(sys.env.get("CLOUDREPO_USER"), sys.env.get("CLOUDREPO_PW")) match {
  case (Some(username), Some(password)) =>
    println("USERNAME and/or PASSWORD found.")
    credentials += Credentials("ubirch.mycloudrepo.io", "ubirch.mycloudrepo.io", username, password)
  case _ =>
    println("USERNAME and/or PASSWORD is taken from /.sbt/.credentials")
    credentials += Credentials(Path.userHome / ".sbt" / ".credentials")
}


/*
 * RESOLVER
 ********************************************************/


resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"))


/*
 * PUBLISHING
 ********************************************************/

val resolverUbirchUtils = "cloudrepo.io" at "https://ubirch.mycloudrepo.io/repositories/ubirch-utils-mvn"

publishTo := Some(resolverUbirchUtils)
publishMavenStyle := true


/*
 * DEPENDENCIES
 ********************************************************/

//version
val json4sV = "3.6.0"


// Ubirch dependencies
val ubirchUtilJson = "com.ubirch.util" %% "json" % "0.5.1"

// External dependencies
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"

val json4sJackson = "org.json4s" %% "json4s-jackson" % json4sV
val json4sCore = "org.json4s" %% "json4s-core" % json4sV
val json4sExt = "org.json4s" %% "json4s-ext" % json4sV
val json4sNative = "org.json4s" %% "json4s-native" % json4sV

val json4sBase = Seq(
  json4sCore,
  json4sJackson,
  json4sExt
)
val json4sWithNative = json4sBase :+ json4sNative

libraryDependencies ++= Seq(
  ubirchUtilJson,
  scalaTest % "test"

) ++ json4sWithNative

