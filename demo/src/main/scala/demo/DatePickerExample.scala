package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub, MTG}
import java.time.LocalDate

object DatePickerExample extends Example("DatePicker") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    MessageStrip(
      _.design := MessageStripDesign.Critical,
      "Note: you must provide an implementation for java.time classes in order to use the DatePicker. " +
        "For example using the scala-java-time library."
    ),
    DemoPanel("Basic DatePicker")(
      //-- Begin: Basic DatePicker
      DatePicker()
      //-- End
    ),
    DemoPanel("DatePicker with Placeholder, Tooltip, Events, ValueState and valueStateMessage") {
      //-- Begin: DatePicker with Placeholder, Tooltip, Events, ValueState and valueStateMessage
      val validityStateBus: EventBus[Boolean] = new EventBus
      DatePicker(
        _.placeholder             := "Delivery date...",
        _.slots.valueStateMessage := div("The value is not valid. Please provide a valid value."),
        _.valueState             <-- validityStateBus.events.map(if _ then ValueState.None else ValueState.Negative),
        _.events.onChange.map(_.detail.valid) --> validityStateBus.writer
      )
      //-- End
    },
    DemoPanel("DatePicker with Minimum and Maximum Date - 1/1/2020 - 4/5/2020 display-format='yyyy-MM-dd'")(
      //-- Begin: DatePicker with Minimum and Maximum Date - 1/1/2020 - 4/5/2020 display-format='yyyy-MM-dd'
      DatePicker(
        _.displayFormat := "yyyy-MM-dd",
        _.valueFormat   := "yyyy-MM-dd",
        _.minDateStr    := "2020-01-01",
        _.maxDateStr    := "2020-05-04"
      )
      //-- End
    ),
    DemoPanel("DatePicker with different Display and Value Formats") {
      //-- Begin: DatePicker with different Display and Value Formats
      // The user sees (and types) dates in the "long" format, while the value is in the "yyyy-MM-dd" format
      val valueVar = Var("2024-12-24")
      div(
        Label(child.text <-- valueVar.signal.map(value => s"Current value: $value")),
        br(),
        DatePicker(
          _.displayFormat := "long",
          _.valueFormat   := "yyyy-MM-dd",
          _.value         := valueVar.now(),
          _.events.onValidDateChange --> valueVar.writer
        )
      )
      //-- End
    },
    DemoPanel("DatePicker with Clear Icon")(
      //-- Begin: DatePicker with Clear Icon
      DatePicker(_.showClearIcon := true, _.value := "2024-12-24")
      //-- End
    ),
    DemoPanel("DatePicker with Japanese Calendar Type")(
      //-- Begin: DatePicker with Japanese Calendar Type
      DatePicker(_.primaryCalendarType := CalendarType.Japanese)
      //-- End
    )
  )

  // enabling Japanese calendar.
  //noinspection ScalaUnusedExpression
  CalendarType.Japanese.importObject

}
