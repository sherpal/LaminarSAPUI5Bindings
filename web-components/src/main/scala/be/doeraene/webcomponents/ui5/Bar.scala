package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.BarDesign
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** The Bar is a container which is primarily used to hold titles, buttons and input elements and its design and
  * functionality is the basis for page headers and footers. The component consists of three areas to hold its content -
  * startContent slot, default slot and endContent slot. It has the capability to center content, such as a title, while
  * having other components on the left and right side.
  */
object Bar extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/Bar.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-bar")

  lazy val design: HtmlAttr[BarDesign] = htmlAttr("design", BarDesign.AsStringCodec)

  object slots {
    val endContent: Slot   = new Slot("endContent")
    val startContent: Slot = new Slot("startContent")
  }

}
