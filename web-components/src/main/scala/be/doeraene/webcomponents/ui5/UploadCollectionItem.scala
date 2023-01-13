package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.{HtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, JSName}
import be.doeraene.webcomponents.ui5.configkeys.{ListMode, UploadState}
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.eventtypes.HasItem
import com.raquo.laminar.codecs.IntAsStringCodec
import be.doeraene.webcomponents.WebComponent

/** This component allows you to represent files before uploading them to a server, with the help of
  * ui5-upload-collection-item. It also allows you to show already uploaded files.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/UploadCollectionItem/">the doc</a> for more
  *   information.
  */
object UploadCollectionItem extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
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

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-upload-collection-item")

  lazy val disableDeleteButton: HtmlAttr[Boolean] =
    customHtmlAttr("disable-delete-button", BooleanAsAttrPresenceCodec)

  lazy val fileName: HtmlAttr[String] =
    customHtmlAttr("file-name", StringAsIsCodec)

  lazy val fileNameClickable: HtmlAttr[Boolean] =
    customHtmlAttr("file-name-clickable", BooleanAsAttrPresenceCodec)

  lazy val hideRetryButton: HtmlAttr[Boolean] =
    customHtmlAttr("hide-retry-button", BooleanAsAttrPresenceCodec)

  lazy val hideTerminateButton: HtmlAttr[Boolean] =
    customHtmlAttr("hide-terminate-button", BooleanAsAttrPresenceCodec)

  lazy val progress: HtmlAttr[Int] = customHtmlAttr("progress", IntAsStringCodec)

  lazy val uploadState: HtmlAttr[UploadState] =
    customHtmlAttr("upload-state", UploadState.AsStringCodec)

  object slots {
    val thumbnail: Slot = new Slot("thumbnail")
  }

  object events {
    val onFileNameClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("file-name-click")
    val onRename: EventProp[EventWithPreciseTarget[Ref]]        = new EventProp("rename")
    val onRetry: EventProp[EventWithPreciseTarget[Ref]]         = new EventProp("retry")
    val onTerminate: EventProp[EventWithPreciseTarget[Ref]]     = new EventProp("terminate")
  }

  

}
