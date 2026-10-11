package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.MenuItemGroupCheckMode
import com.raquo.laminar.api.L.*
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-menu-item-group represents a group of [[MenuItem]]s (and [[MenuSeparator]]s) inside a [[Menu]]. The
  * `checkMode` of the group defines whether (and how many of) its items can be checked (see [[MenuItem.checked]]).
  *
  * @since UI5 2.12.0
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/MenuItemGroup/">the doc</a> for more information.
  */
object MenuItemGroup extends WebComponent {

  @js.native
  trait RawElement extends js.Object

  @js.native
  @JSImport("@ui5/webcomponents/dist/MenuItemGroup.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-menu-item-group")

  /** Check mode of the items of the group (None, Single or Multiple). */
  lazy val checkMode: HtmlAttr[MenuItemGroupCheckMode] = MenuItemGroupCheckMode.asHtmlAttr("check-mode")

  object slots {}

  object events {}

  def item: MenuItem.type           = MenuItem
  def separator: MenuSeparator.type = MenuSeparator

}
