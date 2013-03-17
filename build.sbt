scalaVersion := "2.10.0"

//resolvers += Resolver.sonatypeRepo("snapshots")

resolvers += "Typesafe snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"

resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/releases"


libraryDependencies += "com.typesafe.akka" %% "akka-camel" % "2.1.0"