package wuil.examples.todo.core

import autowire.Serializers
import upickle.Js
import upickle.default._

trait JsonSerializers extends Serializers[Js.Value, Reader, Writer] {
  def read[Result: Reader](p: Js.Value) = readJs[Result](p)

  def write[Result: Writer](r: Result) = writeJs(r)
}