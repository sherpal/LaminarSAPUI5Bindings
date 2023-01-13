package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.{HtmlAttr}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** The ui5-badge is a small non-interactive component which contains text information and color chosen from a list of
  * predefined color schemes. It serves the purpose to attract the user attention to some piece of information (state,
  * quantity, condition, etc.).
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Badge/">the doc</a> for more information.
  */
object Badge extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/Badge.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = htmlTag("ui5-badge")

  lazy val colourScheme: HtmlAttr[ColourScheme] = htmlAttr("color-scheme", ColourScheme.AsStringCodec)

  object slots {
    // note that unlike most elements that have an attribute Icon, this element has a slot icon instead.
    // most of the time you will want to use a ui5-icon for this slot.
    val icon: Slot = new Slot("icon")
  }

  object events {}

  

}
