package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-cb-item represents the item for a ui5-combobox.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/ComboBox/">the doc</a> for more
  *   information.
  */
object ComboBoxItem {

  @js.native
  trait RawElement extends js.Object {
    def text: String           = js.native
    def additionalText: String = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/ComboBoxItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = ComboBoxItem.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-cb-item")

  val id: ReactiveProp[String, String] = idAttr

  val text: ReactiveHtmlAttr[String]           = customHtmlAttr("text", StringAsIsCodec)
  val additionalText: ReactiveHtmlAttr[String] = customHtmlAttr("additional-text", StringAsIsCodec)

  object slots {}

  object events {}

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(ComboBoxItem)): _*)

}
