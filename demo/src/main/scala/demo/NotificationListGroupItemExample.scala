package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object NotificationListGroupItemExample extends Example("NotificationListGroupItem") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("NotificationListGroupItem") {
      //-- Begin: NotificationListGroupItem
      UList(
        _.headerText := "Notifications grouped",
        _.notificationGroup(
          _.showClose := true,
          _.showCounter := true,
          _.priority := Priority.High,
          _.titleText := "Some high priority notifications",
          _.item(
            _.showClose := true,
            _.titleText := "Some notification was triggered!",
            _.priority := Priority.High
          ),
          _.item(
            _.showClose := true,
            _.titleText := "Some other notification was triggered!",
            _.priority := Priority.High
          )
        ),
        _.notificationGroup(
          _.showClose := true,
          _.showCounter := true,
          _.priority := Priority.Medium,
          _.titleText := "Some medium priority notifications",
          _.item(
            _.showClose := true,
            _.titleText := "Some medium notification was triggered!",
            _.priority := Priority.Medium
          ),
          _.item(
            _.showClose := true,
            _.titleText := "Some other medium notification was triggered!",
            _.priority := Priority.Medium
          )
        )
      )
      //-- End
    }
  )

}
