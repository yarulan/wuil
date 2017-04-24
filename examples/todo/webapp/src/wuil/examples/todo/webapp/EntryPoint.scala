package wuil.examples.todo.webapp

import org.scalajs.dom.window
import scala.scalajs.js.JSApp

object EntryPoint extends JSApp {
  override def main(): Unit = {
    println("path", window.location.pathname)
  }
}
