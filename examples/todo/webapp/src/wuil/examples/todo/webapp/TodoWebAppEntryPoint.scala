package wuil.examples.todo.webapp

import htmldsl.dom.DomBackend
import org.scalajs.dom.window
import org.scalajs.dom.document
import wuil.examples.todo.core.{Todo, TodoListPage}
import wuil.web.{IncrementalDom, IncrementalDomBackend}

import scala.scalajs.js.JSApp

object TodoWebAppEntryPoint extends JSApp {
  override def main(): Unit = {
    println("path", window.location.pathname)
    val view = new TodoListPage(Seq(Todo("1", false), Todo("2", false), Todo("3", true)).toBuffer)
    view.backend = IncrementalDomBackend
    println(document.body)
    IncrementalDom.patch(document.body, (_) => {
      view.render()
      view.attachHandlers()
    }, null)
    view.backend = DomBackend
  }
}
