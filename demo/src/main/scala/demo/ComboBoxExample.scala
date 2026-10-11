package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub, MTG}

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

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
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
            _.filter      := filter,
            _.placeholder := s"Filter is: ${filter.value}",
            someCountries.map(country => ComboBox.item(_.text := country))
          )
        )
      )
      //-- End
    ),
    DemoPanel("ComboBox with Two-Column Layout Items")(
      //-- Begin: ComboBox with Two-Column Layout Items
      ComboBox(
        _.placeholder := "Two-column layout",
        someCountries
          .zip(someCountryCodes)
          .map((country, code) =>
            ComboBox.item(
              _.text           := country,
              _.additionalText := code
            )
          )
      )
      //-- End
    ),
    DemoPanel("ComboBox with no type-ahead")(
      //-- Begin: ComboBox with no type-ahead
      ComboBox(
        _.placeholder := "No type-ahead",
        _.noTypeahead := true,
        someCountries
          .map(country =>
            ComboBox.item(
              _.text := country
            )
          )
      )
      //-- End
    ),
    DemoPanel("ComboBox with Clear Icon")(
      //-- Begin: ComboBox with Clear Icon
      ComboBox(
        _.placeholder   := "Clear icon",
        _.showClearIcon := true,
        _.value         := "Belgium",
        someCountries.map(country => ComboBox.item(_.text := country))
      )
      //-- End
    ),
    DemoPanel("ComboBox with Selected Value") {
      //-- Begin: ComboBox with Selected Value
      // The items display the country name, but we keep track of the country code via `selectedValue`.
      val selectedCodeVar: Var[Option[String]] = Var(Some("BE"))
      val lastTriggerVar: Var[Option[String]]  = Var(None)
      div(
        ComboBox(
          _.placeholder    := "Choose a country",
          _.selectedValue <-- selectedCodeVar.signal.map(_.getOrElse("")),
          someCountries
            .zip(someCountryCodes)
            .map((country, code) => ComboBox.item(_.text := country, _.value := code, _.additionalText := code)),
          _.events.onSelectionChange.map(_.detail.maybeItem.flatMap(_.value.toOption)) --> selectedCodeVar.writer,
          _.events.onSelectionChange.map(_.detail.trigger.map(_.value)) --> lastTriggerVar.writer
        ),
        div(
          marginTop := "0.5rem",
          "Selected country code: ",
          child.text <-- selectedCodeVar.signal.map(_.getOrElse("(none)")),
          child.text <-- lastTriggerVar.signal.map(_.fold("")(trigger => s" (selected via $trigger)"))
        )
      )
      //-- End
    },
    DemoPanel("ComboBox with Custom Items")(
      //-- Begin: ComboBox with Custom Items
      ComboBox(
        _.placeholder := "Custom items",
        someCountries
          .zip(someCountryCodes)
          .map((country, code) =>
            ComboBox.itemCustom(
              _.text  := country,
              _.value := code,
              div(
                display.flex,
                justifyContent.spaceBetween,
                alignItems.center,
                width := "100%",
                span(Icon(_.name := IconName.flag, marginRight := "0.5rem"), country),
                Tag(code)
              )
            )
          )
      )
      //-- End
    ),
    DemoPanel("ComboBox with Grouping of Items")(
      //-- Begin: ComboBox with Grouping of Items
      ComboBox(
        _.placeholder := "ComboBox with grouping of suggestions",
        someOtherCountries
          .groupBy(_.head)
          .toList
          .map((firstLetter, countries) =>
            ComboBox.group(
              _.headerText := firstLetter.toString(),
              countries.sorted.map(country => ComboBox.item(_.text := country))
            )
          )
      )
      //-- End
    )
  )

}
