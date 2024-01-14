package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ListGrowingMode, ListMode, ListSeparator}
import be.doeraene.webcomponents.ui5.internal.Slot
import be.doeraene.webcomponents.ui5.eventtypes.*
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, JSName}
import scala.concurrent.duration.FiniteDuration
import be.doeraene.webcomponents.WebComponent

/** The ui5-list component allows displaying a list of items, advanced keyboard handling support for navigating between
  * items, and predefined modes to improve the development efficiency.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/List/">the doc</a> for more information.
  */
object UList extends WebComponent with HasAccessibleName {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/List.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-list")

  lazy val accessibleRole: HtmlAttr[String]    = htmlAttr("accessible-role", StringAsIsCodec)
  lazy val busy: HtmlAttr[Boolean]             = htmlAttr("busy", BooleanAsAttrPresenceCodec)
  lazy val busyDelay: HtmlAttr[FiniteDuration] = htmlAttr("busy-delay", FiniteDurationCodec)
  lazy val footerText: HtmlAttr[String]        = htmlAttr("footer-text", StringAsIsCodec)
  lazy val growing: HtmlAttr[ListGrowingMode]  = htmlAttr("growing", ListGrowingMode.AsStringCodec)
  lazy val headerText: HtmlAttr[String]        = htmlAttr("header-text", StringAsIsCodec)
  lazy val indent: HtmlAttr[Boolean]           = htmlAttr("indent", BooleanAsAttrPresenceCodec)
  lazy val mode: HtmlAttr[ListMode]            = htmlAttr("mode", ListMode.AsStringCodec)
  lazy val noDataText: HtmlAttr[String]        = htmlAttr("no-data-text", StringAsIsCodec)
  lazy val separators: HtmlAttr[ListSeparator] = htmlAttr("separators", ListSeparator.AsStringCodec)

  object events {
    val onItemClick  = new EventProp[EventWithPreciseTarget[Ref] with HasDetail[HasItem[item.Ref]]]("item-click")
    val onItemClose  = new EventProp[EventWithPreciseTarget[Ref] with HasDetail[HasItem[item.Ref]]]("item-close")
    val onItemDelete = new EventProp[EventWithPreciseTarget[Ref] with HasDetail[HasItem[item.Ref]]]("item-delete")
    val onItemToggle = new EventProp[EventWithPreciseTarget[Ref] with HasDetail[HasItem[item.Ref]]]("item-toggle")
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
      new EventProp[EventWithPreciseTarget[Ref] with HasDetail[SelectionChangeDetail]]("selection-change")
  }

  object slots {
    val header: Slot = new Slot("header")
  }

  @deprecated("Li was a badly designed name. Use `item` instead", "15/08/2022")
  def Li: ListItem.type = ListItem

  def item: ListItem.type             = ListItem
  def customItem: CustomListItem.type = CustomListItem
  def group: UListGroupHeader.type    = UListGroupHeader

  def notificationItem: NotificationListItem.type       = NotificationListItem
  def notificationGroup: NotificationListGroupItem.type = NotificationListGroupItem

}
