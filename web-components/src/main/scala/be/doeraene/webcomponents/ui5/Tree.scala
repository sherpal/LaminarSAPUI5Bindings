package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.ListMode
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.{HtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, JSName}
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.eventtypes.HasItem
import be.doeraene.webcomponents.WebComponent

/** The ui5-tree component provides a tree structure for displaying data in a hierarchy.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Tree/">the doc</a> for more information.
  */
object Tree extends WebComponent with HasAccessibleName {

  //noinspection ScalaUnusedSymbol
  @js.native
  trait RawElement extends js.Object {
    def walk(callback: js.Function2[dom.HTMLElement, Int, Unit]): Unit = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/Tree.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-tree")

  lazy val footerText: HtmlAttr[String] = customHtmlAttr("footer-text", StringAsIsCodec)
  lazy val headerText: HtmlAttr[String] = customHtmlAttr("header-text", StringAsIsCodec)
  lazy val noDataText: HtmlAttr[String] = customHtmlAttr("no-data-text", StringAsIsCodec)

  lazy val mode: HtmlAttr[ListMode] = customHtmlAttr("mode", ListMode.AsStringCodec)

  object events {
    val onItemClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasItem[TreeItem.Ref]]] = new EventProp(
      "item-click"
    )
    val onItemDelete: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasItem[TreeItem.Ref]]] = new EventProp(
      "item-delete"
    )
    val onItemMouseOut: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasItem[TreeItem.Ref]]] = new EventProp(
      "item-mouseout"
    )
    val onItemMouseOver: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasItem[TreeItem.Ref]]] = new EventProp(
      "item-mouseover"
    )
    val onItemToggle: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasItem[TreeItem.Ref]]] = new EventProp(
      "item-toggle"
    )

    @js.native
    trait SelectionChangeDetail extends js.Object {
      @JSName("selectedItems")
      def selectedItemsJS: js.Array[TreeItem.Ref] = js.native
      @JSName("previouslySelectedItems")
      def previouslySelectedItemsJS: js.Array[TreeItem.Ref] = js.native
    }

    object SelectionChangeDetail {
      extension (detail: SelectionChangeDetail)
        def selectedItems: List[TreeItem.Ref]           = detail.selectedItemsJS.toList
        def previouslySelectedItems: List[TreeItem.Ref] = detail.previouslySelectedItemsJS.toList
    }

    val onSelectionChange: EventProp[EventWithPreciseTarget[Ref] & HasDetail[SelectionChangeDetail]] = new EventProp(
      "selection-change"
    )
  }

  object slots {
    val header: Slot = new Slot("header")
  }

  

  def item: TreeItem.type = TreeItem

}
