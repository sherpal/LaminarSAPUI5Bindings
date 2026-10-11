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
      //-- Begin: Basic MultiComboBox
      div(
        MultiComboBox(
          _.placeholder    := "Type your value",
          _.selectedValues := List("UI5"),
          _.item(_.value := "UI5", _.text := "UI5")
        ),
        MultiComboBox(
          _.readonly       := true,
          _.value          := "Readonly combo",
          _.selectedValues := List("UI5"),
          _.item(_.value := "UI5", _.text := "UI5")
        ),
        MultiComboBox(
          _.disabled       := true,
          _.value          := "Disabled combo",
          _.selectedValues := List("UI5"),
          _.item(_.value := "UI5", _.text := "UI5")
        )
      )
      //-- End
    ),
    DemoPanel("MultiComboBox with items")(
      //-- Begin: MultiComboBox with items
      MultiComboBox(
        _.placeholder    := "Choose your countries",
        width            := "500px",
        _.selectedValues := countries.take(1),
        countries.map(country => MultiComboBox.item(_.text := country, _.value := country))
      )
      //-- End
    ),
    DemoPanel("MultiComboBox with free text input")(
      //-- Begin: MultiComboBox with free text input
      MultiComboBox(
        _.placeholder    := "Choose your countries",
        width            := "500px",
        _.noValidation   := true,
        _.selectedValues := countries.zipWithIndex.collect { case (country, index) if index % 3 == 0 => country },
        countries.map(country => MultiComboBox.item(_.text := country, _.value := country))
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
              countries.drop(index * 3).take(3).map(country => MultiComboBox.item(_.text := country))
            )
        }
      )
      //-- End
    ),
    DemoPanel("MultiComboBox with Select All and Clear Icon")(
      //-- Begin: MultiComboBox with Select All and Clear Icon
      MultiComboBox(
        _.placeholder   := "Choose your countries",
        width           := "500px",
        _.showSelectAll := true,
        _.showClearIcon := true,
        countries.map(country => MultiComboBox.item(_.text := country))
      )
      //-- End
    ),
    DemoPanel("Keeping selected values in sync with a Var") {
      //-- Begin: Keeping selected values in sync with a Var
      val countryCodes                        = List("AR", "BE", "BG", "CA", "CO", "HR", "DK")
      val selectedCodesVar: Var[List[String]] = Var(List("BE"))
      div(
        MultiComboBox(
          _.placeholder     := "Choose your countries",
          width             := "500px",
          _.selectedValues <-- selectedCodesVar.signal,
          countries
            .zip(countryCodes)
            .map((country, code) => MultiComboBox.item(_.text := country, _.value := code, _.additionalText := code)),
          _.events.onSelectionChange.map(_.target.selectedValues) --> selectedCodesVar.writer
        ),
        div(
          marginTop := "0.5rem",
          "Selected country codes: ",
          child.text <-- selectedCodesVar.signal.map(_.mkString(", ")),
          Button(
            marginLeft := "1em",
            "Select all European countries",
            _.events.onClick.mapTo(List("BE", "BG", "HR", "DK")) --> selectedCodesVar.writer
          )
        )
      )
      //-- End
    },
    DemoPanel("MultiComboBox with Custom Items")(
      //-- Begin: MultiComboBox with Custom Items
      MultiComboBox(
        _.placeholder := "Choose team members",
        width         := "500px",
        List("Alice" -> "Developer", "Bob" -> "Designer", "Charlie" -> "Product Owner").map((name, role) =>
          MultiComboBox.itemCustom(
            _.text  := name,
            _.value := name.toLowerCase,
            div(
              display.flex,
              alignItems.center,
              gap := "0.5rem",
              Avatar(_.size := AvatarSize.XS, _.initials := AvatarInitials.One(name.head)),
              div(div(fontWeight.bold, name), div(fontSize := "0.75rem", role))
            )
          )
        )
      )
      //-- End
    ),
    DemoPanel("MultiComboBox with Grouping of Items") {
      //-- Begin: MultiComboBox with Grouping of Items
      MultiComboBox(
        _.placeholder := "Select a country",
        _.group(
          _.headerText := "Asia",
          _.item(_.text := "Afghanistan"),
          _.item(_.text := "China"),
          _.item(_.text := "India"),
          _.item(_.text := "Indonesia")
        ),
        _.group(
          _.headerText := "Europe",
          _.item(_.text := "Austria"),
          _.item(_.text := "Belgium"),
          _.item(_.text := "Germany"),
          _.item(_.text := "Italy")
        ),
        _.group(_.headerText := "North America", _.item(_.text := "Canada"), _.item(_.text := "United States"))
      )
      //-- End
    }
  )

}
