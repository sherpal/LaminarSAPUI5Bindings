package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object SegmentedButtonExample extends Example("SegmentedButton") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic SegmentedButton")(
      //-- Begin: Basic SegmentedButton
      SegmentedButton(
        _.accessibleName := "Geographic location",
        _.item(_ => "Map"),
        _.item(_ => "Satellite", _.pressed := true),
        _.item(_ => "Terrain")
      )
      //-- End
    ),
    DemoPanel("SegmentedButton with Icons")(
      //-- Begin: SegmentedButton with Icons
      SegmentedButton(
        _.item(_.icon := IconName.employee, _.pressed := true),
        _.item(_.icon := IconName.menu),
        _.item(_.icon := IconName.factory)
      )
      //-- End
    ),
    DemoPanel("SegmentedButton with 5 SegmentedButtonItems")(
      //-- Begin: SegmentedButton with 5 SegmentedButtonItems
      SegmentedButton(
        _.item(_ => "Item"),
        _.item(_ => "Pressed SegmentedButtonItem With Bigger Text", _.pressed := true),
        _.item(_ => "Item"),
        _.item(_ => "SegmentedButtonItem"),
        _.item(_ => "Press me")
      )
      //-- End
    )
  )

}
