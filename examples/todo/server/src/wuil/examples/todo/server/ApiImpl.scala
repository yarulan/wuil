package wuil.examples.todo.server

import wuil.examples.todo.core.{Api, Todo}

import scala.collection.mutable.ArrayBuffer

object ApiImpl extends Api {
  private var todos = ArrayBuffer[Todo]()
  override def getTodos(): Seq[Todo] = Seq(todos: _*)
}
