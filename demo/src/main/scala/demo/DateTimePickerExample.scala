package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime
import scala.util.Try

object DateTimePickerExample extends Example("DateTimePicker") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("DateTimePicker") {
      //-- Begin: DateTimePicker
      val localDateTimeFormatter                  = DateTimeFormatter.ofPattern("d MMM y, HH:mm:ss")
      val valueUpdateBus: EventBus[LocalDateTime] = new EventBus
      div(
        Label(_ =>
          child.text <-- valueUpdateBus.events.startWithNone.map {
            case None           => "No value selected yet"
            case Some(dateTime) => s"Selected value: $dateTime"
          }
        ),
        br(),
        DateTimePicker(
          _.events.onInput
            .map(_.detail.value)
            // Wrap in Try because input triggers even if element is not valid
            .map(value => Try(LocalDateTime.parse(value, localDateTimeFormatter)).toOption)
            .collect { case Some(dateTime) => dateTime } --> valueUpdateBus.writer,
          _.events.onChange
            .map(_.detail.value) // no need for Try shenanigans, change does not trigger for invalid values
            .map(LocalDateTime.parse(_, localDateTimeFormatter)) --> valueUpdateBus.writer
        )
      )
      //-- End
    },
    DemoPanel("DateTimePicker with format-pattern") {
      //-- Begin: DateTimePicker with format-pattern
      div(
        DateTimePicker(_.formatPattern := "dd/MM/yyyy, hh:mm aa", _.value := "13/04/2020, 09:16 AM"),
        DateTimePicker(_.formatPattern := "yyyy-MM-dd-hh:mm:ss aa", _.value := "2020-04-13-04:16:16 AM"),
        DateTimePicker(_.formatPattern := "dd/MM/yyyy, hh:mm:ss aa", _.value := "13/04/2020, 03:16:16 AM")
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
