package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.{HtmlAttr}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, JSName}
import be.doeraene.webcomponents.ui5.configkeys.ListMode
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.eventtypes.HasItem
import be.doeraene.webcomponents.WebComponent

/** This component allows you to represent files before uploading them to a server, with the help of
  * ui5-upload-collection-item. It also allows you to show already uploaded files.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/UploadCollection/">the doc</a> for more
  *   information.
  */
object UploadCollection extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/UploadCollection.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = htmlTag("ui5-upload-collection")

  lazy val accessibleName: HtmlAttr[String] = htmlAttr("accessible-name", StringAsIsCodec)

  lazy val hideDragOverlay: HtmlAttr[Boolean] = htmlAttr("hide-drag-overlay", BooleanAsAttrPresenceCodec)

  lazy val mode: HtmlAttr[ListMode] = htmlAttr("mode", ListMode.AsStringCodec)

  lazy val noDataDescription: HtmlAttr[String] = htmlAttr("no-data-description", StringAsIsCodec)

  lazy val noDataText: HtmlAttr[String] = htmlAttr("no-data-text", StringAsIsCodec)

  object slots {
    val header: Slot = new Slot("header")
  }

  object events {

    trait HasDataTransfer extends js.Object {
      def dataTransfer: dom.DataTransfer
    }

    val onDrop: EventProp[EventWithPreciseTarget[Ref] & HasDataTransfer] = new EventProp("drop")
    val onItemDelete: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasItem[dom.HTMLElement]]] = new EventProp(
      "item-delete"
    )

    trait SelectionChangeInfo extends js.Object {
      @JSName("selectedItems")
      def selectedItemsJS: Array[dom.HTMLElement]
    }

    object SelectionChangeInfo {
      extension (info: SelectionChangeInfo) def selectedItems: List[dom.HTMLElement] = info.selectedItemsJS.toList
    }

    val onSelectionChange: EventProp[EventWithPreciseTarget[Ref] & HasDetail[SelectionChangeInfo]] = new EventProp(
      "selection-change"
    )
  }

  

  def item: UploadCollectionItem.type = UploadCollectionItem

}
