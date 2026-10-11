package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.eventtypes.{EventWithPreciseTarget, HasDetail}
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** The ui5-option-custom component defines a custom content (its children) of an option in the ui5-select.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/OptionCustom/">the doc</a> for more information.
  */
object SelectOptionCustom extends WebComponent with HasValue {

  @js.native
  trait RawElement extends js.Object {
    def value: String | Unit = js.native
  }

  object RawElement {
    extension (element: RawElement) {
      def maybeValue: Option[String] = element.value match {
        case value: String => Some(value)
        case _: Unit       => None
      }
    }
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/OptionCustom.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-option-custom")

  lazy val tooltip: HtmlAttr[String]     = htmlAttr("tooltip", StringAsIsCodec)
  lazy val selected: HtmlAttr[Boolean]   = htmlAttr("selected", BooleanAsAttrPresenceCodec)
  lazy val displayText: HtmlAttr[String] = htmlAttr("display-text", StringAsIsCodec)

  object events {
    trait ClickDetail extends js.Object {
      def originalEvent: dom.Event
    }

    /** Fired when the option is clicked (since UI5 2.23.0). */
    val onClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ClickDetail]] = new EventProp("click")
  }

}
