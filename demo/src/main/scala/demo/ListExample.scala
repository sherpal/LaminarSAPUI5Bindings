package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}
import demo.helpers.MTG
import be.doeraene.webcomponents.ui5.eventtypes.MoveEventDetail

object ListExample extends Example("List") {

  private val countries = List("Argentina", "Belgium", "Bulgaria", "Canada", "Columbia", "Croatia", "Denmark")

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic List")(
      //-- Begin: Basic List
      UList(
        width := "100%",
        _.item(
          _.icon                := IconName.`nutrition-activity`,
          _.description         := "Tropical plant with an edible fruit",
          _.additionalText      := "In-stock",
          _.additionalTextState := ValueState.Positive,
          "Pineapple"
        ),
        _.item(
          _.icon                := IconName.`nutrition-activity`,
          _.description         := "Occurs between red and yellow",
          _.additionalText      := "Expires",
          _.additionalTextState := ValueState.Critical,
          "Orange"
        ),
        _.item(
          _.icon                := IconName.`nutrition-activity`,
          _.description         := "The yellow lengthy fruit",
          _.additionalText      := "Re-stock",
          _.additionalTextState := ValueState.Information,
          "Blueberry"
        ),
        _.item(
          _.icon                := IconName.`nutrition-activity`,
          _.description         := "The tropical stone fruit",
          _.additionalText      := "Re-stock",
          _.additionalTextState := ValueState.Negative,
          "Mango"
        )
      )
      //-- End
    ),
    DemoPanel("List with growing='Scroll'") {
      //-- Begin: List with growing='Scroll'
      val fruits = LazyList.from(0).map { fruitIndex =>
        val additionalTextState = ValueState.allValues(fruitIndex % ValueState.allValues.size)
        UList.item(
          _.icon                := IconName.`nutrition-activity`,
          _.description         := s"This is the description of fruit $fruitIndex",
          _.additionalText      := additionalTextState.value,
          _.additionalTextState := additionalTextState,
          s"Fruit $fruitIndex"
        )
      }

      val listGrowingBus: EventBus[Unit] = new EventBus
      val numberOfFruitsToDisplaySignal  = listGrowingBus.events.delay(200).mapTo(5).scanLeft(5)(_ + _)

      val fruitsToDisplaySignal = numberOfFruitsToDisplaySignal.map(fruits.take)

      UList(
        _.events.onLoadMore.mapTo(()) --> listGrowingBus.writer,
        children <-- fruitsToDisplaySignal,
        height    := "300px",
        _.growing := ListGrowingMode.Scroll,
        _.headerText <-- numberOfFruitsToDisplaySignal.map(numberOfFruits =>
          s"List of fruits (currently $numberOfFruits displayed)."
        )
      )
      //-- End
    },
    DemoPanel("List in Single-selection Mode") {
      //-- Begin: List in Single-selection Mode
      val maybeSelectedCountryVar: Var[Option[String]] = Var(Option.empty)

      UList(
        _.selectionMode := ListMode.Single,
        _.headerText <-- maybeSelectedCountryVar.signal.map(maybeCountry =>
          s"Select a country:${maybeCountry.fold("")(country => s" (selected: $country)")}"
        ),
        _.events.onSelectionChange
          .map(_.detail.maybeSelectedItem.flatMap(_.dataset.get("countryName"))) --> maybeSelectedCountryVar.writer,
        countries.map { country =>
          val isInactive = country == countries.last
          UList.item(
            country ++ (if isInactive then " (Item with 'type' set to 'Inactive')" else ""),
            dataAttr("country-name") := country,
            _.tpe.maybe(Option.when(isInactive)(ListItemType.Inactive))
          )
        }
      )
      //-- End
    },
    DemoPanel("List in Multi-selection Mode") {
      //-- Begin: List in Multi-selection Mode
      val selectedItemsVar: Var[List[String]] = Var(List.empty)
      val selectedItemsInfoSignal = selectedItemsVar.signal.map {
        case Nil   => "No selected items"
        case items => s"Selected items: ${items.mkString(", ")}"
      }

      UList(
        _.selectionMode := ListMode.Multiple,
        _.headerText <-- selectedItemsInfoSignal.map(selectedItems =>
          s"Multiple selection is possible: ($selectedItems)"
        ),
        countries.map(country =>
          UList.item(
            country,
            dataAttr("country-name") := country,
            _.highlight              := (if country == "Belgium" then ValueState.Positive else ValueState.None)
          )
        ),
        _.events.onSelectionChange.map(
          _.detail.selectedItems.flatMap(_.dataset.get("countryName"))
        ) --> selectedItemsVar.writer
      )
      //-- End
    },
    DemoPanel("Loading List")(
      //-- Begin: Loading List
      UList(_.loading := true, _.headerText := "Fetching data...")
      //-- End
    ),
    DemoPanel("List with GroupHeaders") {
      //-- Begin: List with GroupHeaders
      def expansionListItem(expansion: String) = UList.item(
        _.iconEnd := true,
        _.icon    := IconName.`slim-arrow-right`,
        expansion
      )
      UList(
        _.selectionMode := ListMode.Multiple,
        _.headerText    := "Expansion list",
        _.grouped(
          _.headerText := "Mirrodin Block",
          expansionListItem("Mirrodin"),
          expansionListItem("Darksteel"),
          expansionListItem("Fifth Dawn")
        ),
        _.grouped(
          _.headerText := "Kamigawa Block",
          expansionListItem("Champions of Kamigawa"),
          expansionListItem("Betrayers of Kamigawa"),
          expansionListItem("Saviors of Kamigawa")
        ),
        _.grouped(
          _.headerText := "Ravnica Block",
          expansionListItem("Ravnica: City of Guilds"),
          expansionListItem("Guildpact"),
          expansionListItem("Dissension")
        )
      )
      //-- End
    },
    DemoPanel("List in Delete Mode")(
      //-- Begin: List in Delete Mode
      UList(
        _.selectionMode := ListMode.Delete,
        _.headerText    := "Note: The list items removal is up to application developers",
        countries.map(country => UList.item(country))
      )
      //-- End
    ),
    DemoPanel("List with No Data")(
      //-- Begin: List with No Data
      UList(_.headerText := "Products", _.noDataText := "No Data Available", _.separators := ListSeparator.None)
      //-- End
    ),
    DemoPanel("List Item Speratior Types")(
      //-- Begin: List Item Speratior Types
      div(
        UList(
          _.headerText := "No separators",
          _.separators := ListSeparator.None,
          countries.take(3).map(country => UList.item(country, _.icon := IconName.world))
        ),
        UList(
          _.headerText := "Inner separators",
          _.separators := ListSeparator.Inner,
          countries.slice(3, 6).map(country => UList.item(country, _.icon := IconName.`hello-world`))
        )
      )
      //-- End
    ),
    DemoPanel("List Item with delete button slot (since 1.9.0)") {
      //-- Begin: List Item with delete button slot (since 1.9.0)
      val countryDeleteBus = new EventBus[String]
      div(
        UList(
          _.selectionMode := ListMode.Delete,
          countries
            .map(country =>
              UList.item(
                country,
                _.icon := IconName.world,
                _.slots.deleteButton := Button(
                  _.design := ButtonDesign.Transparent,
                  _.icon   := IconName.delete,
                  onClick.mapTo(country) --> countryDeleteBus.writer
                )
              )
            )
        ),
        Toast(_.showFromTextEvents(countryDeleteBus.events.map(country => s"Should delete $country")))
      )
      //-- End
    },
    DemoPanel("Example of drag and drop (since 2.0.0)") {
      //-- Begin: Example of drag and drop (since 2.0.0)
      case class CardWithPlacement(card: MTG.Card, index: Int)
      val cardsVar = Var(MTG.cards)

      val moveBus: EventBus[MoveEventDetail[UList.item.Ref]] = new EventBus
      val moveHandler = moveBus.events.withCurrentValueOf(cardsVar.signal).map { (eventDetail, cards) =>
        val sourceIndex           = eventDetail.source.element.dataset("index").toInt
        val destinationIndexShift = if eventDetail.destination.placement == "Before" then 0 else 1
        val destinationIndex      = eventDetail.destination.element.dataset("index").toInt + destinationIndexShift

        val cardsSourceRemoved = cards.patch(sourceIndex, Nil, 1)
        val cardsAfterChange =
          if destinationIndex < sourceIndex then cardsSourceRemoved.patch(destinationIndex, List(cards(sourceIndex)), 0)
          else cardsSourceRemoved.patch(destinationIndex - 1, List(cards(sourceIndex)), 0)
        cardsAfterChange
      }

      UList(
        _.headerText := "You can change items order!",
        children <-- cardsVar.signal
          .map(_.zipWithIndex.map(CardWithPlacement(_, _)))
          .map(_.map { card =>
            UList.item(_.movable := true, dataAttr("index") := card.index.toString, card.card.name)
          }),
        _.events.onMoveOver.preventDefault --> Observer.empty,
        _.events.onMove.map(_.detail) --> moveBus.writer,
        moveHandler --> cardsVar.writer
      )
      //-- End
    }
  )

}
