package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.*
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, JSName}
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget

/** The DateTimePicker component alows users to select both date (day, month and year) and time (hours, minutes and
  * seconds) and for the purpose it consists of input field and Date/Time picker.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/DateTimePicker/">the doc</a> for more
  *   information.
  */
object DateTimePicker extends HasAccessibleName with HasName with HasValue {

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

  type Ref         = dom.html.Element with RawElement
  type ModFunction = DateTimePicker.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-datetime-picker")

  val id: ReactiveProp[String, String] = idAttr

  val disabled: ReactiveHtmlAttr[Boolean] = customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)

  val hideWeekNumbers: ReactiveHtmlAttr[Boolean] = customHtmlAttr("hide-week-numbers", BooleanAsAttrPresenceCodec)

  val placeholder: ReactiveHtmlAttr[String] = customHtmlAttr("placeholder", StringAsIsCodec)

  val readonly: ReactiveHtmlAttr[Boolean] = customHtmlAttr("readonly", BooleanAsAttrPresenceCodec)

  val required: ReactiveHtmlAttr[Boolean] = customHtmlAttr("required", BooleanAsAttrPresenceCodec)

  val valueState: ReactiveHtmlAttr[ValueState] = customHtmlAttr("value-state", ValueState.AsStringCodec)

  val formatPattern: ReactiveHtmlAttr[String] = customHtmlAttr("format-pattern", StringAsIsCodec)

  val maxDateRaw: ReactiveHtmlAttr[String] = customHtmlAttr("max-date", StringAsIsCodec)

  val minDateRaw: ReactiveHtmlAttr[String] = customHtmlAttr("min-date", StringAsIsCodec)

  val primaryCalendarType: ReactiveHtmlAttr[CalendarType] =
    customHtmlAttr("primary-calendar-type", CalendarType.AsStringCodec)

  val secondaryCalendarType: ReactiveHtmlAttr[CalendarType] =
    customHtmlAttr("secondary-calendar-type", CalendarType.AsStringCodec)

  object slots {
    val valueStateMessage: Slot = Slot("valueStateMessage")
  }

  object events {
    import DatePicker.events.DateEventData
    val onChange = new EventProp[EventWithPreciseTarget[Ref] & HasDetail[DateEventData]]("change")
    val onInput  = new EventProp[EventWithPreciseTarget[Ref] & HasDetail[DateEventData]]("input")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(DateTimePicker)): _*)

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