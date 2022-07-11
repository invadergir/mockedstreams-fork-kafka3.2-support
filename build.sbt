lazy val commonSettings = Seq(
  organization := "com.madewithtea",
  version := "4.0.0",
  scalaVersion := "2.13.8",
  crossScalaVersions := List("2.12.10", "2.13.8"),
  description := "Topology Unit-Testing Library for Kafka Streams",
  organizationHomepage := Some(url("https://www.madewithtea.com")),
  scalacOptions := Seq("-deprecation","-feature")
)

val scalaTestVersion = "3.0.8"
val rocksDBVersion = "5.18.4"
val kafkaVersion = "3.2.0"

lazy val kafka = Seq(
  "org.apache.kafka" % "kafka-clients" % kafkaVersion,
  "org.apache.kafka" % "kafka-clients" % kafkaVersion classifier "test",
  "org.apache.kafka" % "kafka-streams" % kafkaVersion,
  "org.apache.kafka" %% "kafka-streams-scala" % kafkaVersion,
  "org.apache.kafka" % "kafka-streams-test-utils" % kafkaVersion,
  "org.apache.kafka" %% "kafka" % kafkaVersion
)

lazy val scalaTest = "org.scalatest" %% "scalatest" % scalaTestVersion % "test"
lazy val rocksDB = "org.rocksdb" % "rocksdbjni" % rocksDBVersion % "test"

Global / useGpg := false

lazy val mockedstreams = (project in file("."))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      scalaTest,
      rocksDB
    ) ++ kafka
  )

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ =>
  false
}

pomExtra :=
  <url>https://www.madewithtea.com/pages/mocked-streams.html</url>
    <licenses>
      <license>
        <name>Apache License Version 2.0</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <developers>
      <developer>
        <id>jpzk</id>
        <name>Jendrik Poloczek</name>
        <url>https://www.madewithtea.com</url>
      </developer>
    </developers>

// disabled because it depends on 'tut' lib which is no longer available.  See https://github.com/tpolecat/tut
//micrositeName := "Mocked Streams"
//micrositeDescription := "Unit-Testing Topologies in Kafka Streams"
//micrositeUrl := "http://mockedstreams.madewithtea.com"
//micrositeBaseUrl := ""
//micrositeTheme := "pattern"
//micrositeDocumentationUrl := "/docs"
//micrositeGitterChannel := false
//micrositeDocumentationLabelDescription := "Documentation"
//micrositeCompilingDocsTool := WithMdoc
//micrositeDataDirectory := (resourceDirectory in Compile).value / "docs" / "data"
//micrositeGithubOwner := "jpzk"
//micrositeGithubRepo := "mockedstreams"
//micrositeAuthor := "Jendrik Poloczek"
//micrositeTwitter := "@madewithtea"
//micrositeTwitterCreator := "@madewithtea"
//micrositeCompilingDocsTool := WithMdoc
//micrositeShareOnSocial := true
//
//
//lazy val docs = project // new documentation project
//  .in(file("ms-docs")) // important: it must not be docs/
//  .dependsOn(mockedstreams)
//  .enablePlugins(MdocPlugin)
//
//enablePlugins(MicrositesPlugin)
