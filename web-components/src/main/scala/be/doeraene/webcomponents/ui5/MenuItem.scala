package be.doeraene.webcomponents.ui5

import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
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

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-menu-item")

  lazy val accessibleName: HtmlAttr[String] = htmlAttr("accessible-name", StringAsIsCodec)

  lazy val additionalText: HtmlAttr[String] = htmlAttr("additional-text", StringAsIsCodec)

  lazy val disabled: HtmlAttr[Boolean] = htmlAttr("disabled", BooleanAsAttrPresenceCodec)

  lazy val startsSection: HtmlAttr[Boolean] = htmlAttr("starts-section", BooleanAsAttrPresenceCodec)

  // reference to itself, useful for sub-menus
  def item: MenuItem.type = this

}
