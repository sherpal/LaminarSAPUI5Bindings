package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}
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
          _.primaryTitle := "UI5 Web Components",
          _.secondaryTitle := "The Best Run SAP",
          _.showCoPilot := true,
          _.slots.startButton := Button(
            _.icon := IconName.menu,
            _.events.onClick.mapTo(()) --> toggleCollapseBus.writer
          )
        ),
        SideNavigation(
          _.collapsed <-- collapsedSignal,
          _.item(_.text := "Home", _.icon := IconName.home),
          _.item(
            _.text := "People",
            _.expanded := true,
            _.icon := IconName.group,
            _.subItem(_.text := "From My Team"),
            _.subItem(_.text := "From Other Team")
          ),
          _.item(_.text := "Locations", _.icon := IconName.`locate-me`, _.selected := true),
          _.item(
            _.text := "Events",
            _.icon := IconName.calendar,
            _.subItem(_.text := "Local"),
            _.subItem(_.text := "Others")
          ),
          _.slots.fixedItems := SideNavigation.item(_.text := "Useful Links", _.icon := IconName.`chain-link`),
          _.slots.fixedItems := SideNavigation.item(_.text := "History", _.icon := IconName.history)
        )
      )
      //-- End
    }
  )

}
