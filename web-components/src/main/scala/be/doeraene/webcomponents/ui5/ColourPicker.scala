package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.internal.Slot
import be.doeraene.webcomponents.ui5.scaladsl.colour.Colour
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, JSName}
import be.doeraene.webcomponents.WebComponent

/** The ui5-badge is a small non-interactive component which contains text information and color chosen from a list of
  * predefined color schemes. It serves the purpose to attract the user attention to some piece of information (state,
  * quantity, condition, etc.).
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/ColorPicker/">the doc</a> for more
  *   information.
  */
object ColourPicker extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    @JSName("color")
    def colourJS: String = js.native
  }

  object RawElement {
    extension (rawElement: RawElement)
      /** The current colour as [[Colour]] instance. English UK spelling for consistency. */
      def colour: Colour = Colour.fromString(rawElement.colourJS)
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/ColorPicker.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = htmlTag("ui5-color-picker")

  lazy val colour: HtmlAttr[Colour] = htmlAttr("color", Colour.AsStringCodec)

  object slots {}

  object events {
    val onChange: EventProp[EventWithPreciseTarget[dom.HTMLElement with RawElement]] = new EventProp("change")
  }

}
