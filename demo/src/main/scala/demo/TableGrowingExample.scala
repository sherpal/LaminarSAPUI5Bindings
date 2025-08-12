package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub, MTG}
import org.scalajs.dom

import scala.scalajs.js

object TableGrowingExample extends Example("TableGrowing") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Growing Table with 'More' button") {
      //-- Begin: Growing Table with 'More' button
      val loadMoreBus: EventBus[Unit] = new EventBus
      val totalNumberOfCards          = MTG.cards.length
      val numberOfLoadedCards         = loadMoreBus.events.delay(3000).mapTo(4).scanLeft(4)(_ + _)

      val cardsToDisplay = numberOfLoadedCards.map(MTG.cards.take)

      val loadingState = EventStream
        .merge(
          loadMoreBus.events.mapTo(+1),
          numberOfLoadedCards.changes.mapTo(-1)
        )
        .scanLeft(0)(_ + _)
        .map(_ > 0)

      Table(
        _.slots.features <-- numberOfLoadedCards
          .map(_ min totalNumberOfCards)
          .map { nbr =>
            TableGrowing(
              display  <-- EventStream.fromValue(nbr >= totalNumberOfCards).filter(identity).map(_ => "none"),
              _.mode    := TableGrowingMode.Button,
              _.subtext := s"[$nbr / $totalNumberOfCards]",
              _.events.onLoadMore.mapTo(()) --> loadMoreBus
            )
          },
        _.loading <-- loadingState,
        _.slots.headerRow := Table.headerRow(
          _.cell(width       := "12rem", span(lineHeight := "1.4rem", "Card")),
          _.cell(minWidth.px := 800, span(lineHeight := "1.4rem", "Type")),
          _.cell(
            minWidth.px   := 600,
            _.popinText   := "Comment",
            _.popinHidden := false,
            span(lineHeight := "1.4rem", "Comment")
          ),
          _.cell(span(lineHeight := "1.4rem", "Cost"))
        ),
        children <-- cardsToDisplay.map(
          _.map(card =>
            Table.row(
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
    }
  )

}
