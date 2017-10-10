import java.io.File
import java.util.Properties

lazy val projName = project.in(file(".")).
settings(
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.10.6",
  name := "replace-proj-name"
)

lazy val versions = Map(
  "spark" -> "1.6.0-cdh5.8.0"
)

EclipseKeys.withSource := true
EclipseKeys.withJavadoc := true

ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % versions("spark") % "provided",
  "org.apache.spark" %% "spark-sql" % versions("spark") % "provided",
  "com.databricks" %% "spark-avro" % "2.0.1",
  "com.typesafe" % "config" % "1.2.1",
  "com.typesafe.scala-logging" % "scala-logging-slf4j_2.10" % "2.1.2",
  "com.github.scopt" %% "scopt" % "3.4.0",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test",
   "com.databricks" %% "spark-csv" % "1.5.0",
    "org.apache.hadoop" % "hadoop-aws" % "2.7.3", 
    "org.apache.spark" %% "spark-hive" % "1.6.2",
    "com.amazonaws" % "aws-java-sdk-s3" % "1.11.36"
)


assemblyMergeStrategy in assembly := {
 case PathList("META-INF", xs @ _*) => MergeStrategy.discard
 case x => MergeStrategy.first
}

dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.4.4" 

resolvers += Resolver.sonatypeRepo("public")
resolvers ++= Seq(
  "Artima" at "http://repo.artima.com/releases",
  "Local" at s"file://${Path.userHome.absolutePath}/.m2/repository",
  "Cloudera" at "https://repository.cloudera.com/artifactory/cloudera-repos/"
)

credentials += Credentials(Path.userHome / ".sbt" / ".credentials")

publishTo := {
  val prop = new Properties()
  IO.load(prop, new File(Path.userHome + "/.sbt/.credentials"))
  val nexusUri = "http://" + prop.getProperty("host") + ":" + prop.getProperty("port")

  if (isSnapshot.value)
    Some("snapshots" at nexusUri + "/repository/proj-snapshot")
  else
    Some("releases" at nexusUri + "/repository/proj-release")
}
