package wuil.examples.todo.core

import wuil.View

import scala.collection.mutable
import htmldsl._
import org.scalajs.dom.raw.HTMLLIElement

import scala.collection.mutable.ArrayBuffer

class Page extends View {
  override def render(): Unit = {
    html(lang := "en", "data-framework" := "wuil", {
      head {
        meta(charset := "utf-8")
        title {
          text("WUIL • TodoMVC")
        }
        link(rel := "stylesheet", href := "/static/todomvc-common/base.css")
        link(rel := "stylesheet", href := "/static/todomvc-app-css/index.css")
        script("src" := "/static/incremental-dom-min.js")
        script("src" := "/static/fastopt.js", "defer" := "")
      }
    })
  }
}

class TodoListPage(initialTodos: Seq[Todo]) extends View {
  private val todos = ArrayBuffer(initialTodos: _*)

  val autofocus = Attr("autofocus")

  private lazy val newTodoInput = {
    input(cls := "new-todo", placeholder := "What needs to be done?", autofocus)
  }

  private def todoView(todo: Todo): HTMLLIElement = {
    li {
      val listItem = backend.getElementUnderConstruction.get
      div(cls := "view", {
        input(cls := "toggle", tpe := "checkbox", checked := todo.completed)
        label { text(todo.title) }
        val btn = button(cls := "destroy")
        if (btn ne null) {
          btn.onclick = (_) => {
            todoList.removeChild(listItem)
          }
        }
      })
      input(cls := "edit", "value" := todo.title)
    }
  }

  private lazy val todoList = {
    ul(cls := "todo-list", {
      todos.foreach { todo =>
        todoView(todo)
      }
    })
  }

  override def attachHandlers(): Unit = {
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

  override def render(): Unit = {
    section(cls := "todoapp", {
      header(cls := "header", {
        h1 {
          text("todos")
        }
        newTodoInput
      })
      section(cls := "main", {
        input(cls := "toggle-all", id := "toggle-all", tpe := "checkbox")
        label(`for` := "toggle-all", {
          text("Mark all as complete")
        })
        todoList
      })
      footer(cls := "footer", {
        span(cls := "todo-count", {
          val nRemaining = todos.count(!_.completed)
          strong { text(nRemaining.toString) }
          text( s""" ${if (nRemaining == 1) "item" else "items"} left""")
        })
        ul(cls := "filters", {
          li { a(cls := "selected", href := "#/", { text("All") }) }
          li { a(href := "#/active", { text("Active") }) }
          li { a(href := "#/completed", { text("Completed") }) }
        })
        val nCompleted = todos.count(_.completed)
        if (nCompleted > 0) {
          button(cls := "clear-completed", { text("Clear completed") })
        }
      })
    })
    footer(cls := "info", {
      p {
        text("Double-click to edit a todo")
      }
      p {
        text("Written by ")
        a(href := "https://github.com/yarulan", {
          text("Yaroslav Ulanovych")
        })
      }
      p {
        text("Part of ")
        a(href := "http://todomvc.com", {
          text("TodoMVC")
        })
      }
    })
  }
}