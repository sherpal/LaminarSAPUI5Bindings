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

}
