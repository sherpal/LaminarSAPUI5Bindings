package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object CalendarExample extends Example("Calendar") {

  def component(using demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo): HtmlElement = div(
    DemoPanel("Basic Calendar") {
      //-- Begin: Basic Calendar
      val maybeSelectedDateVar: Var[Option[Calendar.events.SelectedDatesChangeInfo]] = Var(None)
      div(
        Label(child.text <-- maybeSelectedDateVar.signal.map {
          case None       => "No selected date yet."
          case Some(info) => s"Selected dates: ${info.values.zip(info.dates).mkString(", ")}."
        }),
        br(),
        Calendar(_.events.onSelectedDatesChange.map(_.detail) --> maybeSelectedDateVar.writer.contramapSome)
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
    }
  )

}
