package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.*
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, JSName}
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.WebComponent

/** The DateTimePicker component alows users to select both date (day, month and year) and time (hours, minutes and
  * seconds) and for the purpose it consists of input field and Date/Time picker.
  */
object DateTimePicker extends WebComponent with HasAccessibleName with HasName with HasValue {

  @js.native
  trait RawElement extends js.Object {
    @JSName("dateValue")
    def dateValueJS: js.Date = js.native

    def open: Boolean = js.native

    @scala.annotation.compileTimeOnly(
      "The methods openPicker(), closePicker() and isOpen() are replaced by open property."
    )
    def closePicker(): Unit = js.native

    def formatValue(date: js.Date): String = js.native

    def isInValidRange(value: String): Boolean = js.native

    def isValid(value: String): Boolean = js.native

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
  @JSImport("@ui5/webcomponents/dist/DateTimePicker.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)
  used(Localization)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-datetime-picker")

  lazy val disabled: HtmlAttr[Boolean]        = htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val hideWeekNumbers: HtmlAttr[Boolean] = htmlAttr("hide-week-numbers", BooleanAsAttrPresenceCodec)
  lazy val placeholder: HtmlAttr[String]      = htmlAttr("placeholder", StringAsIsCodec)
  lazy val readonly: HtmlAttr[Boolean]        = htmlAttr("readonly", BooleanAsAttrPresenceCodec)
  lazy val required: HtmlAttr[Boolean]        = htmlAttr("required", BooleanAsAttrPresenceCodec)
  lazy val valueState: HtmlAttr[ValueState]   = htmlAttr("value-state", ValueState.AsStringCodec)
  lazy val formatPattern: HtmlAttr[String]    = htmlAttr("format-pattern", StringAsIsCodec)
  lazy val maxDateRaw: HtmlAttr[String]       = htmlAttr("max-date", StringAsIsCodec)
  lazy val minDateRaw: HtmlAttr[String]       = htmlAttr("min-date", StringAsIsCodec)
  lazy val open: HtmlAttr[Boolean]            = htmlAttr("open", BooleanAsAttrPresenceCodec)

  lazy val primaryCalendarType: HtmlAttr[CalendarType] =
    htmlAttr("primary-calendar-type", CalendarType.AsStringCodec)

  lazy val secondaryCalendarType: HtmlAttr[CalendarType] =
    htmlAttr("secondary-calendar-type", CalendarType.AsStringCodec)

  object slots {
    val valueStateMessage: Slot = Slot("valueStateMessage")
  }

  object events {
    import DatePicker.events.DateEventData
    val onChange = new EventProp[EventWithPreciseTarget[Ref] & HasDetail[DateEventData]]("change")
    val onInput  = new EventProp[EventWithPreciseTarget[Ref] & HasDetail[DateEventData]]("input")
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
