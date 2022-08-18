package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.configkeys.ValueState
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.WebComponent

/** The ui5-time-picker component provides an input field with assigned sliders which are opened on user action. The
  * ui5-time-picker allows users to select a localized time using touch, mouse, or keyboard input. It consists of two
  * parts: the time input field and the sliders.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/TimePicker/">the doc</a> for more
  *   information.
  */
object TimePicker extends WebComponent with HasValue {

  @js.native
  trait RawElement extends js.Object {
    def dateValue: js.Date = js.native

    def value: String = js.native

    def closePicker(): Unit = js.native

    def formatValue(date: js.Date): String = js.native

    def isOpen(): Boolean = js.native

    def isValid(value: String): Boolean = js.native

    def openPicker(): Unit = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/TimePicker.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-time-picker")

  lazy val disabled: ReactiveHtmlAttr[Boolean] = customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)

  lazy val formatPattern: ReactiveHtmlAttr[String] = customHtmlAttr("format-pattern", StringAsIsCodec)

  lazy val placeholder: ReactiveHtmlAttr[String] = customHtmlAttr("placeholder", StringAsIsCodec)

  lazy val readonly: ReactiveHtmlAttr[Boolean] = customHtmlAttr("readonly", BooleanAsAttrPresenceCodec)

  lazy val valueState: ReactiveHtmlAttr[ValueState] = customHtmlAttr("value-state", ValueState.AsStringCodec)

  object slots {
    val valueStateMessage: Slot = Slot("valueStateMessage")
  }

  object events {
    val onChange: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("change")
    val onInput: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("input")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(TimePicker)): _*)

}
