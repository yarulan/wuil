package wuil.examples.todo.core

import upickle.Js
import upickle.default._
import scalajs.concurrent.JSExecutionContext.Implicits.queue

import scala.concurrent.Future

object Client extends autowire.Client[Js.Value, Reader, Writer] with JsonSerializers {
  override def doCall(req: Request): Future[Js.Value] = {
    ???
//    Ajax.post(
//      url = "/api/" + req.path.mkString("/"),
//      data = upickle.json.write(Js.Obj(req.args.toSeq: _*))
//    ).map(_.responseText)
//      .map(upickle.json.read)
  }
}