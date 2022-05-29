package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.eventtypes.{HasDetail, HasTargetRef}
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** Simple UI button
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Menu/">the doc</a> for more information.
  */
object Menu {

  //noinspection ScalaUnusedSymbol
  @js.native
  trait RawElement extends js.Object {
    def close(): Unit = js.native

    def showAt(opener: dom.HTMLElement): Unit = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/Menu.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = Menu.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-menu")

  val id: ReactiveProp[String, String] = idAttr

  val headerText: ReactiveHtmlAttr[String] = customHtmlAttr("headerText", StringAsIsCodec)

  sealed trait ItemClickDetail extends js.Object {
    def item: dom.HTMLElement
    def text: String
  }
  val onItemClick = new EventProp[dom.Event & HasDetail[ItemClickDetail]]("item-click")

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(Menu)): _*)

  def getMenuById(menuId: String): Option[dom.HTMLElement & RawElement] =
    Option(dom.document.getElementById(menuId)).map(_.asInstanceOf[dom.HTMLElement & RawElement])

  def item: MenuItem.type = MenuItem
}
