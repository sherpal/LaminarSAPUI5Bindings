package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.eventtypes.{HasDetail, HasTargetRef}
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.{HtmlAttr}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** Simple UI button
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Menu/">the doc</a> for more information.
  */
object Menu extends WebComponent {

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

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = htmlTag("ui5-menu")

  lazy val headerText: HtmlAttr[String] = htmlAttr("headerText", StringAsIsCodec)

  object events {

    @js.native
    trait ItemClickDetail extends js.Object {
      def item: dom.HTMLElement = js.native
      def text: String          = js.native
    }
    val onItemClick = new EventProp[dom.Event & HasDetail[ItemClickDetail]]("item-click")
  }

  

  def getMenuById(menuId: String): Option[dom.HTMLElement & RawElement] =
    Option(dom.document.getElementById(menuId)).map(_.asInstanceOf[dom.HTMLElement & RawElement])

  def item: MenuItem.type = MenuItem
}
