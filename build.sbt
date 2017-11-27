import sbt.ExclusionRule

name := "model-repo"

version := "0.1"

scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
  "org.apache.spark"              %% "spark-core"                    % "2.2.0",
  "org.apache.spark"              %% "spark-mllib"                   % "2.2.0",
  "org.scalatest" %% "scalatest" % "3.0.3" % Test,
  "junit" % "junit" % "4.11" % Test
)