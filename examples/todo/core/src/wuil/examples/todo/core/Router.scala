package wuil.examples.todo.core
import wuil.View
import autowire._

import scala.concurrent.{ExecutionContext, Future}

class Router(api: Api)(implicit ctx: ExecutionContext) extends wuil.Router {
  override def route(url: String): Future[View] = {
    Client[Api].getTodos().call().map(todos => new Page(todos.toBuffer))
  }
}
