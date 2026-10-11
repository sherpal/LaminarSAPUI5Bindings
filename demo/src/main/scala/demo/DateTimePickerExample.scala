package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}
import scala.util.Try

object DateTimePickerExample extends Example("DateTimePicker") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("DateTimePicker") {
      //-- Begin: DateTimePicker
      val valueUpdateBus: EventBus[String] = new EventBus
      div(
        Label(child.text <-- valueUpdateBus.events.startWithNone.map {
          case None           => "No value selected yet"
          case Some(dateTime) => s"Selected value: $dateTime"
        }),
        br(),
        DateTimePicker(
          _.events.onInput
            .map(_.detail.value) --> valueUpdateBus.writer,
          _.events.onChange
            .map(_.detail.value)  --> valueUpdateBus.writer
        )
      )
      //-- End
    },
    DemoPanel("DateTimePicker with display-format and value-format") {
      //-- Begin: DateTimePicker with display-format and value-format
      def picker(format: String, value: String) =
        DateTimePicker(_.displayFormat := format, _.valueFormat := format, _.value := value)

      div(
        picker("dd/MM/yyyy, hh:mm aa", "13/04/2020, 09:16 AM"),
        picker("yyyy-MM-dd-hh:mm:ss aa", "2020-04-13-04:16:16 AM"),
        picker("dd/MM/yyyy, hh:mm:ss aa", "13/04/2020, 03:16:16 AM"),
        // The value is stored in a different format than the one displayed
        DateTimePicker(
          _.displayFormat := "long",
          _.valueFormat   := "yyyy-MM-dd'T'HH:mm:ss",
          _.value         := "2020-04-13T15:16:16",
          _.showClearIcon := true
        )
      )
      //-- End
    },
    DemoPanel("DateTimePicker in states") {
      //-- Begin: DateTimePicker in states
      div(ValueState.allValues.map(state => DateTimePicker(_.valueState := state)))
      //-- End
    }
  )

}
