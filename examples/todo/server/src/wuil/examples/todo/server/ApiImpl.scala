package wuil.examples.todo.server

import wuil.examples.todo.core.{Api, Todo}

import scala.collection.mutable.ArrayBuffer

object ApiImpl extends Api {
  private var todos = ArrayBuffer[Todo](Todo("1"), Todo("3"), Todo("2"))
  override def getTodos(): Seq[Todo] = Seq(todos: _*)
}
