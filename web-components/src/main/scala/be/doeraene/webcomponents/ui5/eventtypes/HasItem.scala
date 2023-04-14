package be.doeraene.webcomponents.ui5.eventtypes

import org.scalajs.dom

import scala.scalajs.js

trait HasItem[ExtraType] extends js.Object {
  def item: dom.HTMLElement with ExtraType
}
