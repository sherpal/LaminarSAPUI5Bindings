package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, EmbeddingAsIsCodec, IconName, LinkDesign, LinkTarget}
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.configkeys.WrappingType
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.WebComponent

/** The ui5-link is a hyperlink component that is used to navigate to other apps and web pages, or to trigger actions.
  * It is a clickable text element, visualized in such a way that it stands out from the standard text. On hover, it
  * changes its style to an underlined text to provide additional feedback to the user.
  */
object Link extends WebComponent with HasAccessibleName {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/Link.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-link")

  lazy val disabled: HtmlAttr[Boolean] =
    htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val href: HtmlAttr[String]       = htmlAttr("href", StringAsIsCodec)
  lazy val target: HtmlAttr[LinkTarget] = htmlAttr("target", LinkTarget.AsStringCodec)
  lazy val tooltip: HtmlAttr[String]    = htmlAttr("tooltip", StringAsIsCodec)
  lazy val icon: HtmlAttr[IconName]     = htmlAttr("icon", IconName.AsStringCodec)
  lazy val endIcon: HtmlAttr[IconName]  = htmlAttr("end-icon", IconName.AsStringCodec)

  lazy val design: HtmlAttr[LinkDesign] =
    htmlAttr("design", LinkDesign.AsStringCodec)

  lazy val wrappingType: HtmlAttr[WrappingType] = htmlAttr("wrapping-type", WrappingType.AsStringCodec)

  object slots {}

  object events {
    val onClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("click")
  }

}
