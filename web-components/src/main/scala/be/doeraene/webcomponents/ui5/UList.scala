package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.ListGrowingMode
import be.doeraene.webcomponents.ui5.configkeys.ListMode
import be.doeraene.webcomponents.ui5.configkeys.ListSeparator
import be.doeraene.webcomponents.ui5.eventtypes.*
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.concurrent.duration.FiniteDuration
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.annotation.JSName

/** The ui5-list component allows displaying a list of items, advanced keyboard handling support for navigating between
  * items, and predefined modes to improve the development efficiency.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/List/">the doc</a> for more information.
  */
object UList extends WebComponent with HasAccessibleName {

  @js.native
  trait RawElement extends js.Object {
    @JSName("listItems")
    def listItemsJS: js.Array[item.Ref] = js.native
  }

  object RawElement extends js.Object {
    extension (rawElement: RawElement) {
      def listItems: List[item.Ref] = rawElement.listItemsJS.toList
    }
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/List.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-list")

  lazy val accessibleRole: HtmlAttr[String]        = htmlAttr("accessible-role", StringAsIsCodec)
  lazy val loading: HtmlAttr[Boolean]              = htmlAttr("loading", BooleanAsAttrPresenceCodec)
  lazy val loadingDelay: HtmlAttr[FiniteDuration]  = htmlAttr("loading-delay", FiniteDurationCodec)
  lazy val footerText: HtmlAttr[String]            = htmlAttr("footer-text", StringAsIsCodec)
  lazy val growing: HtmlAttr[ListGrowingMode]      = htmlAttr("growing", ListGrowingMode.AsStringCodec)
  lazy val headerText: HtmlAttr[String]            = htmlAttr("header-text", StringAsIsCodec)
  lazy val indent: HtmlAttr[Boolean]               = htmlAttr("indent", BooleanAsAttrPresenceCodec)
  lazy val selectionMode: HtmlAttr[ListMode]       = htmlAttr("selection-mode", ListMode.AsStringCodec)
  lazy val noDataText: HtmlAttr[String]            = htmlAttr("no-data-text", StringAsIsCodec)
  lazy val separators: HtmlAttr[ListSeparator]     = htmlAttr("separators", ListSeparator.AsStringCodec)
  lazy val accessibleDescription: HtmlAttr[String] = htmlAttr("accessible-description", StringAsIsCodec)

  @deprecated("busy property has been renamed to loading", since = "2.0.0")
  def busy: HtmlAttr[Boolean] = loading
  @deprecated("busyDelay property has been renamed to loadingDelay", since = "2.0.0")
  def busyDelay: HtmlAttr[FiniteDuration] = loadingDelay
  @deprecated("mode has been renamed to selectionMode")
  def mode: HtmlAttr[ListMode] = selectionMode

  object events {
    val onItemClick  = new EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasItem[item.Ref]]]("item-click")
    val onItemClose  = new EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasItem[item.Ref]]]("item-close")
    val onItemDelete = new EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasItem[item.Ref]]]("item-delete")
    val onItemToggle = new EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasItem[item.Ref]]]("item-toggle")
    val onLoadMore   = new EventProp[EventWithPreciseTarget[Ref]]("load-more")

    @js.native
    trait SelectionChangeDetail extends js.Object {
      @JSName("selectedItems")
      def selectedItemsJS: js.Array[item.Ref] = js.native

      @JSName("previouslySelectedItems")
      def previouslySelectedItemsJS: js.Array[item.Ref] = js.native
    }

    object SelectionChangeDetail {
      extension (detail: SelectionChangeDetail)
        def selectedItems: List[item.Ref]           = detail.selectedItemsJS.toList
        def previouslySelectedItems: List[item.Ref] = detail.previouslySelectedItemsJS.toList

        /** Returns the first selected item when it exists (useful in [[ListMode.SingleSelect]]) */
        def maybeSelectedItem: Option[item.Ref] = detail.selectedItemsJS.headOption

        /** Returns the first previously selected item when it exists (useful in [[ListMode.SingleSelect]])) */
        def maybePreviouslySelectedItem: Option[item.Ref] = detail.previouslySelectedItemsJS.headOption
    }

    val onSelectionChange =
      new EventProp[EventWithPreciseTarget[Ref] & HasDetail[SelectionChangeDetail]]("selection-change")

    lazy val onMove: EventProp[EventWithPreciseTarget[Ref] & HasDetail[MoveEventDetail[item.Ref]]] = new EventProp(
      "move"
    )
    lazy val onMoveOver: EventProp[EventWithPreciseTarget[Ref] & HasDetail[MoveEventDetail[item.Ref]]] = new EventProp(
      "move-over"
    )
  }

  object slots {
    val header: Slot = new Slot("header")
  }

  @deprecated("Li was a badly designed name. Use `item` instead", "15/08/2022")
  def Li: ListItem.type = ListItem

  def item: ListItem.type             = ListItem
  def customItem: CustomListItem.type = CustomListItem
  def grouped                         = UListGroup

  @scala.annotation.compileTimeOnly(
    "group has been replaced by grouped, and the structure of your list items now needs to be nested."
  )
  def group = ???

  @scala.annotation.compileTimeOnly("notificationItem has been removed from UList. Use NotificationList instead")
  def notificationItem: NotificationListItem.type = NotificationListItem
  @scala.annotation.compileTimeOnly("notificationGroup has been removed from UList. Use NotificationList instead")
  def notificationGroup: NotificationListGroupItem.type = NotificationListGroupItem

}
