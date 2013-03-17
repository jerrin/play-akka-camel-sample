import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName         = "TextSearch"
    val appVersion      = "1.0"

    val appDependencies = Seq(javaCore, javaJdbc, javaEbean,
    	"net.sf.opencsv" % "opencsv" % "2.3",
    	"org.apache.commons" % "commons-lang3" % "3.1",
	    "com.google.inject" % "guice" % "3.0",
	    "com.tzavellas" % "sse-guice" % "0.7.1",
		"org.apache.camel"  % "camel-jetty"   % "2.10.4",
		"org.apache.camel"  % "camel-ftp"   % "2.10.4",
		"com.clever-age" % "play2-elasticsearch" % "0.5.3"
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(
	resolvers += Resolver.url("play-plugin-releases", new URL("http://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/"))(Resolver.ivyStylePatterns),
	resolvers += Resolver.url("play-plugin-snapshots", new URL("http://repo.scala-sbt.org/scalasbt/sbt-plugin-snapshots/"))(Resolver.ivyStylePatterns),
	resolvers := Seq("typesafe" at "http://repo.typesafe.com/typesafe/repo"),
	resolvers := Seq("sonatype" at "http://oss.sonatype.org/content/repositories/releases")
	        
    )

} 
