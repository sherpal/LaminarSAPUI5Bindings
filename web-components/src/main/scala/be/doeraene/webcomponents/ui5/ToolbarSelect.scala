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
import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, IconName, ValueState}
import be.doeraene.webcomponents.ui5.scaladsl.csssize.CSSSize

object ToolbarSelect extends WebComponent {

  @js.native
  trait RawElement extends js.Object

  @js.native
  @JSImport("@ui5/webcomponents/dist/ToolbarSelect.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-toolbar-select")

  lazy val accessibleName: HtmlAttr[String]    = htmlAttr("accessible-name", StringAsIsCodec)
  lazy val accessibleNameRef: HtmlAttr[String] = htmlAttr("accessible-name-ref", StringAsIsCodec)
  lazy val disabled: HtmlAttr[Boolean]         = htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val valueState: HtmlAttr[ValueState]    = htmlAttr("value-state", ValueState.AsStringCodec)
  lazy val widthJS: HtmlAttr[String]           = htmlAttr("width", StringAsIsCodec)
  lazy val width: HtmlAttr[CSSSize]            = htmlAttr("width", CSSSize.AsStringCodec)

  object slots {}

  object events {
    lazy val onChange: EventProp[dom.Event] = new EventProp("change")
    lazy val onClose: EventProp[dom.Event]  = new EventProp("close")
    lazy val onOpen: EventProp[dom.Event]   = new EventProp("open")
  }

  def option: ToolbarSelectOption.type = ToolbarSelectOption

}
