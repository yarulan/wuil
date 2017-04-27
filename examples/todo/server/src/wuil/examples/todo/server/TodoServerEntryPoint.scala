package wuil.examples.todo.server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentType, ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import htmldsl.backends.`object`.ObjectBackend
import upickle.Js
import wuil.examples.todo.core.{Page, Router}

object TodoServerEntryPoint {
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("DefaultActorSystem")
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher

    val api = ApiImpl
    val server = new AutowireServer(api)
    val client = new Client(server)
    val router = new Router(api, client)

    val route = concat(
      get {
        concat(
          pathPrefix("static") {
            getFromResourceDirectory("static")
          },
          path(Segments) { s =>
            complete(router.route(s.mkString("/")).map{ _ =>
              val view = new Page
              ObjectBackend.enable()
              view.render()
              import htmldsl.nodeToRenderable
              val html = ObjectBackend.lastConstructedNode.get.toHtmlString
              HttpEntity(ContentTypes.`text/html(UTF-8)`, html)
            })
          }
        )
      },
      post {
        path("api" / Segments) { s =>
          extract(_.request.entity match {
            case HttpEntity.Strict(nb: ContentType.NonBinary, data) =>
              data.decodeString(nb.charset.value)
          }) { e =>
            complete {
              val args = upickle.json.read(e).asInstanceOf[Js.Obj].value.toMap
              server.routes(autowire.Core.Request(s, args)).map(upickle.json.write(_))
            }
          }
        }
      }
    )

    Http().bindAndHandle(route, "localhost", 8080).onComplete {
      case util.Success(binding) =>
        println(s"Started at ${binding.localAddress}")
    }
  }
}
