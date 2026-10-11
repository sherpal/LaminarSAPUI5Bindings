package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.{ListItemAccessibleRole, ListItemType, ValueState}
import be.doeraene.webcomponents.ui5.datatypes.AccessibilityAttributes
import be.doeraene.webcomponents.ui5.eventtypes.{EventWithPreciseTarget, HasDetail, ListItemClickDetail}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-tree-item-custom represents a node in a tree structure, shown as a ui5-list.
  *
  * This is the item to use inside a ui5-tree. You can represent an arbitrary tree structure by recursively nesting tree
  * items. You can use this item to put any custom content inside the tree item.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/TreeItemCustom/">the doc</a> for more information.
  */
object TreeItemCustom extends WebComponent with HasIcon with HasAccessibleName {

  @js.native
  trait RawElement extends js.Object {
    def toggle(): Unit = js.native

    def hasChildren: Boolean = js.native

    def hideSelectionElement: Boolean = js.native

    def selected: Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/TreeItemCustom.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-tree-item-custom")

  lazy val expanded: HtmlAttr[Boolean]    = htmlAttr[Boolean]("expanded", BooleanAsAttrPresenceCodec)
  lazy val hasChildren: HtmlAttr[Boolean] = htmlAttr[Boolean]("has-children", BooleanAsAttrPresenceCodec)
  lazy val selected: HtmlAttr[Boolean]    = htmlAttr[Boolean]("selected", BooleanAsAttrPresenceCodec)
  lazy val hideSelectionElement: HtmlAttr[Boolean] =
    htmlAttr[Boolean]("hide-selection-element", BooleanAsAttrPresenceCodec)
  lazy val additionalTextState: HtmlAttr[ValueState] =
    htmlAttr[ValueState]("additional-text-state", ValueState.AsStringCodec)
  lazy val navigated: HtmlAttr[Boolean]    = htmlAttr[Boolean]("navigated", BooleanAsAttrPresenceCodec)
  lazy val tpe: HtmlAttr[ListItemType]     = ListItemType.asHtmlAttr("type")
  lazy val highlight: HtmlAttr[ValueState] = ValueState.asHtmlAttr("highlight")
  lazy val movable: HtmlAttr[Boolean]      = htmlAttr("movable", BooleanAsAttrPresenceCodec)
  lazy val tooltip: HtmlAttr[String]       = htmlAttr("tooltip", StringAsIsCodec)

  /** Whether the selection of the item is in an indeterminate state (only in `Multiple` selection mode). */
  lazy val indeterminate: HtmlAttr[Boolean] = htmlAttr("indeterminate", BooleanAsAttrPresenceCodec)

  @deprecated("intermediate was a wrong name (it never worked), use indeterminate instead", since = "2.27.0")
  def intermediate: HtmlAttr[Boolean] = indeterminate

  @deprecated("itemType has been renamed to tpe, for consistency with the other items", since = "2.27.0")
  def itemType: HtmlAttr[ListItemType] = tpe

  /** Role of the item. If not set, it is inherited from the parent tree.
    *
    * @since UI5 2.23.0
    */
  lazy val accessibleRole: HtmlAttr[ListItemAccessibleRole] = ListItemAccessibleRole.asHtmlAttr("accessible-role")

  /** Additional accessibility attributes. Supported fields: `hasPopup`, `ariaSetsize` and `ariaPosinset`. */
  lazy val accessibilityAttributes: HtmlProp[AccessibilityAttributes, AccessibilityAttributes] =
    AccessibilityAttributes.asHtmlProp

  object slots {
    val content: Slot      = Slot("content")
    val deleteButton: Slot = Slot("deleteButton")

    /** @since UI5 2.10.0 */
    val image: Slot = Slot("image")
  }

  object events {
    val onDetailClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("detail-click")

    @deprecated("detailClick has been renamed to onDetailClick", since = "2.27.0")
    def detailClick: EventProp[EventWithPreciseTarget[Ref]] = onDetailClick

    /** Fired when the item is activated (by mouse or keyboard), since UI5 2.23.0. */
    val onClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ListItemClickDetail]] = new EventProp("click")
  }

  def item: TreeItem.type             = TreeItem
  def customItem: TreeItemCustom.type = this

}
