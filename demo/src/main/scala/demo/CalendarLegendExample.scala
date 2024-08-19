package demo

import demo.helpers.Example
import demo.helpers.FetchDemoPanelFromGithub
import com.raquo.laminar.api.L.*
import demo.helpers.DemoPanel
import be.doeraene.webcomponents.ui5.CalendarLegend
import be.doeraene.webcomponents.ui5.configkeys.CalendarLegendItemType

object CalendarLegendExample extends Example("CalendarLegend") {

  def component(using demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo): HtmlElement = div(
    DemoPanel("CalendarLegendItem Types") {
      //-- Begin: CalendarLegendItem Types
      CalendarLegend(
        CalendarLegendItemType.allValues
          .filter(_.value.startsWith("Type"))
          .map(tpe =>
            CalendarLegend.item(
              _.text := tpe.value,
              _.tpe  := tpe
            )
          )
      )
      //-- End
    },
    DemoPanel("Hide CalendarLegendItems") {
      //-- Begin: Hide CalendarLegendItems
      CalendarLegend(
        _.hideNonWorkingDay := true,
        _.hideWorkingDay    := true,
        _.hideToday         := true,
        _.hideSelectedDay   := true,
        CalendarLegendItemType.allValues
          .filter(_.value.startsWith("Type"))
          .map(tpe =>
            CalendarLegend.item(
              _.text := tpe.value,
              _.tpe  := tpe
            )
          )
      ) //-- End
    }
  )

}
