package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}
import be.doeraene.webcomponents.ui5.datatypes.AccessibilityAttributes
import org.scalajs.dom.HTMLElement

object SideNavigationExample extends Example("SideNavigation") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Side Navigation in Application") {
      //-- Begin: Side Navigation in Application
      val toggleCollapseBus: EventBus[Unit] = new EventBus

      val collapsedSignal = toggleCollapseBus.events.scanLeft(false)((collapsed, _) => !collapsed)
      div(
        ShellBar(
          _.primaryTitle   := "UI5 Web Components",
          _.secondaryTitle := "The Best Run SAP",
          _.slots.startButton := Button(
            _.icon := IconName.menu,
            _.events.onClick.mapTo(()) --> toggleCollapseBus.writer
          )
        ),
        SideNavigation(
          _.collapsed <-- collapsedSignal,
          _.group(
            _.text     := "Group 1",
            _.expanded := true,
            _.item(_.text := "Home", _.icon := IconName.home),
            _.item(
              _.text     := "People",
              _.expanded := true,
              _.icon     := IconName.group,
              _.subItem(_.text := "From My Team"),
              _.subItem(_.text := "From Other Team")
            )
          ),
          _.group(
            _.text := "Group 2",
            _.item(_.text := "Locations", _.icon := IconName.`locate-me`, _.selected := true),
            _.item(
              _.text := "Events",
              _.icon := IconName.calendar,
              _.subItem(_.text := "Local"),
              _.subItem(_.text := "Others")
            )
          ),
          _.slots.fixedItems := SideNavigation.item(_.text := "Useful Links", _.icon := IconName.`chain-link`),
          _.slots.fixedItems := SideNavigation.item(_.text := "History", _.icon := IconName.history)
        )
      )
      //-- End
    },
    DemoPanel("Side Navigation with tags and action items") {
      //-- Begin: Side Navigation with tags and action items
      val dialogOpenVar = Var(false)
      val lastClickedVar = Var("Nothing clicked yet")

      def newTag(text: String) = Tag(
        _.design        := TagDesign.Set2,
        _.colourScheme  := ColourScheme._6,
        _.hideStateIcon := true,
        text
      )

      div(
        SideNavigation(
          height := "350px",
          _.events.onItemClick.map(event => s"Clicked: ${event.detail.item.text}") --> lastClickedVar.writer,
          _.item(_.text := "Home", _.icon := IconName.home, _.selected := true),
          _.item(
            _.text      := "Analytics",
            _.icon      := IconName.`business-objects-experience`,
            _.slots.tag := newTag("Beta"),
            _.subItem(_.text := "Reports"),
            _.subItem(_.text := "Dashboards", _.slots.tag := newTag("New"))
          ),
          _.item(_.text := "Archive", _.icon := IconName.`folder-blank`, _.disabled := true),
          _.slots.fixedItems := SideNavigation.item(
            _.text                    := "Create Item",
            _.icon                    := IconName.add,
            _.design                  := SideNavigationItemDesign.Action,
            _.unselectable            := true,
            _.accessibilityAttributes := AccessibilityAttributes(hasPopup = AriaHasPopup.Dialog),
            _.events.onClick.mapTo(true) --> dialogOpenVar.writer
          ),
          _.slots.fixedItems := SideNavigation.item(
            _.text         := "External Link",
            _.icon         := IconName.`chain-link`,
            _.href         := "https://ui5.github.io/webcomponents/",
            _.target       := LinkTarget._blank,
            _.unselectable := true
          )
        ),
        Text(child.text <-- lastClickedVar.signal),
        Dialog(
          _.headerText := "Create Item",
          _.open <-- dialogOpenVar.signal,
          _.events.onClose.mapTo(false) --> dialogOpenVar.writer,
          Input(_.placeholder := "Item name"),
          _.slots.footer := Bar(
            _.design           := BarDesign.Footer,
            _.slots.endContent := Button("Close", _.events.onClick.mapTo(false) --> dialogOpenVar.writer)
          )
        )
      )
      //-- End
    }
  )

}
