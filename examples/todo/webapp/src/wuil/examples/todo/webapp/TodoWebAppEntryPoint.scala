package wuil.examples.todo.webapp

import htmldsl.Backend
import org.scalajs.dom.document
import htmldsl.backends.dom.DomBackend
import wuil.examples.todo.core.{Todo, TodoListPage}
import wuil.web.{IncrementalDom, IncrementalDomBackend}

import scala.scalajs.js.JSApp

object TodoWebAppEntryPoint extends JSApp {
  override def main(): Unit = {
    println("Starting")
    IncrementalDomBackend.enable()
    val view = new TodoListPage(Seq(Todo("1", false), Todo("2", false), Todo("3", true)).toBuffer)
//    view.backend = IncrementalDomBackend
    println(document.body)
    IncrementalDom.patch(document.body, (_) => {
      view.render()
      view.attachHandlers()
    }, null)
    DomBackend.enable()
    println("Started")
  }
}
