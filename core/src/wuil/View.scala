package wuil

trait View {
  val msg = "Hello"
  def render(): Unit
  def attachHandlers(): Unit = {}
}