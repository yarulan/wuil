package wuil

import htmldsl.Backend

trait View {
  implicit var backend: Backend = null
  val msg = "Hello"
  def render(): Unit
  def attachHandlers(): Unit = {}
}