package be.doeraene.webcomponents.ui5.eventtypes

import org.scalajs.dom

import scala.scalajs.js

/** Detail of the `click` event fired by list items (ui5-li, ui5-li-custom, ui5-tree-item, ui5-tree-item-custom,
  * ui5-menu-item, ...) since UI5 2.23.0.
  */
@js.native
trait ListItemClickDetail extends js.Object {

  /** The original (mouse or keyboard) event that triggered the click. */
  def originalEvent: dom.Event = js.native
}
