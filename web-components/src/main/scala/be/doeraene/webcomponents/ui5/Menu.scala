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
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget

/** ui5-menu component represents a hierarchical menu structure.
  */
object Menu extends WebComponent {

  //noinspection ScalaUnusedSymbol
  @js.native
  trait RawElement extends js.Object {}

  object RawElement {
    extension (rawElement: RawElement) {
      @deprecated("close method is replaced by using the open property", since = "2.0.0")
      def close(): Unit =
        rawElement.asInstanceOf[js.Dynamic].updateDynamic("open")(false)

      @deprecated("showAt method is replaced by using the open and opener property", since = "2.0.0")
      def showAt(opener: dom.HTMLElement): Unit = {
        rawElement.asInstanceOf[js.Dynamic].updateDynamic("opener")(opener)
        scala.scalajs.js.timers.setTimeout(0) {
          rawElement.asInstanceOf[js.Dynamic].updateDynamic("open")(true)
        }
      }
    }
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/Menu.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-menu")

  lazy val loading: HtmlAttr[Boolean]             = htmlAttr("loading", BooleanAsAttrPresenceCodec)
  lazy val loadingDelay: HtmlAttr[FiniteDuration] = htmlAttr("loading-delay", FiniteDurationCodec)
  lazy val headerText: HtmlAttr[String]           = htmlAttr("headerText", StringAsIsCodec)

  lazy val open: HtmlAttr[Boolean]    = htmlAttr("open", BooleanAsAttrPresenceCodec)
  lazy val openerId: HtmlAttr[String] = htmlAttr("opener", StringAsIsCodec)

  @deprecated("busy was renamed to loading", since = "2.0.0")
  def busy: HtmlAttr[Boolean] = loading

  @deprecated("busyDelay was renamed to loadingDelay", since = "2.0.0")
  def busyDelay: HtmlAttr[FiniteDuration] = loadingDelay

  object events {

    @js.native
    trait ItemClickDetail extends js.Object {
      def item: dom.HTMLElement = js.native
      def text: String          = js.native
    }
    def onItemClick = new EventProp[dom.Event & HasDetail[ItemClickDetail]]("item-click")

    def onClose       = new EventProp[EventWithPreciseTarget[Ref]]("close")
    def onOpen        = new EventProp[EventWithPreciseTarget[Ref]]("open")
    def onBeforeClose = new EventProp[dom.Event & HasDetail[HasEscPressed]]("before-close")
    def onBeforeOpen  = new EventProp[dom.Event & HasDetail[HasItem[MenuItem.Ref]]]("before-open")

    @deprecated("onAfterClose was renamed to onClose", since = "2.0.0")
    def onAfterClose = onClose

    @deprecated("onAfterOpen was renamed to onOpen", since = "2.0.0")
    def onAfterOpen = onOpen
  }

  def getMenuById(menuId: String): Option[dom.HTMLElement & RawElement] =
    Option(dom.document.getElementById(menuId)).map(_.asInstanceOf[dom.HTMLElement & RawElement])

  def item: MenuItem.type = MenuItem
  def separator           = MenuSeparator
}
