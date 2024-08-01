package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.compiletime.ops.int.<=
import scala.concurrent.duration.FiniteDuration
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, IconName}
import be.doeraene.webcomponents.ui5.scaladsl.csssize.CSSSize

object ToolbarButton extends WebComponent {

  @js.native
  trait RawElement extends js.Object

  @js.native
  @JSImport("@ui5/webcomponents/dist/ToolbarButton.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-toolbar-button")

  lazy val accessibleName: HtmlAttr[String]    = htmlAttr("accessible-name", StringAsIsCodec)
  lazy val accessibleNameRef: HtmlAttr[String] = htmlAttr("accessible-name-ref", StringAsIsCodec)
  lazy val design: HtmlAttr[ButtonDesign]      = htmlAttr("design", ButtonDesign.AsStringCodec)
  lazy val disabled: HtmlAttr[Boolean]         = htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val icon: HtmlAttr[IconName]            = htmlAttr("icon", IconName.AsStringCodec)
  lazy val endIcon: HtmlAttr[IconName]         = htmlAttr("endIcon", IconName.AsStringCodec)
  lazy val text: HtmlAttr[String]              = htmlAttr("text", StringAsIsCodec)
  lazy val tooltip: HtmlAttr[String]           = htmlAttr("tooltip", StringAsIsCodec)
  lazy val widthJS: HtmlAttr[String]           = htmlAttr("width", StringAsIsCodec)
  lazy val width: HtmlAttr[CSSSize]            = htmlAttr("width", CSSSize.AsStringCodec)

  @scala.annotation.compileTimeOnly("iconEnd property has been removed in favour of the end-icon property")
  def iconEnd: HtmlAttr[Boolean] = ???

  object slots {}

  object events {
    lazy val onClick: EventProp[dom.Event] = new EventProp("click")
  }

}
