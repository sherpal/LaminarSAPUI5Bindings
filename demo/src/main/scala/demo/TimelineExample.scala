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
          _.titleText     := "called",
          _.subtitleText  := "20.02.2022 11:30",
          _.icon          := IconName.phone,
          _.name          := "John Smith",
          _.nameClickable := true,
          _.events.onNameClick.mapTo(()) --> Observer[Any](_ => println("Clicked on John Smith!"))
        ),
        _.item(
          _.titleText    := "Weekly Sync - CP Design",
          _.subtitleText := "27.07.2022 (11:00 - 12:30)",
          _.icon         := IconName.calendar,
          div("MR SOF02 2.43")
        ),
        _.item(
          _.titleText    := "Video Converence Call - UI5",
          _.subtitleText := "31.01.2023 (12:00 - 13:00)",
          _.icon         := IconName.calendar,
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
          _.titleText     := "called",
          _.subtitleText  := "20.02.2022 11:30",
          _.icon          := IconName.phone,
          _.name          := "John Smith",
          _.nameClickable := true
        ),
        _.item(
          _.titleText    := "Weekly Sync - CP Design",
          _.subtitleText := "27.07.2022 (11:00 - 12:30)",
          _.icon         := IconName.calendar,
          div("MR SOF02 2.43")
        ),
        _.item(
          _.titleText    := "Video Converence Call - UI5",
          _.subtitleText := "31.01.2023 (12:00 - 13:00)",
          _.icon         := IconName.calendar,
          div("Online meeting")
        )
      )
      //-- End
    ),
    DemoPanel("Timeline with group")(
      //-- Begin: Timeline with group
      Timeline(
        _.layout := TimelineLayout.Vertical,
        _.group(
          _.groupName := "Events",
          _.item(
            _.titleText    := "Event",
            _.subtitleText := "18.06.2024 11:30",
            _.name         := "SAP D-com"
          ),
          _.item(
            _.titleText    := "Event",
            _.subtitleText := "19.06.2024 12:30",
            _.name         := "SAP Talk",
            _.icon         := IconName.calendar
          ),
          _.item(
            _.titleText    := "Event",
            _.subtitleText := "21.06.2024 18:30",
            _.name         := "SAP iXP Summer Party"
          )
        ),
        _.group(
          _.groupName := "Meetings",
          _.item(
            _.titleText    := "coming-up",
            _.subtitleText := "10.07.2024 11:30",
            _.name         := "Team Belgium Meeting",
            _.icon         := IconName.calendar
          ),
          _.item(
            _.titleText    := "coming-up",
            _.subtitleText := "20.08.2024 12:30",
            _.name         := "Team Belgium Planning",
            _.icon         := IconName.calendar
          ),
          _.item(
            _.titleText    := "coming-up",
            _.subtitleText := "22.08.2024 14:30",
            _.name         := "Team Belgium Retrospective",
            _.icon         := IconName.calendar
          )
        ),
        _.group(
          _.groupName := "Calls",
          _.item(
            _.titleText     := "made group call",
            _.subtitleText  := "20.09.2024 11:30",
            _.name          := "John Doe",
            _.icon          := IconName.calendar,
            _.nameClickable := true
          ),
          _.item(
            _.subtitleText  := "20.09.2024 12:30",
            _.name          := "John Doe",
            _.nameClickable := true,
            Avatar(_.initials := ('J', 'D')),
            Label("has ended the call")
          )
        )
      )
      //-- End
    )
  )

}
