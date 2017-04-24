package wuil.examples.todo.core

trait Api {
  def getTodos(): Seq[Todo]
}