package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-shellbar-spacer is an element, used for visual separation between the two content areas of the
  * [[ShellBar]]. Put it inside the `content` slot of the [[ShellBar]].
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/ShellBarSpacer/">the doc</a> for more information.
  */
object ShellBarSpacer extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/ShellBarSpacer.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-shellbar-spacer")

  object slots {}

  object events {}

}
