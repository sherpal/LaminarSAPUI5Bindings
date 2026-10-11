package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object TimePickerExample extends Example("TimePicker") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic TimePicker") {
      //-- Begin: Basic TimePicker
      val selectedValueBus: EventBus[String] = new EventBus
      div(
        Label(child.text <-- selectedValueBus.events.map(value => s"Currently selected: $value")),
        br(),
        TimePicker(
          _.events.onChange.map(_.target.value) --> selectedValueBus
        )
      )
      //-- End
    },
    DemoPanel("TimePicker in twelve hours format") {
      //-- Begin: TimePicker in twelve hours format
      val selectedValueBus: EventBus[String] = new EventBus
      div(
        Label(child.text <-- selectedValueBus.events.map(value => s"Currently selected: $value")),
        br(),
        TimePicker(
          _.displayFormat := "hh:mm:ss a",
          _.valueFormat   := "hh:mm:ss a",
          _.events.onChange.map(_.target.value) --> selectedValueBus
        )
      )
      //-- End
    },
    DemoPanel("TimePicker with different Display and Value Formats") {
      //-- Begin: TimePicker with different Display and Value Formats
      // The user sees a twelve hours format, while the value is stored in the "HH:mm" format
      val valueVar = Var("14:30")
      div(
        Label(child.text <-- valueVar.signal.map(value => s"Current value: $value")),
        br(),
        TimePicker(
          _.displayFormat := "hh:mm a",
          _.valueFormat   := "HH:mm",
          _.value         := valueVar.now(),
          _.events.onChange.map(_.detail).filter(_.valid).map(_.value) --> valueVar.writer
        )
      )
      //-- End
    },
    DemoPanel("TimePicker with value-state and valueStateMessage") {
      //-- Begin: TimePicker with value-state and valueStateMessage
      TimePicker(_.valueState := ValueState.Negative, _.slots.valueStateMessage := div("Please provide a valid value"))
      //-- End
    }
  )

}
