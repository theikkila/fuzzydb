name := """proofing"""

version := "1.0"

scalaVersion := "2.11.5"

// Change this to another test framework if you prefer
//libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"
libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
libraryDependencies += "org.scala-lang.modules" %% "scala-pickling" % "0.10.0"
// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % "2.3.9"

mainClass in (Compile, run) := Some("Runner")