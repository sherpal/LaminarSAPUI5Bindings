package be.doeraene.webcomponents.ui5

import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** ui5-menu-item is the item to use inside a ui5-menu. An arbitrary hierarchy structure can be represented by
  * recursively nesting menu items.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Menu/">the doc</a> for more information.
  */
object MenuItem extends WebComponent with HasIcon with HasText {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/MenuItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-menu-item")

  lazy val disabled: ReactiveHtmlAttr[Boolean] = customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)

  lazy val startsSection: ReactiveHtmlAttr[Boolean] = customHtmlAttr("starts-section", BooleanAsAttrPresenceCodec)

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(MenuItem)): _*)

  // reference to itself, useful for sub-menus
  def item: MenuItem.type = this

}
