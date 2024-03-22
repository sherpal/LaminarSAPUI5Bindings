package be.doeraene.webcomponents.ui5

import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.configkeys.WrappingType
import be.doeraene.webcomponents.WebComponent

/** Simple UI button
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Label/">the doc</a> for more information.
  */
object Label extends WebComponent with HasIcon {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/Label.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-label")

  lazy val required: HtmlAttr[Boolean]          = htmlAttr("required", BooleanAsAttrPresenceCodec)
  lazy val showColon: HtmlAttr[Boolean]         = htmlAttr("show-colon", BooleanAsAttrPresenceCodec)
  lazy val forId: HtmlAttr[String]              = htmlAttr("for", StringAsIsCodec)
  lazy val wrappingType: HtmlAttr[WrappingType] = htmlAttr("wrapping-type", WrappingType.AsStringCodec)

  val isRequired: Setter[HtmlElement] = required := true

}
