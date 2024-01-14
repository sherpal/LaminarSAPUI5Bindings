package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import com.raquo.laminar.codecs.DoubleAsStringCodec
import com.raquo.laminar.codecs.IntAsStringCodec
import be.doeraene.webcomponents.ui5.configkeys.ValueState
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.WebComponent

/** The ui5-step-input consists of an input field and buttons with icons to increase/decrease the value with the
  * predefined step.
  *
  * The user can change the value of the component by pressing the increase/decrease buttons, by typing a number
  * directly, by using the keyboard up/down and page up/down, or by using the mouse scroll wheel. Decimal values are
  * supported.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/StepInput/">the doc</a> for more
  *   information.
  */
object StepInput extends WebComponent with HasAccessibleName with HasName {

  @js.native
  trait RawElement extends js.Object {
    def value: Double = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/StepInput.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-step-input")

  lazy val disabled: HtmlAttr[Boolean] = htmlAttr("disabled", BooleanAsAttrPresenceCodec)

  lazy val max: HtmlAttr[Double] = htmlAttr("max", DoubleAsStringCodec)

  lazy val min: HtmlAttr[Double] = htmlAttr("min", DoubleAsStringCodec)

  lazy val placeholder: HtmlAttr[String] = htmlAttr("placeholder", StringAsIsCodec)

  lazy val readonly: HtmlAttr[Boolean] = htmlAttr("readonly", BooleanAsAttrPresenceCodec)

  lazy val required: HtmlAttr[Boolean] = htmlAttr("required", BooleanAsAttrPresenceCodec)

  lazy val step: HtmlAttr[Double] = htmlAttr("step", DoubleAsStringCodec)

  lazy val value: HtmlAttr[Double] = htmlAttr("value", DoubleAsStringCodec)

  lazy val valuePrecision: HtmlAttr[Int] = htmlAttr("value-precision", IntAsStringCodec)

  lazy val valueState: HtmlAttr[ValueState] = htmlAttr("value-state", ValueState.AsStringCodec)

  object slots {
    val valueStateMessage: Slot = Slot("valueStateMessage")
  }

  object events {
    val onChange: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("change")
  }

}
