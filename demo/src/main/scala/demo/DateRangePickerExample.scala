package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub, MTG}

object DateRangePickerExample extends Example("DateRangePicker") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic DateRangePicker") {
      //-- Begin: Basic DateRangePicker
      DateRangePicker()
      //-- End
    },
    DemoPanel("DateRangePicker with Minimum and Maximum Date - 1/1/2020 - 4/5/2020 display-format='dd/MM/yyyy'") {
      //-- Begin: DateRangePicker with Minimum and Maximum Date - 1/1/2020 - 4/5/2020 display-format='dd/MM/yyyy'
      DateRangePicker(
        _.displayFormat := "dd/MM/yyyy",
        _.valueFormat   := "dd/MM/yyyy",
        _.minDateRaw    := "01/01/2020",
        _.maxDateRaw    := "04/05/2020"
      )
      //-- End
    },
    DemoPanel("DateRangePicker with display-format='long'") {
      //-- Begin: DateRangePicker with display-format='long'
      DateRangePicker(_.displayFormat := "long")
      //-- End
    },
    DemoPanel("DateRangePicker showing two months") {
      //-- Begin: DateRangePicker showing two months
      DateRangePicker(_.showTwoMonths := true)
      //-- End
    },
    DemoPanel("DateRangePicker with Clear Icon") {
      //-- Begin: DateRangePicker with Clear Icon
      // Without valueFormat, the value is expected in ISO format (yyyy-MM-dd)
      DateRangePicker(_.showClearIcon := true, _.value := "2021-03-31 - 2021-04-09")
      //-- End
    },
    DemoPanel("Disabled DateRangePicker") {
      //-- Begin: Disabled DateRangePicker
      DateRangePicker(_.disabled := true, _.value := "2021-03-31 - 2021-04-09")
      //-- End
    },
    DemoPanel("Readonly DateRangePicker") {
      //-- Begin: Readonly DateRangePicker
      DateRangePicker(_.readonly := true, _.value := "2021-03-31 - 2021-04-09")
      //-- End
    }
  )

}
