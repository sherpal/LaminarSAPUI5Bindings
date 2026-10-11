package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.CalendarSelectionMode
import be.doeraene.webcomponents.ui5.configkeys.CalendarType
import be.doeraene.webcomponents.ui5.configkeys.CalendarWeekNumbering
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

/** The ui5-calendar component allows users to select one or more dates.
  *
  * Currently selected dates are represented with instances of ui5-date as children of the ui5-calendar. The value
  * property of each ui5-date must be a date string, correctly formatted according to the ui5-calendar's valueFormat
  * property. Whenever the user changes the date selection, ui5-calendar will automatically create/remove instances of
  * ui5-date in itself, unless you prevent this behavior by calling preventDefault() for the selected-dates-change
  * event. This is useful if you want to control the selected dates externally.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/Calendar/">the doc</a> for more information.
  */
object Calendar extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/Calendar.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-calendar")

  lazy val hideWeekNumbers: HtmlAttr[Boolean] = htmlAttr("hide-week-numbers", BooleanAsAttrPresenceCodec)

  lazy val selectionMode: HtmlAttr[CalendarSelectionMode] =
    CalendarSelectionMode.asHtmlAttr("selection-mode")

  @deprecated("Use displayFormat and valueFormat instead", since = "2.14.0")
  lazy val formatPattern: HtmlAttr[String] = htmlAttr("format-pattern", StringAsIsCodec)

  /** Determines the format displayed in the calendar (e.g. "dd/MM/yyyy", or "short", "medium", "long"). */
  lazy val displayFormat: HtmlAttr[String] = htmlAttr("display-format", StringAsIsCodec)

  /** Determines the format used for the value of the selected dates, the special dates, the disabled dates and the
    * min/max dates. If not set, ISO format (yyyy-MM-dd) is used.
    */
  lazy val valueFormat: HtmlAttr[String] = htmlAttr("value-format", StringAsIsCodec)

  lazy val maxDateRaw: HtmlAttr[String] = htmlAttr("max-date", StringAsIsCodec)

  lazy val minDateRaw: HtmlAttr[String] = htmlAttr("min-date", StringAsIsCodec)

  lazy val primaryCalendarType: HtmlAttr[CalendarType] =
    htmlAttr("primary-calendar-type", CalendarType.AsStringCodec)

  lazy val secondaryCalendarType: HtmlAttr[CalendarType] =
    htmlAttr("secondary-calendar-type", CalendarType.AsStringCodec)

  lazy val calendarWeekNumbering: HtmlAttr[CalendarWeekNumbering] =
    htmlAttr("calendar-week-numbering", CalendarWeekNumbering.AsStringCodec)

  object slots {
    val calendarLegend = new Slot("calendarLegend")

    val specialDates = new Slot("specialDates")

    /** Dates (or ranges of dates) that cannot be selected. Takes [[CalendarDateRange]] elements, whose values must be
      * formatted according to the `valueFormat` property.
      */
    val disabledDates = new Slot("disabledDates")
  }

  object events {
    trait SelectedDatesChangeInfo extends js.Object {
      @JSName("selectedValues")
      def selectedValuesJS: js.Array[String]

      /** Selected dates as UTC timestamps (in seconds). These are JS numbers, hence `Double`. */
      @JSName("selectedDates")
      def selectedDatesJS: js.Array[Double]
    }

    object SelectedDatesChangeInfo {
      extension (info: SelectedDatesChangeInfo)
        @deprecated("values was renamed to selectedValues", since = "2.0.0")
        def values: List[String] = info.selectedValues
        @deprecated("values was renamed to selectedDates", since = "2.0.0")
        def dates: List[Double] = info.selectedDates

        def selectedValues: List[String] = info.selectedValuesJS.toList
        def selectedDates: List[Double]  = info.selectedDatesJS.toList
    }

    val onSelectionChange: EventProp[EventWithPreciseTarget[Ref] & HasDetail[SelectedDatesChangeInfo]] =
      new EventProp(
        "selection-change"
      )

    @deprecated("onSelectedDatesChange has been renamed to onSelectionChange", since = "2.0.0")
    def onSelectedDatesChange = onSelectionChange
  }

  def date: CalendarDate.type = CalendarDate

  def specialDate: SpecialCalendarDate.type = SpecialCalendarDate

  def dateRange: CalendarDateRange.type = CalendarDateRange
}
