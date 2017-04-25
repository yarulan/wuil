package wuil.web

import org.scalajs.dom.raw.{Element, Node, Text}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("IncrementalDOM")
object IncrementalDom extends js.Object {
  def elementOpen(tagName: String, key: String, staticPropertyValuePairs: js.Array[js.Any], propertyValuePairs: js.Any*): Element = js.native

  def elementOpenStart(tagName: String, key: String, staticPropertyValuePairs: js.Array[js.Any]): Unit = js.native

  def attr(name: String, value: js.Any): Unit = js.native

  def elementOpenEnd(): Element = js.native

  def elementClose(tagName: String): Element = js.native

  def elementVoid(tagName: String, key: String, staticPropertyValuePairs: js.Array[js.Any], propertyValuePairs: js.Any*): Element = js.native

  def text(value: js.Any): Text = js.native

  def patch(node: Node, description: js.Function1[js.Any, Unit], data: js.Any): Unit = js.native
}