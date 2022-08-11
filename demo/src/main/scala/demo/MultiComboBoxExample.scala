package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object MultiComboBoxExample extends Example("MultiComboBox") {

  private val countries = List("Argentina", "Belgium", "Bulgaria", "Canada", "Columbia", "Croatia", "Denmark")

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic MultiComboBox")(
      //-- Begin: Basic MultiComboBox"
      div(
        MultiComboBox(_.placeholder := "Type your value", _.item(_.selected := true, _.text := "UI5")),
        MultiComboBox(_.readonly := true, _.value := "Readonly combo", _.item(_.selected := true, _.text := "UI5")),
        MultiComboBox(_.disabled := true, _.value := "Disabled combo", _.item(_.selected := true, _.text := "UI5"))
      )
      //-- End
    ),
    DemoPanel("MultiComboBox with items")(
      //-- Begin: MultiComboBox with items
      MultiComboBox(
        _.placeholder := "Choose your countries",
        _ => width := "500px",
        _ =>
          countries.zipWithIndex.map((country, index) =>
            MultiComboBox.item(_.text := country, _.selected := (index == 0))
          )
      )
      //-- End
    ),
    DemoPanel("MultiComboBox with free text input")(
      //-- Begin: MultiComboBox with free text input
      MultiComboBox(
        _.placeholder := "Choose your countries",
        _ => width := "500px",
        _.allowCustomValues := true,
        _ =>
          countries.zipWithIndex.map((country, index) =>
            MultiComboBox.item(_.text := country, _.selected := (index % 3 == 0))
          )
      )
      //-- End
    ),
    DemoPanel("MultiComboBox with Value State")(
      //-- Begin: MultiComboBox with Value State
      div(
        ValueState.allValues.filterNot(_ == ValueState.Information).filterNot(_ == ValueState.None).zipWithIndex.map {
          (valueState, index) =>
            MultiComboBox(
              _.valueState := valueState,
              _ => countries.drop(index * 3).take(3).map(country => MultiComboBox.item(_.text := country))
            )
        }
      )
      //-- End
    )
  )

}
