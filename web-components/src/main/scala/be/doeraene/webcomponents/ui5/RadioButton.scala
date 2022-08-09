package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.configkeys.ValueState
import be.doeraene.webcomponents.ui5.configkeys.WrappingType

/** The ui5-radio-button component enables users to select a single option from a set of options. When a
  * ui5-radio-button is selected by the user, the change event is fired. When a ui5-radio-button that is within a group
  * is selected, the one that was previously selected gets automatically deselected. You can group radio buttons by
  * using the name property. Note: If ui5-radio-button is not part of a group, it can be selected once, but can not be
  * deselected back.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/RadioButton/">the doc</a> for more
  *   information.
  */
object RadioButton extends HasAccessibleName with HasName with HasText {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/RadioButton.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element & RawElement
  type ModFunction = RadioButton.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-radio-button")

  val id: ReactiveProp[String, String] = idAttr

  val disabled: ReactiveHtmlAttr[Boolean] = customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)
  val checked: ReactiveHtmlAttr[Boolean]  = customHtmlAttr("checked", BooleanAsAttrPresenceCodec)
  val readonly: ReactiveHtmlAttr[Boolean] = customHtmlAttr("readonly", BooleanAsAttrPresenceCodec)

  val valueState: ReactiveHtmlAttr[ValueState] = customHtmlAttr("value-state", ValueState.AsStringCodec)

  val wrappingType: ReactiveHtmlAttr[WrappingType] = customHtmlAttr("wrapping-type", WrappingType.AsStringCodec)

  object slots {}

  object events {
    val onChange: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("change")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(RadioButton)): _*)

}
