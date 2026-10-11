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
      NotificationList(
        _.group(
          _.titleText := "Some high priority notifications",
          _.item(
            _.showClose := true,
            _.titleText := "Some notification was triggered!",
            _.state     := ValueState.Critical
          ),
          _.item(
            _.showClose := true,
            _.titleText := "Some other notification was triggered!",
            _.state     := ValueState.Critical
          )
        ),
        _.group(
          _.titleText := "Some medium priority notifications",
          _.item(
            _.showClose := true,
            _.titleText := "Some medium notification was triggered!",
            _.state     := ValueState.Information
          ),
          _.item(
            _.showClose := true,
            _.titleText := "Some other medium notification was triggered!",
            _.state     := ValueState.Information
          )
        )
      )
      //-- End
    },
    DemoPanel("Growing NotificationListGroupItem") {
      //-- Begin: Growing NotificationListGroupItem
      val maxNumberOfNotifications = 12
      val numberOfNotificationsVar = Var(3)
      NotificationList(
        _.group(
          _.titleText := "Notifications loaded on demand",
          _.growing <-- numberOfNotificationsVar.signal.map(n =>
            if n < maxNumberOfNotifications then Growing.Button else Growing.None
          ),
          _.events.onLoadMore --> numberOfNotificationsVar.updater[Any]((n, _) => n + 3),
          children <-- numberOfNotificationsVar.signal.map(n =>
            (1 to n).toList.map(index =>
              NotificationListItem(
                _.titleText  := s"Notification number $index",
                _.importance := (
                  if index % 4 == 0 then NotificationListItemImportance.Important
                  else NotificationListItemImportance.Standard
                ),
                s"This is the content of notification number $index."
              )
            )
          )
        )
      )
      //-- End
    }
  )

}
