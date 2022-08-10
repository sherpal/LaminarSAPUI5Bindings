package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example}
import org.scalajs.dom.HTMLElement

object ShellBarExample extends Example("ShellBar") {

  def component: HtmlElement = div(
    DemoPanel("ShellBar") {
      //-- Begin: ShellBar
      val openPopoverBus: EventBus[HTMLElement] = new EventBus
      div(
        ShellBar(
          _.primaryTitle := "Corporate Portal",
          _.secondaryTitle := "Secondary title",
          _.notificationsCount := "99+",
          _.showNotifications := true,
          _.showProductSwitch := true,
          _.showCoPilot := true,
          _.slots.profile := Avatar(_ => img(src := "/images/avatars/sherpal.png")),
          _.slots.logo := img(src := "/images/avatars/scala-logo.png"),
          _.slots.startButton := Button(_.icon := IconName.`nav-back`),
          _.item(_.icon := IconName.disconnected, _.text := "Disconnected"),
          _.item(_.icon := IconName.`incoming-call`, _.text := "Incoming Calls", _.count := "4"),
          _.slots.searchField := Input(),
          _.slots.menuItems := UList.Li(_ => "Application 1"),
          _.slots.menuItems := UList.Li(_ => "Application 2"),
          _.slots.menuItems := UList.Li(_ => "Application 3"),
          _.slots.menuItems := UList.Li(_ => "Application 4"),
          _.slots.menuItems := UList.Li(_ => "Application 5"),
          _.events.onProfileClick.map(_.detail.targetRef) --> openPopoverBus.writer
        ),
        Popover(
          _ => inContext(el => openPopoverBus.events --> Observer[HTMLElement](el.ref.showAt)),
          _.placementType := PopoverPlacementType.Bottom,
          _ => div(Title(_ => padding := "0.25rem 1rem 0rem 1rem", _ => "sherpal")),
          _ =>
            div(
              UList(
                _.separators := ListSeparator.None,
                _.Li(_.icon := IconName.`sys-find`, _ => "App Finder"),
                _.Li(_.icon := IconName.settings, _ => "Settings"),
                _.Li(_.icon := IconName.edit, _ => "Edit Home Page"),
                _.Li(_.icon := IconName.`sys-help`, _ => "Help"),
                _.Li(_.icon := IconName.log, _ => "Sign out")
              )
            )
        )
      )
      //-- End
    },
    DemoPanel("Basic ShellBar")(
      //-- Begin: Basic ShellBar
      ShellBar(
        _.primaryTitle := "Corporate Portal",
        _.secondaryTitle := "secondary title",
        _.slots.profile := Avatar(_.icon := IconName.customer),
        _.slots.logo := img(src := "/images/avatars/scala-logo.png"),
        _.slots.startButton := Button(_.icon := IconName.`nav-back`)
      )
      //-- End
    )
  )

}
