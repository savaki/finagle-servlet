import sbt._

object Build extends Build {
  lazy val all = Project(id = "all",
    base = file(".")) aggregate(finagle_servlet, finagle_web)

  lazy val finagle_servlet = Project(id = "finagle-servlet",
    base = file("finagle-servlet"))

  lazy val finagle_web = Project(id = "finagle-web",
    base = file("finagle-web")) dependsOn (finagle_servlet)

}
