name := "finatra-servlet"

organization := "com.github.savaki"

scalaVersion := "2.9.1"

version := "0.1"

{
    val finagleVersion = "5.3.9"
    libraryDependencies ++= Seq(
        "com.twitter" % "finagle-core" % finagleVersion withSources(),
        "com.twitter" % "finagle-native" % finagleVersion withSources(),
        "com.twitter" % "finagle-http" % finagleVersion withSources()
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



