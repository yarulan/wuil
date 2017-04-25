package wuil.examples.todo.core

import wuil.View

import scala.collection.mutable

import htmldsl._

class TodoListPage(todos: mutable.Seq[Todo]) extends View {
  override def render(): Unit = {
    'ul {
      todos.foreach { todo =>
        'li {
          text(todo.title)
        }
      }
    }
  }
}