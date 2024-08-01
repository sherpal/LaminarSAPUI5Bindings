package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** The ui5-combobox-group-item is type of suggestion item, that can be used to split the ui5-combobox suggestions into
  * groups.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/ComboBox/">the doc</a> for more
  *   information.
  */
object ComboBoxGroupItem extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def headerText: String     = js.native
    def additionalText: String = js.native
  }

  object RawElement {
    extension (rawElement: RawElement) {
      @deprecated("text property of ComboBoxGroupItem has been renamed to headerText", since = "2.0.0")
      def text: String = rawElement.headerText
    }
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/ComboBoxItemGroup.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-cb-item-group")

  lazy val headerText: HtmlAttr[String] = htmlAttr("header-text", StringAsIsCodec)

  @deprecated("text property of ComboBoxGroupItem has been renamed to headerText", since = "2.0.0")
  def text = headerText

  object slots {}

  object events {}

  def item: ComboBoxItem.type = ComboBoxItem

}
