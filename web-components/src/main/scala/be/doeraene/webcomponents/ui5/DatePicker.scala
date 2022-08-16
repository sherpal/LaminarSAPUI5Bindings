package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, CalendarType, IconName, ValueState}
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{EventProcessor, ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import java.time.LocalDate
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.WebComponent

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

    var value: String = js.native

    def closePicker(): Unit = js.native

    def formatValue(date: js.Date): String = js.native

    def isInValidRange(input: String): Boolean = js.native

    def isOpen(): Boolean = js.native

    def isValid(value: String): Boolean = js.native

    def openPicker(): Unit = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/DatePicker.js", JSImport.Default)
  object RawImport extends js.Object

  @js.native
  @JSImport("@ui5/webcomponents-localization/dist/Assets.js", JSImport.Default)
  object Localization extends js.Object

  @js.native
  @JSImport("@ui5/webcomponents-base/dist/config/Language.js", "setLanguage")
  def setLanguage(language: String): Unit = js.native

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)
  used(Localization)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = DatePicker.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-date-picker")

  lazy val disabled: ReactiveHtmlAttr[Boolean]        = customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val hideWeekNumbers: ReactiveHtmlAttr[Boolean] = customHtmlAttr("hide-week-numbers", BooleanAsAttrPresenceCodec)

  lazy val placeholder: ReactiveHtmlAttr[String] = customHtmlAttr("placeholder", StringAsIsCodec)

  lazy val readonly: ReactiveHtmlAttr[Boolean] = customHtmlAttr("readonly", BooleanAsAttrPresenceCodec)

  lazy val required: ReactiveHtmlAttr[Boolean] = customHtmlAttr("required", BooleanAsAttrPresenceCodec)

  lazy val valueState: ReactiveHtmlAttr[ValueState] = customHtmlAttr("value-state", ValueState.AsStringCodec)

  lazy val formatPattern: ReactiveHtmlAttr[String] = customHtmlAttr("format-pattern", StringAsIsCodec)

  lazy val maxDate: ReactiveHtmlAttr[LocalDate] = customHtmlAttr("max-date", LocalDateCodec)
  lazy val minDate: ReactiveHtmlAttr[LocalDate] = customHtmlAttr("min-date", LocalDateCodec)

  lazy val primaryCalendarType: ReactiveHtmlAttr[CalendarType] =
    customHtmlAttr("primary-calendar-type", CalendarType.AsStringCodec)

  lazy val secondaryCalendarType: ReactiveHtmlAttr[CalendarType] =
    customHtmlAttr("secondary-calendar-type", CalendarType.AsStringCodec)

  object slots {
    val valueStateMessage: Slot = new Slot("valueStateMessage")
  }

  object events {

    trait DateEventData extends js.Object {
      def value: String
      def valid: Boolean
    }

    val onChange = new EventProp[EventWithPreciseTarget[Ref] & HasDetail[DateEventData]]("change")
    val onInput  = new EventProp[EventWithPreciseTarget[Ref] & HasDetail[DateEventData]]("input")

    val onValidDateChange: EventProcessor[EventWithPreciseTarget[Ref] & HasDetail[DateEventData], LocalDate] =
      onChange.map(_.detail).filter(_.valid).map(_.value).map(stringToLocalDate)

    val onValidDateInput: EventProcessor[EventWithPreciseTarget[Ref] & HasDetail[DateEventData], LocalDate] =
      onInput.map(_.detail).filter(_.valid).map(_.value).map(stringToLocalDate)
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(DatePicker)): _*)

  // var so that someone can use a different format if they like
  var format: java.time.format.DateTimeFormatter = java.time.format.DateTimeFormatter.ISO_LOCAL_DATE

  def stringToLocalDate(str: String): LocalDate = LocalDate.parse(str, format)

  def localDateToString(date: LocalDate): String = date.format(format)

}
