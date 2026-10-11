package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.internal.Slot
import be.doeraene.webcomponents.ui5.scaladsl.colour.Colour
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail

/** The ui5-color-palette-item component represents a color in the the ui5-color-palette.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/ColorPaletteItem/">the doc</a> for more information.
  */
object ColourPaletteItem extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def selected: Boolean

    def value: String
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/ColorPaletteItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-color-palette-item")

  lazy val value: HtmlAttr[Colour]     = htmlAttr("value", Colour.AsStringCodec)
  lazy val selected: HtmlAttr[Boolean] = htmlAttr("selected", BooleanAsAttrPresenceCodec)

  /** Tooltip of the item. When not set, the colour value is used as the tooltip. */
  lazy val tooltip: HtmlAttr[String] = htmlAttr("tooltip", StringAsIsCodec)

  object slots {}

  object events {
    trait ClickEventDetail extends js.Object {
      def originalEvent: dom.Event
    }

    /** Fired when the item is activated (click, or Space/Enter key). */
    val onClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ClickEventDetail]] = new EventProp("click")
  }

}
