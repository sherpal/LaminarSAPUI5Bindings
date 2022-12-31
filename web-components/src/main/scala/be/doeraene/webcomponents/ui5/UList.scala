package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ListGrowingMode, ListMode, ListSeparator}
import be.doeraene.webcomponents.ui5.internal.Slot
import be.doeraene.webcomponents.ui5.eventtypes.*
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
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
  object RawImport extends WebComponent.WithMetadata

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-list")

  lazy val accessibleRole: ReactiveHtmlAttr[String]    = customHtmlAttr("accessible-role", StringAsIsCodec)
  lazy val busy: ReactiveHtmlAttr[Boolean]             = customHtmlAttr("busy", BooleanAsAttrPresenceCodec)
  lazy val busyDelay: ReactiveHtmlAttr[FiniteDuration] = customHtmlAttr("busy-delay", FiniteDurationCodec)
  lazy val footerText: ReactiveHtmlAttr[String]        = customHtmlAttr("footer-text", StringAsIsCodec)
  lazy val growing: ReactiveHtmlAttr[ListGrowingMode]  = customHtmlAttr("growing", ListGrowingMode.AsStringCodec)
  lazy val headerText: ReactiveHtmlAttr[String]        = customHtmlAttr("header-text", StringAsIsCodec)
  lazy val indent: ReactiveHtmlAttr[Boolean]           = customHtmlAttr("indent", BooleanAsAttrPresenceCodec)
  lazy val mode: ReactiveHtmlAttr[ListMode]            = customHtmlAttr("mode", ListMode.AsStringCodec)
  lazy val noDataText: ReactiveHtmlAttr[String]        = customHtmlAttr("no-data-text", StringAsIsCodec)
  lazy val separators: ReactiveHtmlAttr[ListSeparator] = customHtmlAttr("separators", ListSeparator.AsStringCodec)

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
