package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.WrappingType
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** The ui5-mcb-item-group is type of suggestion item, that can be used to split the ui5-multi-combobox suggestions into
  * groups.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/MultiComboBoxItemGroup/">the doc</a> for more
  *   information.
  */
object MultiComboBoxGroupItem extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def headerText: String = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/MultiComboBoxItemGroup.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-mcb-item-group")

  lazy val headerText: HtmlAttr[String] = htmlAttr("header-text", StringAsIsCodec)

  /** Accessible name of the group header. */
  lazy val headerAccessibleName: HtmlAttr[String] = htmlAttr("header-accessible-name", StringAsIsCodec)

  /** Whether the group header text wraps (`Normal`) or is truncated (`None`, default). Since UI5 2.15.0. */
  lazy val wrappingType: HtmlAttr[WrappingType] = htmlAttr("wrapping-type", WrappingType.AsStringCodec)

  object slots {}

  object events {}

  def item: MultiComboBoxItem.type             = MultiComboBoxItem
  def itemCustom: MultiComboBoxItemCustom.type = MultiComboBoxItemCustom

}
