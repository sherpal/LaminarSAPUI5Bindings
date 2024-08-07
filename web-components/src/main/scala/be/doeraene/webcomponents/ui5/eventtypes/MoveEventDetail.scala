package be.doeraene.webcomponents.ui5.eventtypes

import scala.scalajs.js
import org.scalajs.dom

trait MoveEventDetail[+ElemType <: dom.html.Element] extends js.Object {
  def source: MoveEventDetail.HasElement[ElemType]
  def destination: MoveEventDetail.HasElement[ElemType] & MoveEventDetail.HasPlacement

}

object MoveEventDetail {
  trait HasElement[+ElemType <: dom.html.Element] extends js.Object {
    def element: ElemType
  }

  trait HasPlacement extends js.Object {
    def placement: "Before" | "After" | "On"
  }
}
