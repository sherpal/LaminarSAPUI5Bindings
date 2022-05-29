package be.doeraene.webcomponents.ui5.eventtypes

import org.scalajs.dom

import scala.scalajs.js

trait HasTargetRef[ElemType <: dom.HTMLElement] extends js.Object {
  def targetRef: ElemType
}
