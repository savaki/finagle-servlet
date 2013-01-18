name := "finagle-web"

organization := "com.github.savaki"

scalaVersion := "2.9.1"

version := "0.1"

//resolvers += "Finatra repo" at "http://repo.juliocapote.com/"
resolvers += "Twitter Maven repo" at "http://maven.twttr.com/"

{
    val finatraVersion = "1.0.1"
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
         "org.eclipse.jetty" % "jetty-webapp" % jettyVersion % "container" withSources(),
         "org.eclipse.jetty" % "jetty-server" % jettyVersion % "container" withSources(),
         "org.eclipse.jetty" % "jetty-io" % jettyVersion % "container" withSources(),
         "org.eclipse.jetty" % "jetty-http" % jettyVersion % "container" withSources(),
         "org.eclipse.jetty" % "jetty-util" % jettyVersion % "container" withSources(),
         "org.eclipse.jetty" % "jetty-servlets" % jettyVersion % "container" withSources())
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

// add web tasks
seq(webSettings :_*)



