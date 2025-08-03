package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.IconName
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.configkeys.ButtonBadgeDesign

/** The ui5-button-badge component defines a badge that appears in the ui5-button.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/components/ButtonBadge/">the doc</a> for more information.
  */
object ButtonBadge extends WebComponent with HasIcon {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/ButtonBadge.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-button-badge")

  lazy val design: HtmlAttr[ButtonBadgeDesign] = htmlAttr("design", ButtonBadgeDesign.AsStringCodec)
  lazy val text: HtmlAttr[String]              = htmlAttr("text", StringAsIsCodec)

  object slots {}

  object events {}

}
