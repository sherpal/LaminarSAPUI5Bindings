package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.MediaGalleryItemLayout
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.{HtmlAttr}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** The ui5-media-gallery-item web component represents the items displayed in the ui5-media-gallery web component.
  *
  * Note: ui5-media-gallery-item is not supported when used outside of ui5-media-gallery.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/MediaGallery/">the doc</a> for more
  *   information.
  */
object MediaGalleryItem extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def selected: Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/MediaGalleryItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = htmlTag("ui5-media-gallery-item")

  lazy val disabled: HtmlAttr[Boolean] = htmlAttr("disabled", BooleanAsAttrPresenceCodec)

  lazy val layout: HtmlAttr[MediaGalleryItemLayout] =
    htmlAttr("layout", MediaGalleryItemLayout.AsStringCodec)

  lazy val selected: HtmlAttr[Boolean] = htmlAttr("selected", BooleanAsAttrPresenceCodec)

  object slots {
    val thumbnail: Slot = new Slot("thumbnail")
  }

  object events {}

  

}
