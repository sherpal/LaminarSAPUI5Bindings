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
import be.doeraene.webcomponents.ui5.configkeys.ValueState
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.WebComponent

/** The ui5-time-picker component provides an input field with assigned sliders which are opened on user action. The
  * ui5-time-picker allows users to select a localized time using touch, mouse, or keyboard input. It consists of two
  * parts: the time input field and the sliders.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/TimePicker/">the doc</a> for more information.
  */
object TimePicker extends WebComponent with HasValue with HasAccessibleName with HasName {

  @js.native
  trait RawElement extends js.Object {
    def open: Boolean = js.native

    def dateValue: js.Date = js.native

    def value: String = js.native

    def formatValue(date: js.Date): String = js.native

    /** Checks if a value is valid against the current display format. Note: an empty string is considered valid. */
    def isValid(value: String): Boolean = js.native

    /** Checks if a value is valid against the current `valueFormat`. Note: an empty string is considered valid. */
    def isValidValue(value: String): Boolean = js.native
  }

  object RawElement {
    extension (rawElement: RawElement) {
      @deprecated("isOpen method is deprecated, use the open property instead", since = "2.0.0")
      def isOpen(): Boolean = rawElement.open

      def closePicker(): Unit =
        rawElement.asInstanceOf[js.Dynamic].updateDynamic("open")(false)

      def openPicker(): Unit =
        rawElement.asInstanceOf[js.Dynamic].updateDynamic("open")(true)
    }
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/TimePicker.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)
  used(Localization)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-time-picker")

  lazy val disabled: HtmlAttr[Boolean]      = htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val placeholder: HtmlAttr[String]    = htmlAttr("placeholder", StringAsIsCodec)
  lazy val readonly: HtmlAttr[Boolean]      = htmlAttr("readonly", BooleanAsAttrPresenceCodec)
  lazy val valueState: HtmlAttr[ValueState] = htmlAttr("value-state", ValueState.AsStringCodec)
  lazy val open: HtmlAttr[Boolean]          = htmlAttr("open", BooleanAsAttrPresenceCodec)
  lazy val required: HtmlAttr[Boolean]      = htmlAttr("required", BooleanAsAttrPresenceCodec)

  @deprecated("Use displayFormat and valueFormat instead", since = "2.21.0")
  lazy val formatPattern: HtmlAttr[String] = htmlAttr("format-pattern", StringAsIsCodec)

  /** Determines the format displayed in the input field, e.g. "HH:mm:ss" or "hh:mm:ss a". */
  lazy val displayFormat: HtmlAttr[String] = htmlAttr("display-format", StringAsIsCodec)

  /** Determines the format used for the `value` attribute. */
  lazy val valueFormat: HtmlAttr[String] = htmlAttr("value-format", StringAsIsCodec)

  lazy val accessibleDescription: HtmlAttr[String]    = htmlAttr("accessible-description", StringAsIsCodec)
  lazy val accessibleDescriptionRef: HtmlAttr[String] = htmlAttr("accessible-description-ref", StringAsIsCodec)

  object slots {
    val valueStateMessage: Slot = Slot("valueStateMessage")
  }

  object events {
    import DatePicker.events.DateEventData

    val onChange: EventProp[EventWithPreciseTarget[Ref] & HasDetail[DateEventData]] = new EventProp("change")
    val onInput: EventProp[EventWithPreciseTarget[Ref] & HasDetail[DateEventData]]  = new EventProp("input")
    val onOpen: EventProp[EventWithPreciseTarget[Ref]]   = new EventProp("open")
    val onClose: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("close")
  }

}
