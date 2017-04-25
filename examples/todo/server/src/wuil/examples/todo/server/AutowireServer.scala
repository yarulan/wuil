package wuil.examples.todo.server

import upickle.Js
import upickle.default._
import wuil.examples.todo.core.{Api, JsonSerializers}

import scala.concurrent.ExecutionContext

class AutowireServer(api: Api)(implicit ctx: ExecutionContext)
  extends autowire.Server[Js.Value, Reader, Writer]
  with JsonSerializers
{
  val routes = route[Api](api)
}