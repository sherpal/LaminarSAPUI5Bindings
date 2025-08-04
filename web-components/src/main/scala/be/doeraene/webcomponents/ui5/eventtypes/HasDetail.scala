package be.doeraene.webcomponents.ui5.eventtypes

import scala.scalajs.js

trait HasDetail[DetailType] extends js.Object {

  def detail: DetailType

}

object HasDetail {
  trait HasValue[ValueType] extends js.Object {
    def value: ValueType
  }
}
