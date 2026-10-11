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

/** The ui5-mcb-item-custom is a [[MultiComboBox]] item that allows placing custom content (its children) inside a
  * multi-combobox item. The `text` attribute is used for filtering and token display.
  *
  * Since UI5 2.24.0.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/MultiComboBoxItemCustom/">the doc</a> for more
  *   information.
  */
object MultiComboBoxItemCustom extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def text: String              = js.native
    def value: js.UndefOr[String] = js.native
    def selected: Boolean         = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/MultiComboBoxItemCustom.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-mcb-item-custom")

  /** Text of the item, used for filtering and displayed in the token when selected. */
  lazy val text: HtmlAttr[String] = htmlAttr("text", StringAsIsCodec)

  /** Value of the item, used in combination with the `selectedValues` of the parent [[MultiComboBox]]. */
  lazy val value: HtmlAttr[String] = htmlAttr("value", StringAsIsCodec)

  object slots {}

  object events {
    trait ClickDetail extends js.Object {
      def originalEvent: dom.Event
    }

    val onClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ClickDetail]] = new EventProp("click")
  }

}
