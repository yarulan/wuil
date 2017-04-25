package wuil.web

import htmldsl.{Attr, Backend, NoAttr, NoValueAttr, ValuedAttr}
import org.scalajs.dom.raw.{Element, Text}

object IncrementalDomBackend extends Backend {
  import IncrementalDom._

  override def beginElement[T <: Element](tagName: String, attrs: Seq[Attr]): T = {
    elementOpenStart(tagName, null, null)
    attrs.foreach {
      case NoAttr =>
        // Do nothing
      case ValuedAttr(name, value) =>
        attr(name, value)
      case NoValueAttr(name) =>
      attr(name, "")
    }
    elementOpenEnd().asInstanceOf[T]
  }

  override def endElement(tagName: String): Unit = elementClose(tagName)

  override def createTextNode(data: String): Text = text(data)
}
