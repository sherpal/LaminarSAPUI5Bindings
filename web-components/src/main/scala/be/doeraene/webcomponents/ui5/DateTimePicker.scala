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
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/DateTimePicker/">the doc</a> for more
  *   information.
  */
object DateTimePicker extends WebComponent with HasAccessibleName with HasName with HasValue {

  @js.native
  trait RawElement extends js.Object {
    @JSName("dateValue")
    def dateValueJS: js.Date = js.native

    def closePicker(): Unit = js.native

    def formatValue(date: js.Date): String = js.native

    def isInValidRange(value: String): Boolean = js.native

    def isOpen(): Boolean = js.native

    def isValid(value: String): Boolean = js.native

    def openPicker(): Unit = js.native
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
  val closePickerObserver: Observer[Ref] = Observer(_.closePicker())

  /** creates a [[Mod]] for your [[DateTimePicker]]s to close them when the stream emit. */
  def closePickerFromEvents(stream: EventStream[Unit]) =
    inContext[ReactiveHtmlElement[Ref]](el => stream.mapTo(el.ref) --> closePickerObserver)

  /** You can feed [[DateTimePicker]] refs to this observer in order to open them. */
  val openPickerObserver: Observer[Ref] = Observer(_.openPicker())

  /** creates a [[Mod]] for your [[DateTimePicker]]s to close them when the stream emit. */
  def openPickerFromEvents(stream: EventStream[Unit]) =
    inContext[ReactiveHtmlElement[Ref]](el => stream.mapTo(el.ref) --> openPickerObserver)

}
