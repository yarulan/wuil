def defModule(dir: String, id: String = null): Project = Project(Option(id).getOrElse(dir), file(dir))
  .settings(
    scalaVersion := "2.12.2",
    organization := "pro.ulan.wuil",
    version := "1.0-SNAPSHOT",
    scalaSource in Compile := baseDirectory.value / "src",
    scalaSource in Test := baseDirectory.value / "test",
    resourceDirectory in Compile := baseDirectory.value / "res"
  )

lazy val core = defModule("core")
  .enablePlugins(ScalaJSPlugin)
  .settings(
    libraryDependencies ++= Seq(
      "pro.ulan.html-dsl" %%% "core" % "1.0-SNAPSHOT"
    ))

lazy val server = defModule("server")
  .dependsOn(core)
  .settings(
    libraryDependencies ++= Seq(
      "pro.ulan.html-dsl" %% "string" % "1.0-SNAPSHOT"
    )
  )

lazy val web = defModule("web")
  .dependsOn(core)
  .enablePlugins(ScalaJSPlugin)
  .settings(
    libraryDependencies ++= Seq(
      "pro.ulan.html-dsl" %%% "dom" % "1.0-SNAPSHOT"
    ))

lazy val wuil = defModule(".", "wuil")
  .aggregate(core, server, web, examples)

lazy val todoCore = defModule("examples/todo/core", "todoCore")
  .dependsOn(core)
  .enablePlugins(ScalaJSPlugin).
  settings(libraryDependencies ++= Seq(
    "com.lihaoyi" %%% "autowire" % "0.2.6",
    "com.lihaoyi" %%% "upickle" % "0.4.4"
  ))

lazy val todoServer = defModule("examples/todo/server", "todoServer")
  .dependsOn(todoCore)
  .dependsOn(server)
  .dependsOn(todoWebApp)
  .settings(libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-http" % "10.0.5",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
    "ch.qos.logback" % "logback-classic" % "1.1.7"
  ))

lazy val todoWebApp = defModule("examples/todo/webapp", "todoWebApp")
  .dependsOn(todoCore)
  .dependsOn(web)
  .enablePlugins(ScalaJSPlugin)
  .settings(
    artifactPath in(Compile, fastOptJS) := target.value / s"scala-${scalaBinaryVersion.value}" / "classes" / "static" / "fastopt.js",
    scalaJSUseMainModuleInitializer := true
  )

lazy val todo = defModule("examples/todo", "todo")
  .aggregate(todoCore, todoServer, todoWebApp)

lazy val examples = defModule("examples", "examples")
  .aggregate(todo)