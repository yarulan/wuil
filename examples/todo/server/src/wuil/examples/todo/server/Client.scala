package wuil.examples.todo.server

import upickle.Js
import upickle.default._
import wuil.examples.todo.core.JsonSerializers

class Client(server: AutowireServer) extends autowire.Client[Js.Value, Reader, Writer] with JsonSerializers {
  override def doCall(req: Request) = {
    server.routes.apply(req)
  }
}
