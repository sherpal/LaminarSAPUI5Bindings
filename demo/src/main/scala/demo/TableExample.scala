package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, MTG}
import org.scalajs.dom

import scala.scalajs.js

object TableExample extends Example("Table") {

  def component: HtmlElement = div(
    styleTag(s"""
    |.header {
    |    display: flex;
    |    justify-content: space-between;
    |    align-items: center;
    |    flex-wrap: wrap;
    |    height: fit-content;
    |    padding: 0.5rem;
    |}
    |""".stripMargin),
    DemoPanel("Basic Table") {
      //-- Begin: Basic Table
      val toggleStickyHeaderBus: EventBus[Unit] = new EventBus
      val stickyHeaderSignal = toggleStickyHeaderBus.events.foldLeft(false)((isSticky, _) => !isSticky)
      div(
        div(
          className := "header",
          span("Cards table - resize your browser to make some columns pop-in"),
          Button(_.events.onClick.mapTo(()) --> toggleStickyHeaderBus, _ => "Toggle Sticky Column Header")
        ),
        Table(
          _.stickyColumnHeader <-- stickyHeaderSignal,
          _.slots.columns := Table.column(_ => width := "12rem", _ => span(lineHeight := "1.4rem", "Card")),
          _.slots.columns := Table.column(_.minWidth := 800, _ => span(lineHeight := "1.4rem", "Type")),
          _.slots.columns := Table.column(
            _.minWidth := 600,
            _.popinText := "Comment",
            _.demandPopin := true,
            _ => span(lineHeight := "1.4rem", "Comment")
          ),
          _.slots.columns := Table.column(_ => span(lineHeight := "1.4rem", "Cost")),
          _ =>
            MTG.cards.map(card =>
              Table.row(
                _.cell(_ => card.name),
                _.cell(_ => card.tpe),
                _.cell(_ => card.comment),
                _.cell(_ => card.cost)
              )
            )
        )
      )
      //-- End
    },
    DemoPanel("Table in SingleSelect-mode")(
      //-- Begin: Table in SingleSelect-mode
      Table(
        _.mode := TableMode.SingleSelect,
        _.slots.columns := Table.column(_ => width := "12rem", _ => span(lineHeight := "1.4rem", "Card")),
        _.slots.columns := Table.column(_.minWidth := 800, _ => span(lineHeight := "1.4rem", "Type")),
        _.slots.columns := Table.column(
          _.minWidth := 600,
          _.popinText := "Comment",
          _.demandPopin := true,
          _ => span(lineHeight := "1.4rem", "Comment")
        ),
        _.slots.columns := Table.column(_ => span(lineHeight := "1.4rem", "Cost")),
        _ =>
          MTG.cards.map(card =>
            Table.row(
              _.cell(_ => card.name),
              _.cell(_ => card.tpe),
              _.cell(_ => card.comment),
              _.cell(_ => card.cost)
            )
          )
      )
      //-- End
    ),
    DemoPanel("Table in MultiSelect mode")(
      //-- Begin: Table in MultiSelect mode"
      Table(
        _.events.onSelectionChange.map(_.detail.selectedRows.map(_.dataset.toMap)) --> Observer(println),
        _.mode := TableMode.MultiSelect,
        _.slots.columns := Table.column(_ => width := "12rem", _ => span(lineHeight := "1.4rem", "Card")),
        _.slots.columns := Table.column(_.minWidth := 800, _ => span(lineHeight := "1.4rem", "Type")),
        _.slots.columns := Table.column(
          _.minWidth := 600,
          _.popinText := "Comment",
          _.demandPopin := true,
          _ => span(lineHeight := "1.4rem", "Comment")
        ),
        _.slots.columns := Table.column(_ => span(lineHeight := "1.4rem", "Cost")),
        _ =>
          MTG.cards.map(card =>
            Table.row(
              _ => dataAttr("card-name") := card.name,
              _.cell(_ => card.name),
              _.cell(_ => card.tpe),
              _.cell(_ => card.comment),
              _.cell(_ => card.cost)
            )
          )
      )
      //-- End
    ),
    DemoPanel("Table with No Data")(
      //-- Begin: Table with No Data
      Table(
        _.noDataText := "No Data",
        _.slots.columns := Table.column(_ => width := "12rem", _ => span(lineHeight := "1.4rem", "Card")),
        _.slots.columns := Table.column(_.minWidth := 800, _ => span(lineHeight := "1.4rem", "Type")),
        _.slots.columns := Table.column(
          _.minWidth := 600,
          _.popinText := "Comment",
          _.demandPopin := true,
          _ => span(lineHeight := "1.4rem", "Comment")
        ),
        _.slots.columns := Table.column(_ => span(lineHeight := "1.4rem", "Cost"))
      )
      //-- End
    ),
    DemoPanel("Growing Table with 'More' button") {
      //-- Begin: Growing Table with 'More' button
      val loadMoreBus: EventBus[Unit] = new EventBus
      val totalNumberOfCards          = MTG.cards.length
      val numberOfLoadedCards         = loadMoreBus.events.delay(3000).mapTo(4).foldLeft(4)(_ + _)

      val cardsToDisplay = numberOfLoadedCards.map(MTG.cards.take)

      val busyState = EventStream
        .merge(
          loadMoreBus.events.mapTo(+1),
          numberOfLoadedCards.changes.mapTo(-1)
        )
        .foldLeft(0)(_ + _)
        .map(_ > 0)

      Table(
        _.busy <-- busyState,
        _.growing := TableGrowingMode.Button,
        _.growingButtonSubtext <-- numberOfLoadedCards
          .map(_ min totalNumberOfCards)
          .map(n => s"[$n / $totalNumberOfCards]"),
        _.events.onLoadMore.mapTo(()) --> loadMoreBus,
        _.slots.columns := Table.column(_ => width := "12rem", _ => span(lineHeight := "1.4rem", "Card")),
        _.slots.columns := Table.column(_.minWidth := 800, _ => span(lineHeight := "1.4rem", "Type")),
        _.slots.columns := Table.column(
          _.minWidth := 600,
          _.popinText := "Comment",
          _.demandPopin := true,
          _ => span(lineHeight := "1.4rem", "Comment")
        ),
        _.slots.columns := Table.column(_ => span(lineHeight := "1.4rem", "Cost")),
        _ =>
          children <-- cardsToDisplay.map(
            _.map(card =>
              Table.row(
                _ => dataAttr("card-name") := card.name,
                _.cell(_ => card.name),
                _.cell(_ => card.tpe),
                _.cell(_ => card.comment),
                _.cell(_ => card.cost)
              )
            )
          )
      )
      //-- End
    },
    DemoPanel("Growing Table on Scroll") {
      //-- Begin: Growing Table on Scroll
      val loadMoreBus: EventBus[Unit] = new EventBus
      val totalNumberOfCards          = MTG.cards.length
      val numberOfLoadedCards         = loadMoreBus.events.delay(3000).mapTo(4).foldLeft(4)(_ + _)

      val cardsToDisplay = numberOfLoadedCards.map(MTG.cards.take)

      val busyState = EventStream
        .merge(
          loadMoreBus.events.mapTo(+1),
          numberOfLoadedCards.changes.mapTo(-1)
        )
        .foldLeft(0)(_ + _)
        .map(_ > 0)

      div(
        overflowY := "scroll",
        height := "400px",
        Table(
          _.busy <-- busyState,
          _.growing := TableGrowingMode.Scroll,
          _.events.onLoadMore.mapTo(()) --> loadMoreBus,
          _.slots.columns := Table.column(_ => width := "12rem", _ => span(lineHeight := "1.4rem", "Card")),
          _.slots.columns := Table.column(_.minWidth := 800, _ => span(lineHeight := "1.4rem", "Type")),
          _.slots.columns := Table.column(
            _.minWidth := 600,
            _.popinText := "Comment",
            _.demandPopin := true,
            _ => span(lineHeight := "1.4rem", "Comment")
          ),
          _.slots.columns := Table.column(_ => span(lineHeight := "1.4rem", "Cost")),
          _ =>
            children <-- cardsToDisplay.map(
              _.map(card =>
                Table.row(
                  _ => dataAttr("card-name") := card.name,
                  _.cell(_ => card.name),
                  _.cell(_ => card.tpe),
                  _.cell(_ => card.comment),
                  _.cell(_ => card.cost)
                )
              )
            )
        )
      )
      //-- End
    },
    DemoPanel("Table with grouping (SingleSelect)")(
      //-- Begin: Table with grouping (SingleSelect)
      Table(
        _.mode := TableMode.SingleSelect,
        _.slots.columns := Table.column(_ => Label(_ => "City")),
        _.slots.columns := Table.column(_ => Label(_ => "Population")),
        _.slots.columns := Table.column(_ => Label(_ => "Country")),
        _.groupRow("Country: Belgium"),
        _.row(_.cell(_ => "Brussels"), _.cell(_ => "1.2 millions"), _.cell(_ => "Belgium")),
        _.row(_.cell(_ => "Antwerp"), _.cell(_ => "500,000"), _.cell(_ => "Belgium")),
        _.groupRow("Country: France"),
        _.row(_.cell(_ => "Paris"), _.cell(_ => "10 millions"), _.cell(_ => "France")),
        _.row(_.cell(_ => "Lille"), _.cell(_ => "1 million"), _.cell(_ => "France"))
      )
      //-- End
    )
  )

}
