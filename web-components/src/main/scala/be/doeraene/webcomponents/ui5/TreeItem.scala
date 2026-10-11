package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.{ListItemAccessibleRole, ListItemType, ValueState}
import be.doeraene.webcomponents.ui5.datatypes.AccessibilityAttributes
import be.doeraene.webcomponents.ui5.eventtypes.{EventWithPreciseTarget, HasDetail, ListItemClickDetail}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** This is the item to use inside a ui5-tree. You can represent an arbitrary tree structure by recursively nesting tree
  * items.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/TreeItem/">the doc</a> for more information.
  */
object TreeItem extends WebComponent with HasIcon with HasText with HasAdditionalText {

  @js.native
  trait RawElement extends js.Object {
    def toggle(): Unit = js.native

    def hasChildren: Boolean = js.native

    def selected: Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/TreeItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-tree-item")

  lazy val accessibleName: HtmlAttr[String] = htmlAttr("accessible-name", StringAsIsCodec)
  lazy val expanded: HtmlAttr[Boolean]      = htmlAttr("expanded", BooleanAsAttrPresenceCodec)
  lazy val hasChildren: HtmlAttr[Boolean]   = htmlAttr("has-children", BooleanAsAttrPresenceCodec)
  lazy val selected: HtmlAttr[Boolean]      = htmlAttr("selected", BooleanAsAttrPresenceCodec)
  lazy val highlight: HtmlAttr[ValueState]  = ValueState.asHtmlAttr("highlight")
  lazy val tooltip: HtmlAttr[String]        = htmlAttr("tooltip", StringAsIsCodec)
  lazy val navigated: HtmlAttr[Boolean]     = htmlAttr("navigated", BooleanAsAttrPresenceCodec)
  lazy val movable: HtmlAttr[Boolean]       = htmlAttr("movable", BooleanAsAttrPresenceCodec)
  lazy val tpe: HtmlAttr[ListItemType]      = ListItemType.asHtmlAttr("type")

  lazy val additionalTextState: HtmlAttr[ValueState] = ValueState.asHtmlAttr("additional-text-state")

  /** Whether the selection of the item is in an indeterminate state (only in `Multiple` selection mode). */
  lazy val indeterminate: HtmlAttr[Boolean] = htmlAttr("indeterminate", BooleanAsAttrPresenceCodec)

  @deprecated("intermediate was a wrong name (it never worked), use indeterminate instead", since = "2.27.0")
  def intermediate: HtmlAttr[Boolean] = indeterminate

  /** Role of the item. If not set, it is inherited from the parent tree.
    *
    * @since UI5 2.23.0
    */
  lazy val accessibleRole: HtmlAttr[ListItemAccessibleRole] = ListItemAccessibleRole.asHtmlAttr("accessible-role")

  /** Additional accessibility attributes. Supported fields: `hasPopup`, `ariaSetsize` and `ariaPosinset`. */
  lazy val accessibilityAttributes: HtmlProp[AccessibilityAttributes, AccessibilityAttributes] =
    AccessibilityAttributes.asHtmlProp

  object slots {
    val image: Slot        = Slot("image")
    val deleteButton: Slot = Slot("deleteButton")
  }

  object events {
    val onDetailClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("detail-click")

    @deprecated("detailClick has been renamed to onDetailClick", since = "2.27.0")
    def detailClick: EventProp[EventWithPreciseTarget[Ref]] = onDetailClick

    /** Fired when the item is activated (by mouse or keyboard), since UI5 2.23.0. */
    val onClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ListItemClickDetail]] = new EventProp("click")
  }

  def item: TreeItem.type             = this
  def customItem: TreeItemCustom.type = TreeItemCustom

}
