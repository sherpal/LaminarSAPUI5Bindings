package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-toolbar-item is a wrapper component used to integrate any (UI5) web component (such as [[CheckBox]],
  * [[Input]], [[Title]], ...) into a [[Toolbar]]. It manages the overflow behaviour of its (single) child.
  *
  * Note: specialized wrappers like [[ToolbarButton]] or [[ToolbarSelect]] should be preferred when available.
  *
  * @since UI5 2.20.0
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/ToolbarItem/">the doc</a> for more information.
  */
object ToolbarItem extends WebComponent with HasToolbarOverflow {

  @js.native
  trait RawElement extends js.Object

  @js.native
  @JSImport("@ui5/webcomponents/dist/ToolbarItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-toolbar-item")

  object slots {}

  object events {}

}
