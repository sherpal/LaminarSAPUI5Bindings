package be.doeraene.webcomponents.ui5.eventtypes

import org.scalajs.dom

import scala.scalajs.js

trait HasSelectedItem[ExtraType] extends js.Object {
  def selectedItem: dom.HTMLElement with ExtraType
}
