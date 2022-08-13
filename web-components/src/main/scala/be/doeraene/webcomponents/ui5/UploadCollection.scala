package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, JSName}
import be.doeraene.webcomponents.ui5.configkeys.ListMode
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.eventtypes.HasItem

/** This component allows you to represent files before uploading them to a server, with the help of
  * ui5-upload-collection-item. It also allows you to show already uploaded files.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/UploadCollection/">the doc</a> for more
  *   information.
  */
object UploadCollection {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/UploadCollection.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = UploadCollection.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-upload-collection")

  val id: ReactiveProp[String, String] = idAttr

  val accessibleName: ReactiveHtmlAttr[String] = customHtmlAttr("accessible-name", StringAsIsCodec)

  val hideDragOverlay: ReactiveHtmlAttr[Boolean] = customHtmlAttr("hide-drag-overlay", BooleanAsAttrPresenceCodec)

  val mode: ReactiveHtmlAttr[ListMode] = customHtmlAttr("mode", ListMode.AsStringCodec)

  val noDataDescription: ReactiveHtmlAttr[String] = customHtmlAttr("no-data-description", StringAsIsCodec)

  val noDataText: ReactiveHtmlAttr[String] = customHtmlAttr("no-data-text", StringAsIsCodec)

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

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(UploadCollection)): _*)

  def item: UploadCollectionItem.type = UploadCollectionItem

}