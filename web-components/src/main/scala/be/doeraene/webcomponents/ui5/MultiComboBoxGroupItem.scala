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

/** The ui5-mcb-item-group is type of suggestion item, that can be used to split the ui5-multi-combobox suggestions into
  * groups.
  */
object MultiComboBoxGroupItem extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-mcb-item-group")

  lazy val headerText: HtmlAttr[String] = htmlAttr("header-text", StringAsIsCodec)

  object slots {}

  object events {}

  def item = MultiComboBoxItem

}
