package be.doeraene.webcomponents.ui5

import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** The ui5-option-group component is used to group options ([[SelectOption]]s) within a [[Select]]. The options are
  * the children of the group.
  *
  * Since UI5 2.26.0.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/OptionGroup/">the doc</a> for more information.
  */
object OptionGroup extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def headerText: String = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/OptionGroup.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-option-group")

  /** Text of the group header. */
  lazy val headerText: HtmlAttr[String] = htmlAttr("header-text", StringAsIsCodec)

  object slots {}

  object events {}

  def option: SelectOption.type = SelectOption

}
