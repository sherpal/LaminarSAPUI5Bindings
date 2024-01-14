package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, IconName}
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.ValueState
import be.doeraene.webcomponents.ui5.configkeys.ListItemType
import be.doeraene.webcomponents.ui5.internal.Slot
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget

/** The ui5-tree-item-custom represents a node in a tree structure, shown as a ui5-list.
  *
  * This is the item to use inside a ui5-tree. You can represent an arbitrary tree structure by recursively nesting tree
  * items. You can use this item to put any custom content inside the tree item.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Tree/">the doc</a> for more information.
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

  type Ref = dom.html.Element with RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-tree-item-custom")

  lazy val expanded: HtmlAttr[Boolean]     = htmlAttr[Boolean]("expanded", BooleanAsAttrPresenceCodec)
  lazy val hasChildren: HtmlAttr[Boolean]  = htmlAttr[Boolean]("has-children", BooleanAsAttrPresenceCodec)
  lazy val intermediate: HtmlAttr[Boolean] = htmlAttr[Boolean]("intermediate", BooleanAsAttrPresenceCodec)
  lazy val selected: HtmlAttr[Boolean]     = htmlAttr[Boolean]("selected", BooleanAsAttrPresenceCodec)
  lazy val hideSelectionElement: HtmlAttr[Boolean] =
    htmlAttr[Boolean]("hide-selection-element", BooleanAsAttrPresenceCodec)
  lazy val additionalTextState: HtmlAttr[ValueState] =
    htmlAttr[ValueState]("additional-text-state", ValueState.AsStringCodec)
  lazy val navigated: HtmlAttr[Boolean]     = htmlAttr[Boolean]("navigated", BooleanAsAttrPresenceCodec)
  lazy val itemType: HtmlAttr[ListItemType] = htmlAttr[ListItemType]("type", ListItemType.AsStringCodec)

  object slots {
    val content: Slot      = Slot("content")
    val deleteButton: Slot = Slot("deleteButton")
  }

  object events {
    val detailClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("detail-click")
  }

}
