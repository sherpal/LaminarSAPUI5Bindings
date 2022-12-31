package demo

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub, MTG}

object DateRangePickerExample extends Example("DateRangePicker") {

  def webComponent: WebComponent = DateRangePicker

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic DateRangePicker") {
      //-- Begin: Basic DateRangePicker
      DateRangePicker()
      //-- End
    },
    DemoPanel("DateRangePicker with Minimum and Maximum Date - 1/1/2020 - 4/5/2020 format-pattern='dd/MM/yyyy'") {
      //-- Begin: DateRangePicker with Minimum and Maximum Date - 1/1/2020 - 4/5/2020 format-pattern='dd/MM/yyyy'
      DateRangePicker(_.minDateRaw := "1/1/2020", _.maxDateRaw := "4/5/2020", _.formatPattern := "dd/MM/yyyy")
      //-- End
    },
    DemoPanel("DateRangePicker with format-pattern='long'") {
      //-- Begin: DateRangePicker with format-pattern='long'
      DateRangePicker(_.formatPattern := "long")
      //-- End
    },
    DemoPanel("Disabled DateRangePicker") {
      //-- Begin: Disabled DateRangePicker
      DateRangePicker(_.disabled := true, _.value := "Mar 31, 2021 - Apr 9, 2021")
      //-- End
    },
    DemoPanel("Readonly DateRangePicker") {
      //-- Begin: Readonly DateRangePicker
      DateRangePicker(_.readonly := true, _.value := "Mar 31, 2021 - Apr 9, 2021")
      //-- End
    }
  )

}
