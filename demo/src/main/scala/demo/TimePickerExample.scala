package demo

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object TimePickerExample extends Example("TimePicker") {

  def webComponent: WebComponent = TimePicker

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
          _.formatPattern := "hh:mm:ss a",
          _.events.onChange.map(_.target.value) --> selectedValueBus
        )
      )
      //-- End
    },
    DemoPanel("TimePicker with value-state and valueStateMessage") {
      //-- Begin: TimePicker with value-state and valueStateMessage
      TimePicker(_.valueState := ValueState.Error, _.slots.valueStateMessage := div("Please provide a valid value"))
      //-- End
    }
  )

}
