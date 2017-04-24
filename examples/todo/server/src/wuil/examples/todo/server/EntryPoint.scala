package wuil.examples.todo.server

import java.io.File

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.Directives._

object EntryPoint {
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("DefaultActorSystem")
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher

    val route = concat(
      get {
        concat(
          pathEndOrSingleSlash {
            getFromFile("examples/todo/webapp/res/static/index.html")
          },
          pathPrefix("static") {
            getFromDirectory("examples/todo/webapp/res/static/index.html")
          },
          pathPrefix("static") {
            getFromResourceDirectory("static")
          }
        )
      }
    )

    Http().bindAndHandle(route, "localhost", 8080).onComplete {
      case util.Success(binding) =>
        println(s"Started at ${binding.localAddress}")
    }
  }
}
