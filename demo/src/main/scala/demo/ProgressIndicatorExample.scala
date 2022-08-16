package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object ProgressIndicatorExample extends Example("ProgressIndicator") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic Progress Indicator")(
      //-- Begin: Basic Progress Indicator
      div(
        ProgressIndicator(_.value := 0),
        ProgressIndicator(_.value := 25),
        ProgressIndicator(_.value := 75),
        ProgressIndicator(_.value := 100)
      )
      //-- End
    ),
    DemoPanel("Progress Indicator With Custom Display Value")(
      //-- Begin: Progress Indicator With Custom Display Value
      ProgressIndicator(
        _.value := 25,
        _.displayValue := "Custom Display Value"
      )
      //-- End
    ),
    DemoPanel("Progress Indicator With Value State")(
      //-- Begin: Progress Indicator With Value State
      div(
        ValueState.allValues
          .zip(10 to 100 by 20)
          .map((valueState, value) => ProgressIndicator(_.value := value, _.valueState := valueState))
      )
      //-- End
    ),
    DemoPanel("Progress Indicator With Custom Sizes")(
      //-- Begin: Progress Indicator With Custom Sizes
      div(
        ProgressIndicator(_.value := 25, _ => height := "50px", _ => width := "200px"),
        ProgressIndicator(_.value := 75, _ => height := "50px", _ => width := "200px")
      )
      //-- End
    )
  )

}
