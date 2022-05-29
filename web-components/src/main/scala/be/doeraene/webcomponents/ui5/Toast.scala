package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.ToastPlacement
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.concurrent.duration.FiniteDuration
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** Simple UI button
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Toast/">the doc</a> for more information.
  */
object Toast extends HasIcon with HasOnClick {

  @js.native
  trait RawElement extends js.Object {
    def show(): Unit = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/Toast.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = Toast.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-toast")

  val id: ReactiveProp[String, String] = idAttr

  val placement: ReactiveHtmlAttr[ToastPlacement] = customHtmlAttr("placement", ToastPlacement.AsStringCodec)

  val duration: ReactiveHtmlAttr[FiniteDuration] = customHtmlAttr("duration", FiniteDurationCodec)

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(Toast)): _*)

  def getToastById(id: String): Option[dom.HTMLElement & RawElement] =
    Option(dom.document.getElementById(id)).map(_.asInstanceOf[dom.HTMLElement & RawElement])

}
