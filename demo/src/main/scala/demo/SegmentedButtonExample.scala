package demo

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object SegmentedButtonExample extends Example("SegmentedButton") {

  def webComponent: WebComponent = SegmentedButton

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic SegmentedButton")(
      //-- Begin: Basic SegmentedButton
      SegmentedButton(
        _.accessibleName := "Geographic location",
        _.item("Map"),
        _.item("Satellite", _.pressed := true),
        _.item("Terrain")
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
        _.item("Item"),
        _.item("Pressed SegmentedButtonItem With Bigger Text", _.pressed := true),
        _.item("Item"),
        _.item("SegmentedButtonItem"),
        _.item("Press me")
      )
      //-- End
    )
  )

}
