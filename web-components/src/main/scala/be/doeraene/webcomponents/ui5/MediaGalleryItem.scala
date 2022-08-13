package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.MediaGalleryItemLayout
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-media-gallery-item web component represents the items displayed in the ui5-media-gallery web component.
  *
  * Note: ui5-media-gallery-item is not supported when used outside of ui5-media-gallery.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/MediaGallery/">the doc</a> for more
  *   information.
  */
object MediaGalleryItem {

  @js.native
  trait RawElement extends js.Object {
    def selected: Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/MediaGalleryItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = MediaGalleryItem.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-media-gallery-item")

  val id: ReactiveProp[String, String] = idAttr

  val disabled: ReactiveHtmlAttr[Boolean] = customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)

  val layout: ReactiveHtmlAttr[MediaGalleryItemLayout] = customHtmlAttr("layout", MediaGalleryItemLayout.AsStringCodec)

  val selected: ReactiveHtmlAttr[Boolean] = customHtmlAttr("selected", BooleanAsAttrPresenceCodec)

  object slots {
    val thumbnail: Slot = new Slot("thumbnail")
  }

  object events {}

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(MediaGalleryItem)): _*)

}
