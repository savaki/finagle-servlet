name := "finagle-servlet"

organization := "com.github.savaki"

scalaVersion := "2.9.1"

version := "0.1"

{
    val finatraVersion = "1.0.1"
    libraryDependencies ++= Seq(
        "org.mozilla" % "rhino" % "1.7R4" % "compile",
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
         "org.eclipse.jetty" % "jetty-webapp" % jettyVersion % "compile" withSources(),
         "org.eclipse.jetty" % "jetty-server" % jettyVersion % "compile" withSources(),
         "org.eclipse.jetty" % "jetty-io" % jettyVersion % "compile" withSources(),
         "org.eclipse.jetty" % "jetty-http" % jettyVersion % "compile" withSources(),
         "org.eclipse.jetty" % "jetty-util" % jettyVersion % "compile" withSources(),
         "org.eclipse.jetty" % "jetty-servlets" % jettyVersion % "compile" withSources())
}

publishTo := Some(Resolver.file("hello",  new File( "/Users/matt/projects/maven-repo")))


