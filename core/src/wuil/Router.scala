package wuil

import scala.concurrent.Future

trait Router {
  def route(url: String): Future[View]
}
