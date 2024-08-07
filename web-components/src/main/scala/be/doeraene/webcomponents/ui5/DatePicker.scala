package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, CalendarType, IconName, ValueState}
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.{EventProcessor, HtmlAttr}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import java.time.LocalDate
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.WebComponent
import scala.scalajs.js.annotation.JSName

/** The ui5-date-picker component provides an input field with assigned calendar which opens on user action.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/DatePicker/">the doc</a> for more
  *   information.
  */
object DatePicker extends WebComponent with HasAccessibleName with HasName with HasValue {

  //noinspection ScalaUnusedSymbol
  @js.native
  trait RawElement extends js.Object {
    def dateValue: js.Date = js.native

    def open: Boolean = js.native

    var value: String = js.native

    def formatValue(date: js.Date): String = js.native

    def isInValidRange(input: String): Boolean = js.native

    def isValid(value: String): Boolean = js.native
  }

  object RawElement {
    extension (rawElement: RawElement) {
      @deprecated("The methods openPicker(), closePicker() and isOpen() are replaced by open property.")
      def isOpen(): Boolean = rawElement.open

      @deprecated(
        "The methods openPicker(), closePicker() and isOpen() are replaced by open property.",
        since = "2.0.0"
      )
      def openPicker(): Unit =
        rawElement.asInstanceOf[js.Dynamic].updateDynamic("open")(true)

      @deprecated(
        "The methods openPicker(), closePicker() and isOpen() are replaced by open property.",
        since = "2.0.0"
      )
      def closePicker(): Unit =
        rawElement.asInstanceOf[js.Dynamic].updateDynamic("open")(false)
    }
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/DatePicker.js", JSImport.Default)
  object RawImport extends js.Object

  @js.native
  @JSImport("@ui5/webcomponents-base/dist/config/Language.js", "setLanguage")
  def setLanguage(language: String): Unit = js.native

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)
  used(Localization)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-date-picker")

  lazy val disabled: HtmlAttr[Boolean]        = htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val hideWeekNumbers: HtmlAttr[Boolean] = htmlAttr("hide-week-numbers", BooleanAsAttrPresenceCodec)

  lazy val placeholder: HtmlAttr[String] = htmlAttr("placeholder", StringAsIsCodec)

  lazy val readonly: HtmlAttr[Boolean] = htmlAttr("readonly", BooleanAsAttrPresenceCodec)

  lazy val required: HtmlAttr[Boolean] = htmlAttr("required", BooleanAsAttrPresenceCodec)

  lazy val valueState: HtmlAttr[ValueState] = htmlAttr("value-state", ValueState.AsStringCodec)

  lazy val formatPattern: HtmlAttr[String] = htmlAttr("format-pattern", StringAsIsCodec)

  lazy val maxDateStr: HtmlAttr[String] = htmlAttr("max-date", StringAsIsCodec)
  lazy val minDateStr: HtmlAttr[String] = htmlAttr("min-date", StringAsIsCodec)

  lazy val open: HtmlAttr[Boolean] = htmlAttr("open", BooleanAsAttrPresenceCodec)

  lazy val primaryCalendarType: HtmlAttr[CalendarType] =
    htmlAttr("primary-calendar-type", CalendarType.AsStringCodec)

  lazy val secondaryCalendarType: HtmlAttr[CalendarType] =
    htmlAttr("secondary-calendar-type", CalendarType.AsStringCodec)

  object slots {
    val valueStateMessage: Slot = new Slot("valueStateMessage")
  }

  object events {

    trait DateEventData extends js.Object {
      def value: String
      def valid: Boolean
    }

    trait DatePickerValueStateChangeEventDetail extends js.Object {
      def valid: Boolean

      @JSName("valueState")
      def valueStateJS: String
    }

    object DatePickerValueStateChangeEventDetail {
      extension (detail: DatePickerValueStateChangeEventDetail) {
        def valueState: ValueState = ValueState.AsStringCodec.decode(detail.valueStateJS)
      }
    }

    val onChange = new EventProp[EventWithPreciseTarget[Ref] & HasDetail[DateEventData]]("change")
    val onInput  = new EventProp[EventWithPreciseTarget[Ref] & HasDetail[DateEventData]]("input")

    val onValidDateChange: EventProcessor[EventWithPreciseTarget[Ref] & HasDetail[DateEventData], String] =
      onChange.map(_.detail).filter(_.valid).map(_.value)

    val onValidDateInput: EventProcessor[EventWithPreciseTarget[Ref] & HasDetail[DateEventData], String] =
      onInput.map(_.detail).filter(_.valid).map(_.value)

    val onValueStateChange =
      new EventProp[EventWithPreciseTarget[Ref] & HasDetail[DatePickerValueStateChangeEventDetail]](
        "value-state-change"
      )

  }

}
