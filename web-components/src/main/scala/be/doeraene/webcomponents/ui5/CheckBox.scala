package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, IconName, ValueState, WrappingType}
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.{HtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** Allows the user to set a binary value, such as true/false or yes/no for an item.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/CheckBox/">the doc</a> for more
  *   information.
  */
object CheckBox extends WebComponent with HasIcon with HasAccessibleName with HasName with HasText {

  @js.native
  trait RawElement extends js.Object {
    def checked: Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/CheckBox.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-checkbox")

  lazy val checked: HtmlAttr[Boolean]       = customHtmlAttr("checked", BooleanAsAttrPresenceCodec)
  lazy val disabled: HtmlAttr[Boolean]      = customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val indeterminate: HtmlAttr[Boolean] = customHtmlAttr("indeterminate", BooleanAsAttrPresenceCodec)
  lazy val readonly: HtmlAttr[Boolean]      = customHtmlAttr("readonly", BooleanAsAttrPresenceCodec)
  lazy val required: HtmlAttr[Boolean]      = customHtmlAttr("required", BooleanAsAttrPresenceCodec)

  lazy val valueState: HtmlAttr[ValueState] = customHtmlAttr("value-state", ValueState.AsStringCodec)

  lazy val wrappingType: HtmlAttr[WrappingType] = customHtmlAttr("wrapping-type", WrappingType.AsStringCodec)

  object slots {}

  object events {
    val onChange: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("change")
  }

  

}
