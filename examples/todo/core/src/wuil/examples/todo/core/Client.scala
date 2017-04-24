package wuil.examples.todo.core

import upickle.default.{Reader, Writer}

import scala.concurrent.Future

object Client extends autowire.Client[String, Reader, Writer] {
  def write[Result: Writer](r: Result) = upickle.default.write(r)
  def read[Result: Reader](p: String) = upickle.default.read[Result](p)

  override def doCall(req: Request): Future[String] = {
    ???
  }
}
