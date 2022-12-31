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

  def RawImport: WebComponent.WithMetadata = ???

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-mcb-group-item")

  object slots {}

  object events {}

  

}
