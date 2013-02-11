name := "finagle-web"

organization := "com.github.savaki"

scalaVersion := "2.9.1"

version := "0.1"

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
           "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container,provided" artifacts Artifact("javax.servlet", "jar", "jar"),
           "org.eclipse.jetty" % "jetty-webapp" % jettyVersion % "container,provided" withSources(),
           "org.eclipse.jetty" % "jetty-server" % jettyVersion % "container,provided" withSources(),
           "org.eclipse.jetty" % "jetty-io" % jettyVersion % "container,provided" withSources(),
           "org.eclipse.jetty" % "jetty-http" % jettyVersion % "container,provided" withSources(),
           "org.eclipse.jetty" % "jetty-util" % jettyVersion % "container,provided" withSources(),
           "org.eclipse.jetty" % "jetty-servlets" % jettyVersion % "container,provided" withSources()
    )
}


// add web tasks
seq(webSettings :_*)


