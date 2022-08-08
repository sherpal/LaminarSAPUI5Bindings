package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example}
import be.doeraene.webcomponents.ui5.configkeys.IconName.example

object ListExample extends Example("List") {

  private val countries = List("Argentina", "Belgium", "Bulgaria", "Canada", "Columbia", "Croatia", "Denmark")

  def component: HtmlElement = div(
    DemoPanel(
      "Basic List",
      UList(
        _ => width := "100%",
        _.Li(
          _.icon := IconName.`nutrition-activity`,
          _.description := "Tropical plant with an edible fruit",
          _.additionalText := "In-stock",
          _.additionalTextState := ValueState.Success,
          _ => "Pineapple"
        ),
        _.Li(
          _.icon := IconName.`nutrition-activity`,
          _.description := "Occurs between red and yellow",
          _.additionalText := "Expires",
          _.additionalTextState := ValueState.Warning,
          _ => "Orange"
        ),
        _.Li(
          _.icon := IconName.`nutrition-activity`,
          _.description := "The yellow lengthy fruit",
          _.additionalText := "Re-stock",
          _.additionalTextState := ValueState.Information,
          _ => "Blueberry"
        ),
        _.Li(
          _.icon := IconName.`nutrition-activity`,
          _.description := "The tropical stone fruit",
          _.additionalText := "Re-stock",
          _.additionalTextState := ValueState.Error,
          _ => "Mango"
        )
      )
    ),
    DemoPanel(
      "List with growing='Scroll'", {
        val fruits = LazyList.from(0).map { fruitIndex =>
          val additionalTextState = ValueState.allValues(fruitIndex % ValueState.allValues.size)
          UList.Li(
            _.icon := IconName.`nutrition-activity`,
            _.description := s"This is the description of fruit $fruitIndex",
            _.additionalText := additionalTextState.value,
            _.additionalTextState := additionalTextState,
            _ => s"Fruit $fruitIndex"
          )
        }

        val listGrowingBus: EventBus[Unit] = new EventBus
        val numberOfFruitsToDisplaySignal  = listGrowingBus.events.delay(200).mapTo(5).foldLeft(5)(_ + _)

        val fruitsToDisplaySignal = numberOfFruitsToDisplaySignal.map(fruits.take)

        UList(
          _.events.onLoadMore.mapTo(()) --> listGrowingBus.writer,
          _ => children <-- fruitsToDisplaySignal,
          _ => height := "300px",
          _.growing := ListGrowingMode.Scroll,
          _.headerText <-- numberOfFruitsToDisplaySignal.map(numberOfFruits =>
            s"List of fruits (currently $numberOfFruits displayed)."
          )
        )
      }
    ),
    DemoPanel(
      "List in Single-selection Mode", {
        val maybeSelectedCountryVar: Var[Option[String]] = Var(Option.empty)

        UList(
          _.mode := ListMode.SingleSelect,
          _.headerText <-- maybeSelectedCountryVar.signal.map(maybeCountry =>
            s"Select a country:${maybeCountry.fold("")(country => s" (selected: $country)")}"
          ),
          _.events.onSelectionChange
            .map(_.detail.maybeSelectedItem.flatMap(_.dataset.get("countryName"))) --> maybeSelectedCountryVar.writer,
          _ =>
            countries.map { country =>
              val isInactive = country == countries.last
              UList.Li(
                _ => country ++ (if isInactive then " (Item with 'type' set to 'Inactive')" else ""),
                _ => dataAttr("country-name") := country,
                _.tpe.maybe(Option.when(isInactive)(ListItemType.Inactive))
              )
            }
        )
      }
    ),
    DemoPanel(
      "List in Multi-selection Mode", {
        val selectedItemsVar: Var[List[String]] = Var(List.empty)
        val selectedItemsInfoSignal = selectedItemsVar.signal.map {
          case Nil   => "No selected items"
          case items => s"Selected items: ${items.mkString(", ")}"
        }

        UList(
          _.mode := ListMode.MultiSelect,
          _.headerText <-- selectedItemsInfoSignal.map(selectedItems =>
            s"Multiple selection is possible: ($selectedItems)"
          ),
          _ => countries.map(country => UList.Li(_ => country, _ => dataAttr("country-name") := country)),
          _.events.onSelectionChange.map(
            _.detail.selectedItems.flatMap(_.dataset.get("countryName"))
          ) --> selectedItemsVar.writer
        )

      }
    ),
    DemoPanel("Buzy List", UList(_.busy := true, _.headerText := "Fetching data...")),
    DemoPanel(
      "List with GroupHeaders", {
        def expansionListItem(expansion: String) = (_: UList.type).Li(
          _.iconEnd := true,
          _.icon := IconName.`slim-arrow-right`,
          _ => expansion
        )
        UList(
          _.mode := ListMode.MultiSelect,
          _.headerText := "Expansion list",
          _.group(_ => "Mirrodin Block"),
          expansionListItem("Mirrodin"),
          expansionListItem("Darksteel"),
          expansionListItem("Fifth Dawn"),
          _.group(_ => "Kamigawa Block"),
          expansionListItem("Champions of Kamigawa"),
          expansionListItem("Betrayers of Kamigawa"),
          expansionListItem("Saviors of Kamigawa"),
          _.group(_ => "Ravnica Block"),
          expansionListItem("Ravnica: City of Guilds"),
          expansionListItem("Guildpact"),
          expansionListItem("Dissension")
        )
      }
    ),
    DemoPanel(
      "List in Delete Mode",
      UList(
        _.mode := ListMode.Delete,
        _.headerText := "Note: The list items removal is up to application developers",
        _ => countries.map(country => UList.Li(_ => country))
      )
    ),
    DemoPanel(
      "List with No Data",
      UList(_.headerText := "Products", _.noDataText := "No Data Available", _.separators := ListSeparator.None)
    ),
    DemoPanel(
      "List Item Speration Types",
      div(
        UList(
          _.headerText := "No separators",
          _.separators := ListSeparator.None,
          _ => countries.take(3).map(country => UList.Li(_ => country, _.icon := IconName.world))
        ),
        UList(
          _.headerText := "Inner separators",
          _.separators := ListSeparator.Inner,
          _ => countries.drop(3).take(3).map(country => UList.Li(_ => country, _.icon := IconName.`hello-world`))
        )
      )
    )
  )

}
