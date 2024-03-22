package be.doeraene.webcomponents.ui5

import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** The ui5-option component defines the content of an option in the ui5-select.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Select/">the doc</a> for more information.
  */
object SelectOption extends WebComponent with HasIcon with HasAdditionalText with HasValue {

  @js.native
  trait RawElement extends js.Object {
    def value: String | Unit = js.native
  }

  object RawElement {
    implicit final class RichRawElement(val element: RawElement) extends AnyVal {
      def maybeValue: Option[String] = element.value match {
        case value: String => Some(value)
        case _: Unit       => None
      }
    }
  }

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-option")

  lazy val disabled: HtmlAttr[Boolean] = htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val selected: HtmlAttr[Boolean] = htmlAttr("selected", BooleanAsAttrPresenceCodec)

}
