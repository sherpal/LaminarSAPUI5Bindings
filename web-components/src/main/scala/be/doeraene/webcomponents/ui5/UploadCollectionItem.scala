package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, JSName}
import be.doeraene.webcomponents.ui5.configkeys.{ListItemAccessibleRole, ListItemType, UploadState, ValueState}
import be.doeraene.webcomponents.ui5.datatypes.AccessibilityAttributes
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.eventtypes.HasItem
import com.raquo.laminar.codecs.Codec
import com.raquo.laminar.codecs.IntAsStringCodec
import com.raquo.laminar.keys.HtmlProp
import be.doeraene.webcomponents.WebComponent

/** A component to be used within the ui5-upload-collection, representing a (to be) uploaded file.
  *
  * The default slot (children) holds the description of the item, shown below the file name.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/UploadCollectionItem/">the doc</a> for more
  *   information.
  */
object UploadCollectionItem extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    @JSName("file")
    def fileJS: dom.File | Null = js.native

    def progress: Int = js.native

    def fileName: String = js.native
  }

  object RawElement {
    extension (element: RawElement) def file: Option[dom.File] = Option(element.fileJS)
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/UploadCollectionItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-upload-collection-item")

  lazy val disableDeleteButton: HtmlAttr[Boolean] =
    htmlAttr("disable-delete-button", BooleanAsAttrPresenceCodec)

  lazy val hideDeleteButton: HtmlAttr[Boolean] =
    htmlAttr("hide-delete-button", BooleanAsAttrPresenceCodec)

  lazy val fileName: HtmlAttr[String] =
    htmlAttr("file-name", StringAsIsCodec)

  lazy val fileNameClickable: HtmlAttr[Boolean] =
    htmlAttr("file-name-clickable", BooleanAsAttrPresenceCodec)

  lazy val hideRetryButton: HtmlAttr[Boolean] =
    htmlAttr("hide-retry-button", BooleanAsAttrPresenceCodec)

  lazy val hideTerminateButton: HtmlAttr[Boolean] =
    htmlAttr("hide-terminate-button", BooleanAsAttrPresenceCodec)

  lazy val progress: HtmlAttr[Int] = htmlAttr("progress", IntAsStringCodec)

  lazy val uploadState: HtmlAttr[UploadState] =
    htmlAttr("upload-state", UploadState.AsStringCodec)

  /** The `File` associated with this item (this is a property, not an attribute). */
  lazy val file: HtmlProp[Option[dom.File], dom.File | Null] = htmlProp("file", fileCodec)

  // Inherited from ListItem

  /** Visual indication and behavior of the item (UI5 default: `Active`). */
  lazy val tpe: HtmlAttr[ListItemType] = ListItemType.asHtmlAttr("type")

  lazy val selected: HtmlAttr[Boolean] = htmlAttr("selected", BooleanAsAttrPresenceCodec)

  /** If set, a navigation indicator is displayed at the end of the item. */
  lazy val navigated: HtmlAttr[Boolean] = htmlAttr("navigated", BooleanAsAttrPresenceCodec)

  /** Highlight state of the item (`None`, `Positive`, `Critical`, `Negative` or `Information`). */
  lazy val highlight: HtmlAttr[ValueState] = ValueState.asHtmlAttr("highlight")

  lazy val tooltip: HtmlAttr[String] = htmlAttr("tooltip", StringAsIsCodec)

  /** Role of the item. If not set, it is inherited from the parent list (since UI5 2.23). */
  lazy val accessibleRole: HtmlAttr[ListItemAccessibleRole] = ListItemAccessibleRole.asHtmlAttr("accessible-role")

  /** Additional accessibility attributes. Supported fields: `hasPopup`, `ariaSetsize` and `ariaPosinset`. */
  lazy val accessibilityAttributes: HtmlProp[AccessibilityAttributes, AccessibilityAttributes] =
    AccessibilityAttributes.asHtmlProp

  object slots {
    val thumbnail: Slot = new Slot("thumbnail")

    /** Custom delete button (a [[Button]]), to be used in the `Delete` selection mode of the collection. */
    val deleteButton: Slot = new Slot("deleteButton")
  }

  object events {
    trait ClickDetail extends js.Object {
      def originalEvent: dom.Event
    }

    /** Fired when the item is activated by mouse/touch or keyboard (since UI5 2.23). */
    val onClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ClickDetail]] = new EventProp("click")

    /** Fired when the user clicks on the detail button when `type` is `Detail`. */
    val onDetailClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("detail-click")

    val onFileNameClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("file-name-click")
    val onRename: EventProp[EventWithPreciseTarget[Ref]]        = new EventProp("rename")
    val onRetry: EventProp[EventWithPreciseTarget[Ref]]         = new EventProp("retry")
    val onTerminate: EventProp[EventWithPreciseTarget[Ref]]     = new EventProp("terminate")
  }

  private object fileCodec extends Codec[Option[dom.File], dom.File | Null] {
    def decode(domValue: dom.File | Null): Option[dom.File] = Option(domValue.asInstanceOf[dom.File])
    def encode(scalaValue: Option[dom.File]): dom.File | Null = scalaValue.orNull
  }

}
