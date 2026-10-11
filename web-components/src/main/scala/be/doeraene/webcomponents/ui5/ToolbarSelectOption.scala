package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-toolbar-select-option is the option to use inside a [[ToolbarSelect]]. Its text is given as child.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/ToolbarSelectOption/">the doc</a> for more information.
  */
object ToolbarSelectOption extends WebComponent with HasValue {

  @js.native
  trait RawElement extends js.Object {
    def value: js.UndefOr[String] = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/ToolbarSelectOption.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-toolbar-select-option")

  lazy val selected: HtmlAttr[Boolean] = htmlAttr("selected", BooleanAsAttrPresenceCodec)

  object slots {}

  object events {}

}
