package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object NavigationLayoutExample extends Example("NavigationLayoutExample") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic example") {
      //-- Begin: Basic example
      val sideExpandedVar = Var(true)
      NavigationLayout(
        _.mode <-- sideExpandedVar.signal.map(
          if _ then NavigationLayoutMode.Expanded else NavigationLayoutMode.Collapsed
        ),
        _.slots.header := Bar(
          Button(
            _.iconOnly := true,
            _.icon     := IconName.menu,
            onClick.mapToUnit --> sideExpandedVar.updater((current, _) => !current)
          )
        ),
        _.slots.sideContent := div("Menu Item"),
        div(
          Text("This is the main content.")
        )
      )
      //-- End
    }
  )

}
