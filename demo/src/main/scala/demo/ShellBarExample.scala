package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}
import be.doeraene.webcomponents.ui5.datatypes.{AccessibilityAttributes, ShellBarAccessibilityAttributes}
import org.scalajs.dom.HTMLElement

object ShellBarExample extends Example("ShellBar") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("ShellBar") {
      //-- Begin: ShellBar
      val openPopoverBus: EventBus[HTMLElement] = new EventBus

      val profileId = "shellbar-profile-id"

      div(
        ShellBar(
          _.primaryTitle       := "Corporate Portal",
          _.secondaryTitle     := "Secondary title",
          _.notificationsCount := "99+",
          _.showNotifications  := true,
          _.showProductSwitch  := true,
          _.slots.profile      := Avatar(idAttr := profileId, img(src := "/images/avatars/sherpal.png")),
          _.slots.logo         := img(src := "/images/avatars/scala-logo.png"),
          _.slots.startButton  := Button(_.icon := IconName.`nav-back`),
          _.item(_.icon := IconName.disconnected, _.text    := "Disconnected"),
          _.item(_.icon := IconName.`incoming-call`, _.text := "Incoming Calls", _.count := "4"),
          _.slots.searchField := Input(),
          _.slots.menuItems   := UList.item("Application 1"),
          _.slots.menuItems   := UList.item("Application 2"),
          _.slots.menuItems   := UList.item("Application 3"),
          _.slots.menuItems   := UList.item("Application 4"),
          _.slots.menuItems   := UList.item("Application 5"),
          _.events.onProfileClick.map(_.detail.targetRef) --> openPopoverBus.writer
        ),
        Popover(
          _.openerId  := profileId,
          _.open     <-- openPopoverBus.events.mapTo(true),
          _.placement := PopoverPlacementType.Bottom,
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
        _.primaryTitle      := "Corporate Portal",
        _.secondaryTitle    := "secondary title",
        _.slots.profile     := Avatar(_.icon := IconName.customer),
        _.slots.logo        := img(src := "/images/avatars/scala-logo.png"),
        _.slots.startButton := Button(_.icon := IconName.`nav-back`)
      )
      //-- End
    ),
    DemoPanel("ShellBar with Branding and Search") {
      //-- Begin: ShellBar with Branding and Search
      val allSuggestions = List("Laminar", "Scala", "Scala.js", "SAP UI5", "Web Components", "ShellBar")
      val searchValueVar = Var("")
      val lastEventVar   = Var("Nothing happened yet.")

      div(
        ShellBar(
          _.slots.branding := ShellBarBranding(
            _.slots.logo := img(src := "/images/avatars/scala-logo.png"),
            "Corporate Portal",
            _.events.onClick.mapTo("Branding clicked") --> lastEventVar.writer
          ),
          _.showNotifications  := true,
          _.notificationsCount := "3",
          _.slots.profile      := Avatar(_.initials := AvatarInitials.Two('S', 'H')),
          _.slots.assistant    := Button(_.icon := IconName.ai, _.tooltip := "Assistant"),
          _.slots.searchField := ShellBarSearch(
            _.placeholder   := "Search the portal",
            _.showClearIcon := true,
            _.events.onInput.map(_.target.value) --> searchValueVar.writer,
            _.events.onSearch.map(ev => s"Search triggered for '${ev.target.value}'") --> lastEventVar.writer,
            children <-- searchValueVar.signal.map { value =>
              allSuggestions
                .filter(_.toLowerCase.contains(value.toLowerCase))
                .map(suggestion => ShellBarSearch.item(_.text := suggestion, _.icon := IconName.history))
            }
          ),
          _.accessibilityAttributes := ShellBarAccessibilityAttributes(
            branding = AccessibilityAttributes(name = "Corporate Portal home")
          ),
          _.events.onSearchFieldClear.mapTo("Search field cleared") --> lastEventVar.writer,
          _.events.onNotificationsClick.mapTo("Notifications clicked") --> lastEventVar.writer
        ),
        Label(child.text <-- lastEventVar.signal)
      )
      //-- End
    },
    DemoPanel("ShellBar with Content") {
      //-- Begin: ShellBar with Content
      ShellBar(
        _.primaryTitle      := "Corporate Portal",
        _.slots.logo        := img(src := "/images/avatars/scala-logo.png"),
        _.slots.profile     := Avatar(_.initials := AvatarInitials.Two('S', 'H')),
        _.slots.content     := Tag(_.design := TagDesign.Set1, "Trial"),
        _.slots.content     := ShellBarSpacer(),
        _.slots.content     := Button(_.icon := IconName.home, _.design := ButtonDesign.Transparent, "Home"),
        _.item(
          _.icon                    := IconName.bell,
          _.text                    := "Alerts",
          _.count                   := "2",
          _.accessibilityAttributes := AccessibilityAttributes(hasPopup = AriaHasPopup.Dialog)
        )
      )
      //-- End
    }
  )

}
