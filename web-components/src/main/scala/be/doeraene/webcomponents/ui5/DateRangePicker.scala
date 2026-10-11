package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.*
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.annotation.JSName

/** The DateRangePicker enables the users to enter a localized date range using touch, mouse, keyboard input, or by
  * selecting a date range in the calendar.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/DateRangePicker/">the doc</a> for more information.
  */
object DateRangePicker extends WebComponent with HasAccessibleName with HasName with HasValue {

  @js.native
  trait RawElement extends js.Object {
    def open: Boolean = js.native

    @JSName("endDateValue")
    def endDateValueJS: js.Date = js.native

    @JSName("startDateValue")
    def startDateValueJS: js.Date = js.native

    @scala.annotation.compileTimeOnly(
      "The methods openPicker(), closePicker() and isOpen() are replaced by open property."
    )
    def closePicker(): Unit = js.native

    def formatValue(date: js.Date): String = js.native

    def isInValidRange(value: String): Boolean = js.native

    @deprecated("Use isValidValue or isValidDisplayValue instead", since = "2.14.0")
    def isValid(value: String): Boolean = js.native

    /** Checks if a value is valid against the current `valueFormat` of the DateRangePicker. */
    def isValidValue(value: String): Boolean = js.native

    /** Checks if a value is valid against the current `displayFormat` of the DateRangePicker. */
    def isValidDisplayValue(value: String): Boolean = js.native

    @scala.annotation.compileTimeOnly(
      "The methods openPicker(), closePicker() and isOpen() are replaced by open property."
    )
    def openPicker(): Unit = js.native
  }

  object RawElement {
    extension (rawElement: RawElement) {
      @deprecated("The methods openPicker(), closePicker() and isOpen() are replaced by open property.")
      def isOpen(): Boolean = rawElement.open
    }
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/DateRangePicker.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-daterange-picker")

  lazy val open: HtmlAttr[Boolean] = htmlAttr("open", BooleanAsAttrPresenceCodec)

  lazy val delimiter: HtmlAttr[String] = htmlAttr("delimiter", StringAsIsCodec)

  lazy val disabled: HtmlAttr[Boolean] = htmlAttr("disabled", BooleanAsAttrPresenceCodec)

  lazy val hideWeekNumbers: HtmlAttr[Boolean] = htmlAttr("hide-week-numbers", BooleanAsAttrPresenceCodec)

  lazy val placeholder: HtmlAttr[String] = htmlAttr("placeholder", StringAsIsCodec)

  lazy val readonly: HtmlAttr[Boolean] = htmlAttr("readonly", BooleanAsAttrPresenceCodec)

  lazy val required: HtmlAttr[Boolean] = htmlAttr("required", BooleanAsAttrPresenceCodec)

  lazy val valueState: HtmlAttr[ValueState] = htmlAttr("value-state", ValueState.AsStringCodec)

  @deprecated("Use displayFormat and valueFormat instead", since = "2.14.0")
  lazy val formatPattern: HtmlAttr[String] = htmlAttr("format-pattern", StringAsIsCodec)

  /** Determines the format displayed in the input field (a pattern like "dd/MM/yyyy", or "short", "medium", "long"). */
  lazy val displayFormat: HtmlAttr[String] = htmlAttr("display-format", StringAsIsCodec)

  /** Determines the format used for the `value` attribute (and min/max dates). If not set, ISO format is used. */
  lazy val valueFormat: HtmlAttr[String] = htmlAttr("value-format", StringAsIsCodec)

  lazy val accessibleDescription: HtmlAttr[String]    = htmlAttr("accessible-description", StringAsIsCodec)
  lazy val accessibleDescriptionRef: HtmlAttr[String] = htmlAttr("accessible-description-ref", StringAsIsCodec)

  /** Defines whether the clear icon of the input will be shown. */
  lazy val showClearIcon: HtmlAttr[Boolean] = htmlAttr("show-clear-icon", BooleanAsAttrPresenceCodec)

  /** Defines whether two consecutive months are displayed side by side in the picker popup. */
  lazy val showTwoMonths: HtmlAttr[Boolean] = htmlAttr("show-two-months", BooleanAsAttrPresenceCodec)

  lazy val maxDateRaw: HtmlAttr[String] = htmlAttr("max-date", StringAsIsCodec)

  lazy val minDateRaw: HtmlAttr[String] = htmlAttr("min-date", StringAsIsCodec)

  lazy val primaryCalendarType: HtmlAttr[CalendarType] =
    htmlAttr("primary-calendar-type", CalendarType.AsStringCodec)

  lazy val secondaryCalendarType: HtmlAttr[CalendarType] =
    htmlAttr("secondary-calendar-type", CalendarType.AsStringCodec)

  lazy val calendarWeekNumbering: HtmlAttr[CalendarWeekNumbering] =
    htmlAttr("calendar-week-numbering", CalendarWeekNumbering.AsStringCodec)

  object slots {
    val valueStateMessage: Slot = Slot("valueStateMessage")
  }

  object events {
    import DatePicker.events.DateEventData
    import DatePicker.events.DatePickerValueStateChangeEventDetail
    val onChange = new EventProp[EventWithPreciseTarget[Ref] & HasDetail[DateEventData]]("change")
    val onInput  = new EventProp[EventWithPreciseTarget[Ref] & HasDetail[DateEventData]]("input")

    val onOpen: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("open")
    val onClose: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("close")

    val onValueStateChange
        : EventProp[EventWithPreciseTarget[Ref] & HasDetail[DatePickerValueStateChangeEventDetail]] =
      new EventProp("value-state-change")
  }

  /** You can feed [[DateTimePicker]] refs to this observer in order to close them. */
  @deprecated("closePickerObserver has been deprecated in favour of using the open property", since = "2.0.0")
  def closePickerObserver: Observer[Ref] = Observer(ref => ref.asInstanceOf[js.Dynamic].updateDynamic("open")(false))

  /** creates a [[Mod]] for your [[DateTimePicker]]s to close them when the stream emit. */
  @deprecated("closePickerFromEvents has been deprecated in favour of using the open property", since = "2.0.0")
  def closePickerFromEvents(stream: EventStream[Unit]) =
    open <-- stream.mapTo(false)

  /** You can feed [[DateTimePicker]] refs to this observer in order to open them. */
  @deprecated("openPickerObserver has been deprecated in favour of using the open property", since = "2.0.0")
  def openPickerObserver: Observer[Ref] = Observer(ref => ref.asInstanceOf[js.Dynamic].updateDynamic("open")(true))

  /** creates a [[Mod]] for your [[DateTimePicker]]s to close them when the stream emit. */
  @deprecated("openPickerFromEvents has been deprecated in favour of using the open property", since = "2.0.0")
  def openPickerFromEvents(stream: EventStream[Unit]) =
    open <-- stream.mapTo(true)

}
