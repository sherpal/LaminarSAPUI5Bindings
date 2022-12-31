package demo

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object SliderExample extends Example("Slider") {

  def webComponent: WebComponent = Slider

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic Slider") {
      //-- Begin: Basic Slider
      val selectedValueVar: Var[Double] = Var(0)
      div(
        Label(child.text <-- selectedValueVar.signal.map(value => s"Selected value: $value")),
        br(),
        Slider(_.value <-- selectedValueVar.signal, _.events.onInput.map(_.target.value) --> selectedValueVar.writer)
      )
      //-- End
    },
    DemoPanel("Slider with Tooltip") {
      //-- Begin: Slider with Tooltip
      Slider(_.min := 0, _.max := 20, _.showTooltip := true)
      //-- End
    },
    DemoPanel("Disabled Slider with Tickmarks and Labels") {
      //-- Begin: Disabled Slider with Tickmarks and Labels
      Slider(_.min := 20, _.max := 100, _.disabled := true, _.labelInterval := 5, _.showTickmarks := true)
      //-- End
    },
    DemoPanel("Slider Tooltip, Tickmarks and Labels") {
      //-- Begin: Slider Tooltip, Tickmarks and Labels
      Slider(
        _.min := -20,
        _.max := 20,
        _.step := 2,
        _.value := 12,
        _.showTooltip := true,
        _.labelInterval := 2,
        _.showTickmarks := true
      )
      //-- End
    }
  )

}
