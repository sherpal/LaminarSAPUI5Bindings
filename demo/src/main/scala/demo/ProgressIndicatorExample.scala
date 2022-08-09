package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example}

object ProgressIndicatorExample extends Example("ProgressIndicator") {

  def component: HtmlElement = div(
    DemoPanel(
      "Basic Progress Indicator",
      div(
        ProgressIndicator(_.value := 0),
        ProgressIndicator(_.value := 25),
        ProgressIndicator(_.value := 75),
        ProgressIndicator(_.value := 100)
      )
    ),
    DemoPanel(
      "Progress Indicator With Custom Display Value",
      ProgressIndicator(
        _.value := 25,
        _.displayValue := "Custom Display Value"
      )
    ),
    DemoPanel(
      "Progress Indicator With Value State",
      div(
        ValueState.allValues
          .zip(10 to 100 by 20)
          .map((valueState, value) => ProgressIndicator(_.value := value, _.valueState := valueState))
      )
    ),
    DemoPanel(
      "Progress Indicator With Custom Sizes",
      div(
        ProgressIndicator(_.value := 25, _ => height := "50px", _ => width := "200px"),
        ProgressIndicator(_.value := 75, _ => height := "50px", _ => width := "200px")
      )
    )
  )

}
