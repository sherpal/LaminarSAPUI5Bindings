package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object DynamicDateRangeExample extends Example("DynamicDateRange") {

  import DynamicDateRange.DynamicDateRangeOperator.*

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement =
    div(
      h1("DynamicDateRange"),
      DemoPanel("Example")(
        //-- Begin: Example
        div(
          DynamicDateRange(
            _.options := Vector(Date, Tomorrow, DateRange),
            _.events.onChange.map(_.detail.value) --> Observer[Any](x => org.scalajs.dom.console.log(x))
          )
        )
        //-- End
      )
    )

}
