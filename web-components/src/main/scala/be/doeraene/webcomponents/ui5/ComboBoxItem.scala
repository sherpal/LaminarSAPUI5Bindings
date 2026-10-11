package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.eventtypes.{EventWithPreciseTarget, HasDetail}
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** The ui5-cb-item represents the item for a ui5-combobox.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/ComboBoxItem/">the doc</a> for more information.
  */
object ComboBoxItem extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def text: String              = js.native
    def additionalText: String    = js.native
    def value: js.UndefOr[String] = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/ComboBoxItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-cb-item")

  lazy val text: HtmlAttr[String]           = htmlAttr("text", StringAsIsCodec)
  lazy val additionalText: HtmlAttr[String] = htmlAttr("additional-text", StringAsIsCodec)

  /** Value of the item, used in combination with the `selectedValue` of the parent [[ComboBox]] (since UI5 2.20.0). */
  lazy val value: HtmlAttr[String] = htmlAttr("value", StringAsIsCodec)

  object slots {}

  object events {
    trait ClickDetail extends js.Object {
      def originalEvent: dom.Event
    }

    /** Fired when the item is clicked (since UI5 2.23.0). */
    val onClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ClickDetail]] = new EventProp("click")
  }

}
