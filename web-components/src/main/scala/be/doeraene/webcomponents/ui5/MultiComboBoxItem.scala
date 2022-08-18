package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, BooleanAsIsCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** The ui5-cb-item represents the item for a ui5-combobox.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/MultiComboBox">the doc</a> for more
  *   information.
  */
object MultiComboBoxItem extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def text: String           = js.native
    def additionalText: String = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/MultiComboBoxItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-mcb-item")

  lazy val selected: ReactiveHtmlAttr[Boolean]      = customHtmlAttr("selected", BooleanAsAttrPresenceCodec)
  lazy val text: ReactiveHtmlAttr[String]           = customHtmlAttr("text", StringAsIsCodec)
  lazy val additionalText: ReactiveHtmlAttr[String] = customHtmlAttr("additional-text", StringAsIsCodec)

  object slots {}

  object events {}

  

}
