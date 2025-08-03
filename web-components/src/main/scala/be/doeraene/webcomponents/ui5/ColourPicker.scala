package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.ButtonDesign
import be.doeraene.webcomponents.ui5.configkeys.ColourScheme
import be.doeraene.webcomponents.ui5.configkeys.IconName
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.internal.Slot
import be.doeraene.webcomponents.ui5.scaladsl.colour.Colour
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.annotation.JSName

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

    @JSName("value")
    def valueJS: String = js.native
  }

  object RawElement {
    extension (rawElement: RawElement)
      @deprecated("color attribute has been renamed to value", since = "2.0.0")
      def colourJS: String = rawElement.valueJS

      /** The current colour as [[Colour]] instance. English UK spelling for consistency. */
      @deprecated("colour attribute has been renamed to value", since = "2.0.0")
      def colour: Colour = rawElement.value

      /** The current colour as [[Colour]] instance. English UK spelling for consistency. */
      def value: Colour = Colour.fromString(rawElement.valueJS)

  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/ColorPicker.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-color-picker")

  @deprecated("colour attribute has been renamed to value", since = "2.0.0")
  def colour: HtmlAttr[Colour] = value

  lazy val value: HtmlAttr[Colour]       = htmlAttr("value", Colour.AsStringCodec)
  lazy val simplified: HtmlAttr[Boolean] = htmlAttr("simplified", BooleanAsAttrPresenceCodec)

  object slots {}

  object events {
    val onChange: EventProp[EventWithPreciseTarget[dom.HTMLElement & RawElement]] = new EventProp("change")
  }

}
