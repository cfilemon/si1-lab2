name := """2GIG"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  javaJpa,
  javaCore,
  "org.hibernate" % "hibernate-entitymanager" % "3.6.9.Final"
)

scalacOptions ++= Seq("-encoding", "UTF-8")

