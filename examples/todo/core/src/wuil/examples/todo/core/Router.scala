package wuil.examples.todo.core

import autowire._
import upickle.Js
import upickle.default._
import wuil.View

import scala.concurrent.{ExecutionContext, Future}

class Router(api: Api, client: autowire.Client[Js.Value, Reader, Writer])
  (implicit ctx: ExecutionContext)
  extends wuil.Router {
  override def route(url: String): Future[View] = {
    client[Api].getTodos().call().map(todos => new TodoListPage(todos.toBuffer))
  }
}