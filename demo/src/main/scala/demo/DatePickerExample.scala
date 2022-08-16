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
      _.design := MessageStripDesign.Warning,
      _ =>
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
        _.placeholder := "Delivery date...",
        _.slots.valueStateMessage := div("The value is not valid. Please provide a valid value."),
        _.valueState <-- validityStateBus.events.map(if _ then ValueState.None else ValueState.Error),
        _.events.onChange.map(_.detail.valid) --> validityStateBus.writer
      )
      //-- End
    },
    DemoPanel("DatePicker with Minimum and Maximum Date - 1/1/2020 - 4/5/2020 format-pattern='yyyy-MM-dd'")(
      //-- Begin: DatePicker with Minimum and Maximum Date - 1/1/2020 - 4/5/2020 format-pattern='yyyy-MM-dd'
      DatePicker(
        _.formatPattern := "yyyy-MM-dd",
        _.minDate := LocalDate.of(2020, 1, 1),
        _.maxDate := LocalDate.of(2020, 5, 4)
      )
      //-- End
    ),
    DemoPanel("DatePicker with Japanese Calendar Type")(
      //-- Begin: DatePicker with Japanese Calendar Type
      DatePicker(_.primaryCalendarType := CalendarType.Japanese)
      //-- End
    )
  )

  // enabling Japanese calendar.
  CalendarType.JapaneseImport

}
