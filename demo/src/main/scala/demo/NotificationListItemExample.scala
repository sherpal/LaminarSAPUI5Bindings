package demo

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub, MTG}
import org.scalajs.dom

object NotificationListItemExample extends Example("NotificationListItem") {

  def webComponent: WebComponent = NotificationListItem

  // I know it's a bit sketchy to put the below thing common, but in this case it's ok, just need to make sure that
  // the ids do not clash accross examples...
  //-- Begin Common
  // Each notification receives an id in its dataset, that is used to be removed when the delete icon is clicked
  val closeItemsBus: EventBus[dom.HTMLElement] = new EventBus

  val notifIdName = "notif-id"
  // Modifiers to be given to the notification with specified id. It will hide the item on delete
  def notifWithId(id: String): NotificationListItem.ModFunction = _ =>
    List(
      hidden <-- closeItemsBus.events.map(_.dataset("notifId")).filter(_ == id).mapTo(true).startWith(false),
      dataAttr(notifIdName) := id
    )
  //-- End Common

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("NotificationListItem") {
      //-- Begin: NotificationListItem
      // Each notification receives an id in its dataset, that is used to be removed when the delete icon is clicked
      val closeItemsBus: EventBus[dom.HTMLElement] = new EventBus

      val notifIdName = "notif-id"
      // Modifiers to be given to the notification with specified id. It will hide the item on delete
      def notifWithId(id: String): NotificationListItem.ModFunction = _ =>
        List(
          hidden <-- closeItemsBus.events.map(_.dataset("notifId")).filter(_ == id).mapTo(true).startWith(false),
          dataAttr(notifIdName) := id
        )

      UList(
        _.headerText := "Notifications",
        _.events.onItemClose.map(_.detail.item) --> closeItemsBus.writer,
        _.notificationItem(
          notifWithId("notif-1"),
          _.showClose := true,
          _.titleText := "New order (#2525) With a very long title - Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent feugiat, turpis vel scelerisque pharetra, tellus odio vehicula dolor, nec elementum lectus turpis at nunc.",
          _.priority := Priority.High,
          "And with a very long description and long labels of the action buttons - Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent feugiat, turpis vel scelerisque pharetra, tellus odio vehicula dolor, nec elementum lectus turpis at nunc.",
          _.slots.avatar := Avatar(_.size := AvatarSize.XS, img(src := MTG.manaSymbolsRefs("W"))),
          _.slots.footnotes := span("Monique Legrand"),
          _.slots.footnotes := span("2 Days")
        ),
        _.notificationItem(
          notifWithId("notif-2"),
          _.showClose := true,
          _.titleText := "New order (#2526) With a very long title - Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent feugiat, turpis vel scelerisque pharetra, tellus odio vehicula dolor, nec elementum lectus turpis at nunc.",
          _.priority := Priority.High,
          "And with a very long description and long labels of the action buttons - Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent feugiat, turpis vel scelerisque pharetra, tellus odio vehicula dolor, nec elementum lectus turpis at nunc.",
          _.slots.avatar := Avatar(_.size := AvatarSize.XS, img(src := MTG.manaSymbolsRefs("B"))),
          _.slots.footnotes := span("Alain Chevalier"),
          _.slots.footnotes := span("2 Days")
        )
      )
      //-- End
    },
    DemoPanel("NotificationListItem In ShellBar") {
      //-- Begin: NotificationListItem In ShellBar
      val openNotifPopoverBus: EventBus[dom.HTMLElement] = new EventBus
      div(
        ShellBar(
          _.primaryTitle := "Corporate Portal",
          _.slots.logo := img(src := "/images/avatars/scala-logo.png"),
          _.showNotifications := true,
          _.notificationsCount := "4",
          _.events.onNotificationsClick.map(_.detail.targetRef) --> openNotifPopoverBus.writer
        ),
        Popover(
          inContext(el => openNotifPopoverBus.events.map(el.ref -> _) --> Popover.showAtObserver),
          maxWidth := "600px",
          _.placementType := PopoverPlacementType.Bottom,
          _.horizontalAlign := PopoverHorizontalAlign.Right,
          UList(
            _.headerText := "Notifications",
            _.events.onItemClose.map(_.detail.item) --> closeItemsBus.writer,
            _.notificationItem(
              notifWithId("notif-shellbar-1"),
              _.showClose := true,
              _.titleText := "New order (#2525) With a very long title - Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent feugiat, turpis vel scelerisque pharetra, tellus odio vehicula dolor, nec elementum lectus turpis at nunc.",
              _.priority := Priority.High,
              "And with a very long description and long labels of the action buttons - Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent feugiat, turpis vel scelerisque pharetra, tellus odio vehicula dolor, nec elementum lectus turpis at nunc.",
              _.slots.avatar := Avatar(_.size := AvatarSize.XS, img(src := MTG.manaSymbolsRefs("W"))),
              _.slots.footnotes := span("Monique Legrand"),
              _.slots.footnotes := span("2 Days"),
              _.slots.actions := NotificationListItem.action(_.icon := IconName.accept, _.text := "Accept")
            ),
            _.notificationItem(
              notifWithId("notif-shellbar-2"),
              _.showClose := true,
              _.titleText := "New order (#2526) With a very long title - Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent feugiat, turpis vel scelerisque pharetra, tellus odio vehicula dolor, nec elementum lectus turpis at nunc.",
              _.priority := Priority.High,
              "And with a very long description and long labels of the action buttons - Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent feugiat, turpis vel scelerisque pharetra, tellus odio vehicula dolor, nec elementum lectus turpis at nunc.",
              _.slots.avatar := Avatar(_.size := AvatarSize.XS, img(src := MTG.manaSymbolsRefs("B"))),
              _.slots.footnotes := span("Alain Chevalier"),
              _.slots.footnotes := span("2 Days")
            )
          )
        )
      )
      //-- End
    }
  )

}
