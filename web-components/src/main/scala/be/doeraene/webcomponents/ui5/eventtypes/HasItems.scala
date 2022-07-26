package be.doeraene.webcomponents.ui5.eventtypes

import org.scalajs.dom

import scala.scalajs.js

trait HasItems[ExtraType] extends js.Object {
  def items: js.Array[dom.HTMLElement & ExtraType]
}
