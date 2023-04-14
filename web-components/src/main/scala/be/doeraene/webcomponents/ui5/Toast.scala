package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.ToastPlacement
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.concurrent.duration.FiniteDuration
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** Simple UI button
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Toast/">the doc</a> for more information.
  */
object Toast extends WebComponent with HasIcon {

  @js.native
  trait RawElement extends js.Object {
    def show(): Unit = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/Toast.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = htmlTag("ui5-toast")

  lazy val placement: HtmlAttr[ToastPlacement] = htmlAttr("placement", ToastPlacement.AsStringCodec)

  lazy val duration: HtmlAttr[FiniteDuration] = htmlAttr("duration", FiniteDurationCodec)

  object slots {}

  object events {}

  def getToastById(id: String): Option[dom.HTMLElement with RawElement] =
    Option(dom.document.getElementById(id)).map(_.asInstanceOf[dom.HTMLElement with RawElement])

  /** [[Observer]] you can feed a toast to show it. */
  val showObserver: Observer[Ref] = Observer(_.show())

  /** [[Mod]] for [[Toast]]s opening them each time the stream emits. */
  def showFromEvents(openerEvents: EventStream[Unit]): Mod[ReactiveHtmlElement[Ref]] =
    inContext[ReactiveHtmlElement[Ref]](el => openerEvents.mapTo(el.ref) --> showObserver)

  /** [[Mod]] for [[Toast]]s opening them each time the stream emits, putting the given text. */
  def showFromTextEvents(openerEvents: EventStream[String]): Mod[ReactiveHtmlElement[Ref]] = List(
    showFromEvents(openerEvents.mapTo(())),
    child.text <-- openerEvents
  )

}
