name := "finagle-servlet"

organization := "com.github.savaki"

scalaVersion := "2.9.1"

version := "0.1-SNAPSHOT"

resolvers += "Twitter Maven repo" at "http://maven.twttr.com/"

{
    val finatraVersion = "1.2.0"
    libraryDependencies ++= Seq(
        "com.twitter" % "finatra" % finatraVersion withSources()
    )
}

{
    libraryDependencies ++= Seq(
        "org.scalatest" %% "scalatest" % "1.8" % "test" withSources()
    )
}

{
    val jettyVersion = "8.1.8.v20121106"
    libraryDependencies ++= Seq(
         "org.eclipse.jetty" % "jetty-server" % jettyVersion % "provided" withSources()
    )
}

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

