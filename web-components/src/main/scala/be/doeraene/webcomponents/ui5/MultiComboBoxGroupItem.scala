package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.{HtmlAttr}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** The ui5-mcb-group-item is type of suggestion item, that can be used to split the ui5-multi-combobox suggestions into
  * groups.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/MultiComboBox/">the doc</a> for more
  *   information.
  */
object MultiComboBoxGroupItem extends WebComponent with HasText {

  @js.native
  trait RawElement extends js.Object {}

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = htmlTag("ui5-mcb-group-item")

  object slots {}

  object events {}

  

}
