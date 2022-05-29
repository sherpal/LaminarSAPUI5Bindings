package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, IconName, ValueState, WrappingType}
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** Allows the user to set a binary value, such as true/false or yes/no for an item.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/CheckBox/">the doc</a> for more
  *   information.
  */
object CheckBox extends HasIcon with HasAccessibleName with HasName with HasText {

  @js.native
  trait RawElement extends js.Object {
    def checked: Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/CheckBox.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = CheckBox.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-checkbox")

  val id: ReactiveProp[String, String] = idAttr

  val checked: ReactiveHtmlAttr[Boolean]       = customHtmlAttr("checked", BooleanAsAttrPresenceCodec)
  val disabled: ReactiveHtmlAttr[Boolean]      = customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)
  val indeterminate: ReactiveHtmlAttr[Boolean] = customHtmlAttr("indeterminate", BooleanAsAttrPresenceCodec)
  val readonly: ReactiveHtmlAttr[Boolean]      = customHtmlAttr("readonly", BooleanAsAttrPresenceCodec)
  val required: ReactiveHtmlAttr[Boolean]      = customHtmlAttr("required", BooleanAsAttrPresenceCodec)

  val valueState: ReactiveHtmlAttr[ValueState] = customHtmlAttr("value-state", ValueState.AsStringCodec)

  val wrappingType: ReactiveHtmlAttr[WrappingType] = customHtmlAttr("wrapping-type", WrappingType.AsStringCodec)

  object slots {}

  object events {
    val onChange: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("change")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(CheckBox)): _*)

}
