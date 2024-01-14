package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.eventtypes.{HasDetail, HasEscPressed, HasItem, HasTargetRef}
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

import scala.concurrent.duration.FiniteDuration

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

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-menu")

  lazy val busy: HtmlAttr[Boolean]             = htmlAttr("busy", BooleanAsAttrPresenceCodec)
  lazy val busyDelay: HtmlAttr[FiniteDuration] = htmlAttr("busy-delay", FiniteDurationCodec)
  lazy val headerText: HtmlAttr[String]        = htmlAttr("headerText", StringAsIsCodec)

  object events {

    @js.native
    trait ItemClickDetail extends js.Object {
      def item: dom.HTMLElement = js.native
      def text: String          = js.native
    }
    val onItemClick = new EventProp[dom.Event with HasDetail[ItemClickDetail]]("item-click")

    val onAfterClose  = new EventProp[dom.Event]("after-close")
    val onAfterOpen   = new EventProp[dom.Event]("after-open")
    val onBeforeClose = new EventProp[dom.Event with HasDetail[HasEscPressed]]("before-close")
    val onBeforeOpen  = new EventProp[dom.Event with HasDetail[HasItem[MenuItem.Ref]]]("before-open")
  }

  def getMenuById(menuId: String): Option[dom.HTMLElement with RawElement] =
    Option(dom.document.getElementById(menuId)).map(_.asInstanceOf[dom.HTMLElement with RawElement])

  def item: MenuItem.type = MenuItem
}
