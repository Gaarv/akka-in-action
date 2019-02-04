val akkaVersion = "2.5.20"

lazy val channels = (project in file("."))
  .enablePlugins(MultiJvmPlugin)
  .settings(
    name := "akka-sample-multi-node-scala",
    organization := "manning",
    version := "1.0",
    libraryDependencies ++= Seq(
    "com.typesafe.akka" %%  "akka-actor"              % akkaVersion,
    "com.typesafe.akka" %%  "akka-slf4j"              % akkaVersion,
    "com.typesafe.akka" %%  "akka-remote"             % akkaVersion,
    "com.typesafe.akka" %%  "akka-multi-node-testkit" % akkaVersion,
    "com.typesafe.akka" %%  "akka-contrib"            % akkaVersion,
    "com.typesafe.akka" %%  "akka-testkit"            % akkaVersion  % "test",
    "org.scalatest"     %%  "scalatest"               % "3.0.0"      % "test"
    )
  ).configs(MultiJvm)
