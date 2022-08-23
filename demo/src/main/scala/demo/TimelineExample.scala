package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object TimelineExample extends Example("Timeline") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic Timeline")(
      //-- Begin: Basic Timeline
      Timeline(
        _.item(
          _.titleText := "called",
          _.subtitleText := "20.02.2022 11:30",
          _.icon := IconName.phone,
          _.name := "John Smith",
          _.nameClickable := true,
          _.events.onNameClick.mapTo(()) --> Observer[Any](_ => println("Clicked on John Smith!"))
        ),
        _.item(
          _.titleText := "Weekly Sync - CP Design",
          _.subtitleText := "27.07.2022 (11:00 - 12:30)",
          _.icon := IconName.calendar,
          div("MR SOF02 2.43")
        ),
        _.item(
          _.titleText := "Video Converence Call - UI5",
          _.subtitleText := "31.01.2023 (12:00 - 13:00)",
          _.icon := IconName.calendar,
          div("Online meeting")
        )
      )
      //-- End
    ),
    DemoPanel("Horizontal timeline")(
      //-- Begin: Horizontal timeline
      Timeline(
        _.layout := TimelineLayout.Horizontal,
        _.item(
          _.titleText := "called",
          _.subtitleText := "20.02.2022 11:30",
          _.icon := IconName.phone,
          _.name := "John Smith",
          _.nameClickable := true
        ),
        _.item(
          _.titleText := "Weekly Sync - CP Design",
          _.subtitleText := "27.07.2022 (11:00 - 12:30)",
          _.icon := IconName.calendar,
          div("MR SOF02 2.43")
        ),
        _.item(
          _.titleText := "Video Converence Call - UI5",
          _.subtitleText := "31.01.2023 (12:00 - 13:00)",
          _.icon := IconName.calendar,
          div("Online meeting")
        )
      )
      //-- End
    )
  )

}
