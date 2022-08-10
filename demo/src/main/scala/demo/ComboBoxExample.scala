package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, MTG}

object ComboBoxExample extends Example("ComboBox") {

  private def someItems(cb: ComboBox.type) = List(
    cb.item(_.text := "Item 1"),
    cb.item(_.text := "Item 2"),
    cb.item(_.text := "Item 3")
  )

  private val someCountries    = List("Austria", "Belgium", "Bulgaria", "Germany", "United Kingdom", "Kazakhstan")
  private val someCountryCodes = List("AT", "BE", "BG", "DE", "UK", "KZ")

  private val someOtherCountries =
    List("Argentina", "Australia", "Austria", "Barhain", "Belgium", "Brazil", "Canada", "Chile")

  def component: HtmlElement = div(
    DemoPanel("Basic Example")(
      //-- Begin: Basic Example
      div(
        ValueState.allValues.map(valueState =>
          ComboBox(_.placeholder := "Enter value", _.valueState := valueState, someItems)
        )
      )
      //-- End
    ),
    DemoPanel("Disabled and Readonly properties")(
      //-- Begin: Disabled and Readonly properties
      div(
        ComboBox(_.value := "Disabled", _.disabled := true, someItems),
        ComboBox(_.value := "Readonly", _.readonly := true, someItems)
      )
      //-- End
    ),
    DemoPanel("Filters (StartsWithPerTerm(default), StartsWith, Contains)")(
      //-- Begin: Filters (StartsWithPerTerm(default), StartsWith, Contains)
      div(
        ComboBoxFilter.allValues.map(filter =>
          ComboBox(
            _.filter := filter,
            _.placeholder := s"Filter is: ${filter.value}",
            _ => someCountries.map(country => ComboBox.item(_.text := country))
          )
        )
      )
      //-- End
    ),
    DemoPanel("ComboBox with Two-Column Layout Items")(
      //-- Begin: ComboBox with Two-Column Layout Items
      ComboBox(
        _.placeholder := "Two-column layout",
        _ =>
          someCountries
            .zip(someCountryCodes)
            .map((country, code) =>
              ComboBox.item(
                _.text := country,
                _.additionalText := code
              )
            )
      )
      //-- End
    ),
    DemoPanel("ComboBox with Grouping of Items")(
      //-- Begin: ComboBox with Grouping of Items
      ComboBox(
        _.placeholder := "ComboBox with grouping of suggestions",
        _ =>
          someOtherCountries
            .groupBy(_.head)
            .toList
            .flatMap((firstLetter, countries) =>
              ComboBox.group(_.text := firstLetter.toString()) +: countries.sorted.map(country =>
                ComboBox.item(_.text := country)
              )
            )
      )
      //-- End
    )
  )

}
