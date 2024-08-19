package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{CalendarSelectionMode, CalendarType}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, JSName}
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.WebComponent

/** The ui5-calendar component allows users to select one or more dates.
  *
  * Currently selected dates are represented with instances of ui5-date as children of the ui5-calendar. The value
  * property of each ui5-date must be a date string, correctly formatted according to the ui5-calendar's formatPattern
  * property. Whenever the user changes the date selection, ui5-calendar will automatically create/remove instances of
  * ui5-date in itself, unless you prevent this behavior by calling preventDefault() for the selected-dates-change
  * event. This is useful if you want to control the selected dates externally.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Calendar/">the doc</a> for more
  *   information.
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

  lazy val formatPattern: HtmlAttr[String] = htmlAttr("format-pattern", StringAsIsCodec)

  lazy val maxDateRaw: HtmlAttr[String] = htmlAttr("max-date", StringAsIsCodec)

  lazy val minDateRaw: HtmlAttr[String] = htmlAttr("min-date", StringAsIsCodec)

  lazy val primaryCalendarType: HtmlAttr[CalendarType] =
    htmlAttr("primary-calendar-type", CalendarType.AsStringCodec)

  lazy val secondaryCalendarType: HtmlAttr[CalendarType] =
    htmlAttr("secondary-calendar-type", CalendarType.AsStringCodec)

  object slots {
    val calendarLegend = new Slot("calendarLegend")

    val specialDates = new Slot("specialDates")
  }

  object events {
    trait SelectedDatesChangeInfo extends js.Object {
      @JSName("selectedValues")
      def selectedValuesJS: js.Array[String]

      @JSName("selectedDates")
      def selectedDatesJS: js.Array[Long]
    }

    object SelectedDatesChangeInfo {
      extension (info: SelectedDatesChangeInfo)
        @deprecated("values was renamed to selectedValues", since = "2.0.0")
        def values: List[String] = info.selectedValues
        @deprecated("values was renamed to selectedDates", since = "2.0.0")
        def dates: List[Long] = info.selectedDates

        def selectedValues: List[String] = info.selectedValuesJS.toList
        def selectedDates: List[Long]    = info.selectedDatesJS.toList
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
}
