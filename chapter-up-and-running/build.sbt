val akkaVersion = "2.5.20"
val akkaHttpVersion = "10.1.7"

lazy val up = (project in file("."))
    .enablePlugins(JavaServerAppPackaging)
    .settings(
      name := "goticks",

      version := "1.0",

      organization := "com.goticks",

      libraryDependencies ++= Seq(
          "com.typesafe.akka" %% "akka-actor"      % akkaVersion,
          "com.typesafe.akka" %% "akka-stream"     % akkaVersion,
          "com.typesafe.akka" %% "akka-http-core"  % akkaHttpVersion,
          "com.typesafe.akka" %% "akka-http"       % akkaHttpVersion,
          "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
          "com.typesafe.akka" %% "akka-slf4j"      % akkaVersion,
          "ch.qos.logback"    %  "logback-classic" % "1.1.3",
          "com.typesafe.akka" %% "akka-testkit"    % akkaVersion   % "test",
          "org.scalatest"     %% "scalatest"       % "3.0.0"       % "test"
        )
    )

