package wuil.examples.todo.core

import wuil.View

import scala.collection.mutable
import htmldsl._
import org.scalajs.dom.raw.{Element, HTMLLIElement}

import scala.collection.mutable.ArrayBuffer

class Page extends View {
  override def render(): Unit = {
    html(lang := "en", "data-framework" := "wuil") {
      head {
        meta(charset := "utf-8")
        title("WUIL • TodoMVC")
        link(rel := "stylesheet", href := "/static/todomvc-common/base.css")
        link(rel := "stylesheet", href := "/static/todomvc-app-css/index.css")
        script("src" := "/static/incremental-dom.js")
        script("src" := "/static/fastopt.js", "defer" := "")
      }
    }
  }
}

class TodoListPage(initialTodos: Seq[Todo]) extends View {
  private val todos = ArrayBuffer(initialTodos: _*)

  val autofocus = Attr("autofocus")

  private def todoView(todo: Todo): HTMLLIElement = {
    li {
      val listItem = Backend.current.getElementUnderConstruction.get
      div(cls := "view") {
        input(cls := "toggle", tpe := "checkbox", checked := todo.completed)
        label(todo.title)
        val btn = button(cls := "destroy")
        btn.onclick = (_) => {
          todoList.removeChild(listItem)
        }
      }
      input(cls := "edit", "value" := todo.title)
    }
  }

  private lazy val todoList = {
    ul(cls := "todo-list") {
      todos.foreach { todo =>
        todoView(todo)
      }
    }
  }

  override def render(): Unit = {
    section(cls := "todoapp") {
      header(cls := "header") {
        h1("todos")
        val newTodoInput = input(cls := "new-todo", placeholder := "What needs to be done?", autofocus)
        newTodoInput.onkeypress = (e) => {
          if (e.keyCode == 13) {
            e.preventDefault()
            val value = newTodoInput.value.trim
            if (!value.isEmpty) {
              todoList.appendChild(todoView(Todo(value, false)))
              newTodoInput.value = ""
            }
            println("Enter")
          }
        }
      }
      section(cls := "main") {
        input(cls := "toggle-all", id := "toggle-all", tpe := "checkbox")
        label(`for` := "toggle-all", "Mark all as complete")
        todoList
      }
      footer(cls := "footer") {
        span(cls := "todo-count") {
          val nRemaining = todos.count(!_.completed)
          strong(nRemaining.toString)
          text( s""" ${if (nRemaining == 1) "item" else "items"} left""")
        }
        ul(cls := "filters") {
          li {
            a(cls := "selected", href := "#/", "All")
          }
          li {
            a(href := "#/active", "Active")
          }
          li {
            a(href := "#/completed", "Completed")
          }
        }
        val nCompleted = todos.count(_.completed)
        if (nCompleted > 0) {
          button(cls := "clear-completed", "Clear completed")
        }
      }
    }
    footer(cls := "info") {
      p("Double-click to edit a todo")
      p {
        text("Written by ")
        a(href := "https://github.com/yarulan") {
          text("Yaroslav Ulanovych")
        }
      }
      p {
        text("Part of ")
        a(href := "http://todomvc.com") {
          text("TodoMVC")
        }
      }
    }
  }
}