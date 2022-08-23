package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object RangeSliderExample extends Example("RangeSlider") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic Range Slider") {
      //-- Begin: Basic Range Slider
      val startValueVar: Var[Double] = Var(0)
      val endValueVar: Var[Double]   = Var(20)

      div(
        Label(          child.text <-- startValueVar.signal
            .combineWith(endValueVar.signal)
            .map((start, end) => s"Currently selected range: ($start, $end)")
        ),
        RangeSlider(
          _.endValue   <-- endValueVar.signal,
          _.startValue <-- startValueVar.signal,
          _.events.onChange.map(_.target.startValue) --> startValueVar.writer,
          _.events.onChange.map(_.target.endValue) --> endValueVar.writer
        )
      )
      //-- End
    },
    DemoPanel("Range Slider with Custom 'min', 'max', 'startValue' and 'endValue' Properties") {
      //-- Begin: Range Slider with Custom 'min', 'max', 'startValue' and 'endValue' Properties
      RangeSlider(_.min := 100, _.max := 200, _.startValue := 120, _.endValue := 150)
      //-- End
    },
    DemoPanel("Range Slider with Tooltips") {
      //-- Begin: Range Slider with Tooltips
      RangeSlider(_.startValue := 3, _.endValue := 13, _.showTooltip := true)
      //-- End
    },
    DemoPanel("Range Slider with Tickmarks and Custom Step") {
      //-- Begin: Range Slider with Tickmarks and Custom Step
      RangeSlider(_.step := 2, _.startValue := 4, _.endValue := 12, _.showTickmarks := true)
      //-- End
    },
    DemoPanel("Range Slider with Tooltips, Tickmarks and Labels") {
      //-- Begin: Range Slider with Tooltips, Tickmarks and Labels
      RangeSlider(
        _.min := 0,
        _.max := 112,
        _.step := 2,
        _.startValue := 4,
        _.endValue := 12,
        _.showTooltip := true,
        _.labelInterval := 2,
        _.showTickmarks := true
      )
      //-- End
    }
  )

}
