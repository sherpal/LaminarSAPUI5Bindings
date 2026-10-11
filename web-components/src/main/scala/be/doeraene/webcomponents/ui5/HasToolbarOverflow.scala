package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.ToolbarItemOverflowBehavior
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.keys.HtmlAttr

/** Attributes shared by all the items that can be put inside a [[Toolbar]] (ui5-toolbar-button, ui5-toolbar-select,
  * ui5-toolbar-separator, ui5-toolbar-spacer and ui5-toolbar-item), controlling how they behave in the overflow popover.
  */
trait HasToolbarOverflow {

  /** Priority of the item to go inside the overflow popover. */
  lazy val overflowPriority: HtmlAttr[ToolbarItemOverflowBehavior] =
    ToolbarItemOverflowBehavior.asHtmlAttr("overflow-priority")

  /** Whether the overflow popover should stay open when the item is interacted with. */
  lazy val preventOverflowClosing: HtmlAttr[Boolean] = htmlAttr("prevent-overflow-closing", BooleanAsAttrPresenceCodec)

  /** Name of the overflow group of the item. Items sharing the same (non-empty) group overflow together, as one unit
    * (all of them are in the toolbar, or all of them in the overflow popover). Items in a group must keep the default
    * `overflowPriority`, and spacers don't participate in groups.
    *
    * @since UI5 2.27.0
    */
  lazy val overflowGroup: HtmlAttr[String] = htmlAttr("overflow-group", StringAsIsCodec)

}
