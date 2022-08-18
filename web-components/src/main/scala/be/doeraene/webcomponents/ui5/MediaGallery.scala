package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{
  MediaGalleryLayout,
  MediaGalleryMenuHorizontalAlign,
  MediaGalleryMenuVerticalAlign
}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.eventtypes.HasItem
import be.doeraene.webcomponents.WebComponent

/** The ui5-media-gallery component allows the user to browse through multimedia items. Currently, the supported items
  * are images and videos. The items should be defined using the ui5-media-gallery-item component. The items are
  * initially displayed as thumbnails. When the user selects a thumbnail, the corresponding item is displayed in larger
  * size.
  *
  * The component is responsive by default and adjusts the position of the menu with respect to viewport size, but the
  * application is able to further customize the layout via the provided API.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/MediaGallery/">the doc</a> for more
  *   information.
  */
object MediaGallery extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/MediaGallery.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-media-gallery")

  lazy val interactiveDisplayArea: ReactiveHtmlAttr[Boolean] =
    customHtmlAttr("interactive-display-area", BooleanAsAttrPresenceCodec)

  lazy val layout: ReactiveHtmlAttr[MediaGalleryLayout] =
    customHtmlAttr("layout", MediaGalleryLayout.AsStringCodec)

  lazy val menuHorizontalAlign: ReactiveHtmlAttr[MediaGalleryMenuHorizontalAlign] =
    customHtmlAttr("menu-horizontal-align", MediaGalleryMenuHorizontalAlign.AsStringCodec)

  lazy val menuVerticalAlign: ReactiveHtmlAttr[MediaGalleryMenuVerticalAlign] =
    customHtmlAttr("menu-vertical-align", MediaGalleryMenuVerticalAlign.AsStringCodec)

  lazy val showAllThumbnails: ReactiveHtmlAttr[Boolean] =
    customHtmlAttr("show-all-thumbnails", BooleanAsAttrPresenceCodec)

  object slots {}

  object events {
    val onDisplayAreaClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("display-area-click")
    val onOverflowClick: EventProp[EventWithPreciseTarget[Ref]]    = new EventProp("overflow-click")
    val onSelectionChange: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasItem[item.Ref]]] = new EventProp(
      "selection-change"
    )
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(MediaGallery)): _*)

  def item: MediaGalleryItem.type = MediaGalleryItem

}
