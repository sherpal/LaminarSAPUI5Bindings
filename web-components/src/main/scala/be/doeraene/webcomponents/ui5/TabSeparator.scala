package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-tab-separator represents a vertical line to separate tabs inside a ui5-tabcontainer. Put it as a child of a
  * [[TabContainer]] (or in the `items` slot of a [[Tab]]), between [[Tab]]s.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/TabSeparator/">the doc</a> for more information.
  */
object TabSeparator extends WebComponent {

  @js.native
  trait RawElement extends js.Object {

    /** DOM reference of the separator placed in the tab strip (undefined for separators nested in other tabs). */
    def getDomRefInStrip(): js.UndefOr[dom.HTMLElement] = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/TabSeparator.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-tab-separator")

  object slots {}

  object events {}

}
