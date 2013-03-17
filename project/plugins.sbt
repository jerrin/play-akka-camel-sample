// Comment to get more information during initialization
logLevel := Level.Warn

//scalaVersion := "2.10.0"

// The Typesafe repository 
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Typesafe snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"


// Use the Play sbt plugin for Play projects

addSbtPlugin("play" % "sbt-plugin" % "2.1.0")

