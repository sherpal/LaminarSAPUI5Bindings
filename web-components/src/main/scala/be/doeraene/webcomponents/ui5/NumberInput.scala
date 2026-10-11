package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.ValueState
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.DoubleAsStringCodec
import com.raquo.laminar.codecs.IntAsStringCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.annotation.JSName

/** The ui5-number-input component is a numeric input field. It allows users to enter, edit and select numeric values.
  *
  * The default step is 1 but you can set a different one, as well as a maximum and minimum value. The increase/decrease
  * buttons and the up/down keyboard navigation become disabled when the value reaches the max/min.
  *
  * Since UI5 2.27.0.
  *
  * Note: this component is experimental in UI5 and its API may change.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/NumberInput/">the doc</a> for more information.
  */
object NumberInput extends WebComponent with HasAccessibleName with HasName {

  @js.native
  trait RawElement extends js.Object {
    def value: Double = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/NumberInput.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-number-input")

  lazy val disabled: HtmlAttr[Boolean] = htmlAttr("disabled", BooleanAsAttrPresenceCodec)

  lazy val max: HtmlAttr[Double] = htmlAttr("max", DoubleAsStringCodec)

  lazy val min: HtmlAttr[Double] = htmlAttr("min", DoubleAsStringCodec)

  lazy val readonly: HtmlAttr[Boolean] = htmlAttr("readonly", BooleanAsAttrPresenceCodec)

  lazy val step: HtmlAttr[Double] = htmlAttr("step", DoubleAsStringCodec)

  lazy val value: HtmlAttr[Double] = htmlAttr("value", DoubleAsStringCodec)

  /** Number of digits after the decimal point. */
  lazy val valuePrecision: HtmlAttr[Int] = htmlAttr("value-precision", IntAsStringCodec)

  lazy val valueState: HtmlAttr[ValueState] = htmlAttr("value-state", ValueState.AsStringCodec)

  object slots {
    val valueStateMessage: Slot = Slot("valueStateMessage")
  }

  object events {
    trait ValueStateChangeDetail extends js.Object {
      def valid: Boolean

      @JSName("valueState")
      def valueStateJS: String
    }

    object ValueStateChangeDetail {
      extension (detail: ValueStateChangeDetail) {
        def valueState: ValueState = ValueState.AsStringCodec.decode(detail.valueStateJS)
      }
    }

    /** Fired when the input operation has finished by pressing Enter or on focusout. */
    val onChange: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("change")

    /** Fired when the value of the component changes at each keystroke. */
    val onInput: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("input")

    /** Fired before the value state of the component is updated internally. The event is preventable, meaning that if
      * its default action is prevented, the component will not update the value state.
      */
    val onValueStateChange: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ValueStateChangeDetail]] =
      new EventProp("value-state-change")
  }

}
