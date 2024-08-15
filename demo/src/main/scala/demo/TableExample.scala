package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub, MTG}
import org.scalajs.dom

import scala.scalajs.js

object TableExample extends Example("Table") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    //-- Begin Common
    styleTag(s"""
    |.header {
    |    display: flex;
    |    justify-content: space-between;
    |    align-items: center;
    |    flex-wrap: wrap;
    |    height: fit-content;
    |    padding: 0.5rem;
    |}
    |""".stripMargin)
    //-- End Common
    ,
    DemoPanel("Basic Table") {
      //-- Begin: Basic Table
      val toggleStickyHeaderBus: EventBus[Unit] = new EventBus
      val stickyHeaderSignal = toggleStickyHeaderBus.events.scanLeft(false)((isSticky, _) => !isSticky)
      div(
        div(
          className := "header",
          span("Cards table - resize your browser to make some columns pop-in"),
          Button(_.events.onClick.mapTo(()) --> toggleStickyHeaderBus, "Toggle Sticky Column Header")
        ),
        compat.Table(
          _.stickyColumnHeader <-- stickyHeaderSignal,
          _.slots.columns       := compat.Table.column(width := "12rem", span(lineHeight := "1.4rem", "Card")),
          _.slots.columns       := compat.Table.column(_.minWidth := 800, span(lineHeight := "1.4rem", "Type")),
          _.slots.columns := compat.Table.column(
            _.minWidth    := 600,
            _.popinText   := "Comment",
            _.demandPopin := true,
            span(lineHeight := "1.4rem", "Comment")
          ),
          _.slots.columns := compat.Table.column(span(lineHeight := "1.4rem", "Cost")),
          MTG.cards.map(card =>
            compat.Table.row(
              _.cell(card.name),
              _.cell(card.tpe),
              _.cell(card.comment),
              _.cell(card.cost)
            )
          )
        )
      )
      //-- End
    },
    DemoPanel("Table in SingleSelect-mode")(
      //-- Begin: Table in SingleSelect-mode
      compat.Table(
        _.mode          := TableMode.SingleSelect,
        _.slots.columns := compat.Table.column(width := "12rem", span(lineHeight := "1.4rem", "Card")),
        _.slots.columns := compat.Table.column(_.minWidth := 800, span(lineHeight := "1.4rem", "Type")),
        _.slots.columns := compat.Table.column(
          _.minWidth    := 600,
          _.popinText   := "Comment",
          _.demandPopin := true,
          span(lineHeight := "1.4rem", "Comment")
        ),
        _.slots.columns := compat.Table.column(span(lineHeight := "1.4rem", "Cost")),
        MTG.cards.map(card =>
          compat.Table.row(
            _.cell(card.name),
            _.cell(card.tpe),
            _.cell(card.comment),
            _.cell(card.cost)
          )
        )
      )
      //-- End
    ),
    DemoPanel("Table in MultiSelect mode")(
      //-- Begin: Table in MultiSelect mode"
      compat.Table(
        _.events.onSelectionChange.map(_.detail.selectedRows.map(_.dataset.toMap)) --> Observer(println),
        _.mode          := TableMode.MultiSelect,
        _.slots.columns := compat.Table.column(width := "12rem", span(lineHeight := "1.4rem", "Card")),
        _.slots.columns := compat.Table.column(_.minWidth := 800, span(lineHeight := "1.4rem", "Type")),
        _.slots.columns := compat.Table.column(
          _.minWidth    := 600,
          _.popinText   := "Comment",
          _.demandPopin := true,
          span(lineHeight := "1.4rem", "Comment")
        ),
        _.slots.columns := compat.Table.column(span(lineHeight := "1.4rem", "Cost")),
        MTG.cards.map(card =>
          compat.Table.row(
            dataAttr("card-name") := card.name,
            _.cell(card.name),
            _.cell(card.tpe),
            _.cell(card.comment),
            _.cell(card.cost)
          )
        )
      )
      //-- End
    ),
    DemoPanel("Table with No Data")(
      //-- Begin: Table with No Data
      compat.Table(
        _.noDataText    := "No Data",
        _.slots.columns := compat.Table.column(width := "12rem", span(lineHeight := "1.4rem", "Card")),
        _.slots.columns := compat.Table.column(_.minWidth := 800, span(lineHeight := "1.4rem", "Type")),
        _.slots.columns := compat.Table.column(
          _.minWidth    := 600,
          _.popinText   := "Comment",
          _.demandPopin := true,
          span(lineHeight := "1.4rem", "Comment")
        ),
        _.slots.columns := compat.Table.column(span(lineHeight := "1.4rem", "Cost"))
      )
      //-- End
    ),
    DemoPanel("Growing Table with 'More' button") {
      //-- Begin: Growing Table with 'More' button
      val loadMoreBus: EventBus[Unit] = new EventBus
      val totalNumberOfCards          = MTG.cards.length
      val numberOfLoadedCards         = loadMoreBus.events.delay(3000).mapTo(4).scanLeft(4)(_ + _)

      val cardsToDisplay = numberOfLoadedCards.map(MTG.cards.take)

      val busyState = EventStream
        .merge(
          loadMoreBus.events.mapTo(+1),
          numberOfLoadedCards.changes.mapTo(-1)
        )
        .scanLeft(0)(_ + _)
        .map(_ > 0)

      compat.Table(
        _.busy   <-- busyState,
        _.growing := TableGrowingMode.Button,
        _.growingButtonSubtext <-- numberOfLoadedCards
          .map(_ min totalNumberOfCards)
          .map(n => s"[$n / $totalNumberOfCards]"),
        _.events.onLoadMore.mapTo(()) --> loadMoreBus,
        _.slots.columns := compat.Table.column(width := "12rem", span(lineHeight := "1.4rem", "Card")),
        _.slots.columns := compat.Table.column(_.minWidth := 800, span(lineHeight := "1.4rem", "Type")),
        _.slots.columns := compat.Table.column(
          _.minWidth    := 600,
          _.popinText   := "Comment",
          _.demandPopin := true,
          span(lineHeight := "1.4rem", "Comment")
        ),
        _.slots.columns := compat.Table.column(span(lineHeight := "1.4rem", "Cost")),
        children <-- cardsToDisplay.map(
          _.map(card =>
            compat.Table.row(
              dataAttr("card-name") := card.name,
              _.cell(card.name),
              _.cell(card.tpe),
              _.cell(card.comment),
              _.cell(card.cost)
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
      val numberOfLoadedCards         = loadMoreBus.events.delay(3000).mapTo(4).scanLeft(4)(_ + _)

      val cardsToDisplay = numberOfLoadedCards.map(MTG.cards.take)

      val busyState = EventStream
        .merge(
          loadMoreBus.events.mapTo(+1),
          numberOfLoadedCards.changes.mapTo(-1)
        )
        .scanLeft(0)(_ + _)
        .map(_ > 0)

      div(
        overflowY := "scroll",
        height    := "400px",
        compat.Table(
          _.busy   <-- busyState,
          _.growing := TableGrowingMode.Scroll,
          _.events.onLoadMore.mapTo(()) --> loadMoreBus,
          _.slots.columns := compat.Table.column(width := "12rem", span(lineHeight := "1.4rem", "Card")),
          _.slots.columns := compat.Table.column(_.minWidth := 800, span(lineHeight := "1.4rem", "Type")),
          _.slots.columns := compat.Table.column(
            _.minWidth    := 600,
            _.popinText   := "Comment",
            _.demandPopin := true,
            span(lineHeight := "1.4rem", "Comment")
          ),
          _.slots.columns := compat.Table.column(span(lineHeight := "1.4rem", "Cost")),
          children <-- cardsToDisplay.map(
            _.map(card =>
              compat.Table.row(
                dataAttr("card-name") := card.name,
                _.cell(card.name),
                _.cell(card.tpe),
                _.cell(card.comment),
                _.cell(card.cost)
              )
            )
          )
        )
      )
      //-- End
    },
    DemoPanel("Table with grouping (SingleSelect)")(
      //-- Begin: Table with grouping (SingleSelect)
      compat.Table(
        _.mode          := TableMode.SingleSelect,
        _.slots.columns := compat.Table.column(Label("City")),
        _.slots.columns := compat.Table.column(Label("Population")),
        _.slots.columns := compat.Table.column(Label("Country")),
        _.groupRow("Country: Belgium"),
        _.row(_.cell("Brussels"), _.cell("1.2 millions"), _.cell("Belgium")),
        _.row(_.cell("Antwerp"), _.cell("500,000"), _.cell("Belgium")),
        _.groupRow("Country: France"),
        _.row(_.cell("Paris"), _.cell("10 millions"), _.cell("France")),
        _.row(_.cell("Lille"), _.cell("1 million"), _.cell("France"))
      )
      //-- End
    ),
    DemoPanel("Controlling navigated cell") {
      //-- Begin: Controlling navigated cell
      val navigatedCardVar             = Var(Option(MTG.cards.head.name))
      val isCurrentNavigatedCardSignal = (card: MTG.Card) => navigatedCardVar.signal.map(_.contains(card.name))
      div(
        div(
          Label("Choose card to be 'navigated' (navigated table row have the vertical line on the right turned blue):"),
          Select(
            _.events.onChange.map(_.detail.selectedOption.dataset.get("name")) --> navigatedCardVar.writer,
            MTG.cards.map(card =>
              Select.option(card.name, _.selected <-- isCurrentNavigatedCardSignal(card), dataAttr("name") := card.name)
            )
          )
        ),
        compat.Table(
          _.slots.columns := compat.Table.column(width := "12rem", span(lineHeight := "1.4rem", "Card")),
          _.slots.columns := compat.Table.column(_.minWidth := 800, span(lineHeight := "1.4rem", "Type")),
          _.slots.columns := compat.Table.column(
            _.minWidth    := 600,
            _.popinText   := "Comment",
            _.demandPopin := true,
            span(lineHeight := "1.4rem", "Comment")
          ),
          _.slots.columns := compat.Table.column(span(lineHeight := "1.4rem", "Cost")),
          MTG.cards.map(card =>
            compat.Table.row(
              _.navigated <-- isCurrentNavigatedCardSignal(card),
              _.cell(card.name),
              _.cell(card.tpe),
              _.cell(card.comment),
              _.cell(card.cost)
            )
          )
        )
      )
      //-- End
    }
  )

}
