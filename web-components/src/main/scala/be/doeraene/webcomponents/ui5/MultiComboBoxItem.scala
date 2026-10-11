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

/** The ui5-mcb-item represents the item for a ui5-multi-combobox.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/MultiComboBoxItem/">the doc</a> for more information.
  */
object MultiComboBoxItem extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def text: String              = js.native
    def additionalText: String    = js.native
    def value: js.UndefOr[String] = js.native
    def selected: Boolean         = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/MultiComboBoxItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-mcb-item")

  @deprecated(
    "Set the `value` property on items and use the `selectedValues` property on the parent `ui5-multi-combobox` instead for programmatic selection.",
    since = "2.20.0"
  )
  lazy val selected: HtmlAttr[Boolean] = htmlAttr("selected", BooleanAsAttrPresenceCodec)

  lazy val text: HtmlAttr[String]           = htmlAttr("text", StringAsIsCodec)
  lazy val additionalText: HtmlAttr[String] = htmlAttr("additional-text", StringAsIsCodec)

  /** Value of the item, used in combination with the `selectedValues` of the parent [[MultiComboBox]] (since UI5
    * 2.20.0).
    */
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
