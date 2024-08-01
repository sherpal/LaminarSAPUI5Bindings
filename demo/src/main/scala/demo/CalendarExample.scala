package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

import scala.scalajs.js

object CalendarExample extends Example("Calendar") {

  def component(using demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo): HtmlElement = div(
    DemoPanel("Basic Calendar") {
      //-- Begin: Basic Calendar
      val maybeSelectedDateVar: Var[Option[Calendar.events.SelectedDatesChangeInfo]] = Var(None)
      div(
        Label(child.text <-- maybeSelectedDateVar.signal.map {
          case None       => "No selected date yet."
          case Some(info) => s"Selected dates: ${info.selectedValues.zip(info.selectedDates).mkString(", ")}."
        }),
        br(),
        Calendar(_.events.onSelectionChange.map(_.detail) --> maybeSelectedDateVar.writer.contramapSome)
      )
      //-- End
    },
    DemoPanel("Calendar with Minimum and Maximum Date with Format Pattern") {
      //-- Begin: Calendar with Minimum and Maximum Date with Format Pattern
      Calendar(_.minDateRaw := "7/7/2020", _.maxDateRaw := "20/10/2020", _.formatPattern := "dd/MM/yyyy")
      //-- End
    },
    DemoPanel("Calendar & Hidden Week Numbers") {
      //-- Begin: Calendar & Hidden Week Numbers
      Calendar(_.hideWeekNumbers := true)
      //-- End
    },
    DemoPanel("Calendar with Selection Mode Multiple") {
      //-- Begin: Calendar with Selection Mode Multiple
      Calendar(_.selectionMode := CalendarSelectionMode.Multiple)
      //-- End
    },
    DemoPanel("Calendar with Selection Mode Range") {
      //-- Begin: Calendar with Selection Mode Range
      Calendar(_.selectionMode := CalendarSelectionMode.Range)
      //-- End
    },
    DemoPanel("Other types of Calendar") {
      //-- Begin: Other types of Calendar

      div(CalendarType.allValues.filterNot(_ == CalendarType.Gregorian).map { calendarType =>
        // Need to import the support for your calendar type. This has to be done at least once in your application
        // (for example where your application starts, or the first place you use this calendar type)
        calendarType.importObject
        div(
          Title.h3(s"Type of calendar: ${calendarType.value}"),
          Calendar(_.primaryCalendarType := calendarType)
        )
      })
      //-- End
    },
    DemoPanel("Calendar with Legend") {
      //-- Begin: Calendar with Legend
      val types = Vector(CalendarLegendItemType.Type05, CalendarLegendItemType.Type07, CalendarLegendItemType.Type12)

      val now   = new js.Date()
      val year  = now.getFullYear().toInt
      val month = now.getMonth().toInt

      val specialDates = scala.util.Random.shuffle((1 to 28).toVector).take(10).map { day =>
        new js.Date(year, month, day, 1, 0, 0, 0)
      }

      def formatDate(date: js.Date): String = date.toISOString().take(10)

      Calendar(
        _.formatPattern := "YYYY-MM-dd",
        specialDates.zip(LazyList.continually(types).flatten).map { (date, tpe) =>
          Calendar.slots.specialDates := Calendar.specialDate(
            _.tpe   := tpe,
            _.value := formatDate(date)
          )
        },
        _.slots.calendarLegend := CalendarLegend(
          _.hideToday       := true,
          _.hideSelectedDay := true,
          _.item(_.text := "Holiday", _.tpe         := CalendarLegendItemType.Type05),
          _.item(_.text := "School Vacation", _.tpe := CalendarLegendItemType.Type07),
          _.item(_.text := "Wedding", _.tpe         := CalendarLegendItemType.Type12)
        )
      )
      //-- End
    }
  )

}
