package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}
import org.scalajs.dom.HTMLElement

object MenuExample extends Example("Menu") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic Menu") {
      //-- Begin: Basic Menu
      // feed the bus to open or close the menu
      val openMenuBus: EventBus[Boolean] = new EventBus

      val menuOpenerId = "basic-menu-opener-id"

      div(
        Button(idAttr := menuOpenerId, "Open Menu", _.events.onClick.mapTo(true) --> openMenuBus.writer),
        Menu(
          _.open    <-- openMenuBus.events,
          _.openerId := menuOpenerId,
          _.item(_.text := "New File", _.icon   := IconName.`add-document`),
          _.item(_.text := "New Folder", _.icon := IconName.`add-folder`, _.disabled := true),
          _.separator(),
          _.item(_.text := "Open", _.icon := IconName.`open-folder`),
          _.item(_.text := "Close"),
          _.separator(),
          _.item(_.text := "Preferences", _.icon := IconName.`action-settings`),
          _.item(_.text := "Exit", _.icon        := IconName.`journey-arrive`)
        )
      )
      //-- End
    },
    DemoPanel("Menu with Sub-menu items") {
      //-- Begin: Menu with Sub-menu items
      // feed the bus to open or close the menu
      val openMenuBus: EventBus[Boolean] = new EventBus

      val menuOpenerId = "menu-with-sub-menu-items-opener-id"

      div(
        Button(idAttr := menuOpenerId, "Open Menu", _.events.onClick.mapTo(true) --> openMenuBus.writer),
        Menu(
          _.open    <-- openMenuBus.events,
          _.openerId := menuOpenerId,
          _.item(_.text := "New File", _.icon   := IconName.`add-document`, _.additionalText := "Ctrl+N"),
          _.item(_.text := "New Folder", _.icon := IconName.`add-folder`, _.disabled         := true),
          _.separator(),
          _.item(
            _.text := "Open",
            _.icon := IconName.`open-folder`,
            _.item(
              _.text := "Open Locally",
              _.icon := IconName.`open-folder`,
              _.item(_.text := "Open from C"),
              _.item(_.text := "Open from D"),
              _.item(_.text := "Open from E")
            ),
            _.item(_.text := "Open from Cloud")
          ),
          _.item(
            _.text := "Save",
            _.icon := IconName.save,
            _.item(
              _.text := "Save Locally",
              _.icon := IconName.save,
              _.item(_.text := "Save from C", _.icon := IconName.save),
              _.item(_.text := "Save from D", _.icon := IconName.save),
              _.item(_.text := "Save from E", _.icon := IconName.save)
            )
          ),
          _.item(_.text := "Close"),
          _.separator(),
          _.item(_.text := "Preferences", _.icon := IconName.`action-settings`),
          _.item(_.text := "Exit", _.icon        := IconName.`journey-arrive`)
        )
      )
      //-- End
    }
  )

}
