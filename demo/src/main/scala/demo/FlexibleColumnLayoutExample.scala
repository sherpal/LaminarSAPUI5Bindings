package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub, MTG}
import org.scalajs.dom

import scala.scalajs.js

object FlexibleColumnLayoutExample extends Example("FlexibleColumnLayout") {

  import MTG.{cards, Card}

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("FlexibleColumnLayout - One Initial Column") {
      //-- Begin: FlexibleColumnLayout - One Initial Column

      /** Feed in here whatever layout you want to give to the FlexibleColumnLayout */
      val layoutBus: EventBus[FCLLayout] = new EventBus

      val maybeSelectedCardVar = Var(Option.empty[Card])

      def startColumnListItem(card: Card): HtmlElement = UList.item(
        card.name,
        _.description         := card.tpe,
        _.additionalText      := s"Cost: ${card.cost}",
        _.iconEnd             := true,
        _.icon                := IconName.`slim-arrow-right`,
        dataAttr("card-name") := card.name
      )

      def cardFromName(name: String): Option[Card] = cards.find(_.name == name)

      div(
        styleTag("""
          |:host([description]) .ui5-li-root {
          |    padding: 1rem;
          |}
          |
          |.ui5-li-root {
          |    position: relative;
          |    display: flex;
          |    align-items: center;
          |    width: 100%;
          |    height: 100%;
          |    padding: 0px 1rem;
          |    box-sizing: border-box;
          |}
          |""".stripMargin),
        FlexibleColumnLayout(
          _.layout <-- layoutBus.events.startWith(FCLLayout.OneColumn),
          _.events.onLayoutChanged.map(_.detail) --> Observer[js.Any](obj => dom.console.log(obj)),
          _.slots.startColumn := div(
            ShellBar(_.primaryTitle := "Magic"),
            UList(
              height       := "500px",
              _.headerText := "Power Nine",
              cards.filter(_.comment == "Power Nine").map(startColumnListItem),
              _.events.onItemClick
                .map(event =>
                  for {
                    cardName <- event.detail.item.dataset.get("cardName")
                    card     <- cardFromName(cardName)
                  } yield card
                ) --> maybeSelectedCardVar.writer
            )
          ),
          _.slots.midColumn <-- maybeSelectedCardVar.signal.changes.collect { case Some(card) => card }.map { card =>
            div(
              div(
                display    := "flex",
                alignItems := "center",
                Button(
                  _.icon := IconName.`slim-arrow-left`,
                  _.events.onClick.mapTo(Option.empty[Card]) --> maybeSelectedCardVar.writer,
                  marginRight := "1em",
                  _.design    := ButtonDesign.Transparent
                ),
                h1(card.name)
              ),
              img(src := MTG.cardImages(card.name))
            )
          },
          maybeSelectedCardVar.signal.changes.map(maybeCard =>
            if maybeCard.isDefined then FCLLayout.TwoColumnsMidExpanded else FCLLayout.OneColumn
          ) --> layoutBus.writer
        )
      )
      //-- End
    }
  )

}
