package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.ToastPlacement
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.concurrent.duration.FiniteDuration
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget

/** Simple UI button
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Toast/">the doc</a> for more information.
  */
object Toast extends WebComponent with HasIcon {

  @js.native
  trait RawElement extends js.Object

  object RawElement {
    extension (rawElement: RawElement) {
      @deprecated("show method on Toast has been replaced by the open property", since = "2.0.0")
      def show(): Unit =
        rawElement.asInstanceOf[js.Dynamic].updateDynamic("open")(true)
    }
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/Toast.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-toast")

  lazy val placement: HtmlAttr[ToastPlacement] = ToastPlacement.asHtmlAttr("placement")
  lazy val open: HtmlAttr[Boolean]             = htmlAttr("open", BooleanAsAttrPresenceCodec)
  lazy val duration: HtmlAttr[FiniteDuration]  = htmlAttr("duration", FiniteDurationCodec)

  object slots {}

  object events {
    val onClose: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("close")
  }

  def getToastById(id: String): Option[dom.HTMLElement & RawElement] =
    Option(dom.document.getElementById(id)).map(_.asInstanceOf[dom.HTMLElement & RawElement])

  /** [[Observer]] you can feed a toast to show it. */
  @deprecated("showObserver has been replaced by using the open property", since = "2.0.0")
  def showObserver: Observer[Ref] = Observer(_.show())

  /** [[Mod]] for [[Toast]]s opening them each time the stream emits. */
  @deprecated("showFromEvents has been replaced by using the open property", since = "2.0.0")
  def showFromEvents(openerEvents: EventStream[Unit]): Mod[ReactiveHtmlElement[Ref]] =
    open <-- openerEvents.mapTo(true)

  /** [[Mod]] for [[Toast]]s opening them each time the stream emits, putting the given text. */
  def showFromTextEvents(openerEvents: EventStream[String]): Mod[ReactiveHtmlElement[Ref]] = List(
    open       <-- openerEvents.mapTo(true),
    child.text <-- openerEvents
  )

}
