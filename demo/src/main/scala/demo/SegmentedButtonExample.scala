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
        _.item("Map"),
        _.item("Satellite", _.selected := true),
        _.item("Terrain")
      )
      //-- End
    ),
    DemoPanel("SegmentedButton with Icons")(
      //-- Begin: SegmentedButton with Icons
      SegmentedButton(
        _.item(_.icon := IconName.employee, _.selected := true),
        _.item(_.icon := IconName.menu),
        _.item(_.icon := IconName.factory)
      )
      //-- End
    ),
    DemoPanel("SegmentedButton with 5 SegmentedButtonItems")(
      //-- Begin: SegmentedButton with 5 SegmentedButtonItems
      SegmentedButton(
        _.item("Item"),
        _.item("Pressed SegmentedButtonItem With Bigger Text", _.selected := true),
        _.item("Item"),
        _.item("SegmentedButtonItem"),
        _.item("Press me")
      )
      //-- End
    ),
    DemoPanel("SegmentedButton with multi-select") {
      val selectedItemsVar = Var(Vector.empty[String])
      //-- Begin: SegmentedButton with multi-select
      div(
        SegmentedButton(
          _.selectionMode := SegmentedButtonMode.Multiple,
          _.item("First Item", _.accessibleName  := "first"),
          _.item("Second Item", _.accessibleName := "second"),
          _.item("Third Item", _.accessibleName  := "third"),
          SegmentedButton.events.onSelectionChange.map(
            _.detail.selectedItems.map(_.maybeAccessibleName.get)
          ) --> selectedItemsVar.writer
        ),
        div(child.text <-- selectedItemsVar.signal.map(values => s"Selected values are: ${values.mkString(", ")}"))
      )
      //-- End
    }
  )

}
