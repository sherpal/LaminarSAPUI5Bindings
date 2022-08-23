package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}
import org.scalajs.dom.HTMLElement

object ShellBarExample extends Example("ShellBar") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
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
          _.slots.profile := Avatar(img(src := "/images/avatars/sherpal.png")),
          _.slots.logo := img(src := "/images/avatars/scala-logo.png"),
          _.slots.startButton := Button(_.icon := IconName.`nav-back`),
          _.item(_.icon := IconName.disconnected, _.text := "Disconnected"),
          _.item(_.icon := IconName.`incoming-call`, _.text := "Incoming Calls", _.count := "4"),
          _.slots.searchField := Input(),
          _.slots.menuItems := UList.item("Application 1"),
          _.slots.menuItems := UList.item("Application 2"),
          _.slots.menuItems := UList.item("Application 3"),
          _.slots.menuItems := UList.item("Application 4"),
          _.slots.menuItems := UList.item("Application 5"),
          _.events.onProfileClick.map(_.detail.targetRef) --> openPopoverBus.writer
        ),
        Popover(
          inContext(el => openPopoverBus.events --> Observer[HTMLElement](el.ref.showAt)),
          _.placementType := PopoverPlacementType.Bottom,
          div(Title(padding := "0.25rem 1rem 0rem 1rem", "sherpal")),
                      div(
              UList(
                _.separators := ListSeparator.None,
                _.item(_.icon := IconName.`sys-find`, "App Finder"),
                _.item(_.icon := IconName.settings, "Settings"),
                _.item(_.icon := IconName.edit, "Edit Home Page"),
                _.item(_.icon := IconName.`sys-help`, "Help"),
                _.item(_.icon := IconName.log, "Sign out")
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
