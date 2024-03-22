package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.configkeys.ValueState
import be.doeraene.webcomponents.ui5.configkeys.WrappingType
import be.doeraene.webcomponents.WebComponent

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
object RadioButton extends WebComponent with HasAccessibleName with HasName with HasText {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/RadioButton.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-radio-button")

  lazy val disabled: HtmlAttr[Boolean] = htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val checked: HtmlAttr[Boolean]  = htmlAttr("checked", BooleanAsAttrPresenceCodec)
  lazy val readonly: HtmlAttr[Boolean] = htmlAttr("readonly", BooleanAsAttrPresenceCodec)
  lazy val required: HtmlAttr[Boolean] = htmlAttr("required", BooleanAsAttrPresenceCodec)

  lazy val value: HtmlAttr[String] = {
    InputElementsFormSupport
    htmlAttr("value", StringAsIsCodec)
  }

  lazy val valueState: HtmlAttr[ValueState] = htmlAttr("value-state", ValueState.AsStringCodec)

  lazy val wrappingType: HtmlAttr[WrappingType] = htmlAttr("wrapping-type", WrappingType.AsStringCodec)

  object slots {}

  object events {
    val onChange: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("change")
  }

}
