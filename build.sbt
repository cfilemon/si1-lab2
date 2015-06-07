name := """2GIG"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),
  "org.hibernate" % "hibernate-core" % "4.3.10.Final",
  "org.hibernate" % "hibernate-entitymanager" % "4.3.10.Final",
  "org.apache.directory.api" % "api-all" % "1.0.0-M30"
)

scalacOptions ++= Seq("-encoding", "UTF-8")




