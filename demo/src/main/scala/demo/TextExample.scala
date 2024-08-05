package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object TextExample extends Example("Text") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic text")(
      //-- Begin: Basic text
      Text("Some nice little text")
      //-- End
    ),
    DemoPanel("Text with maxLines being truncated")(
      //-- Begin: Text with maxLines being truncated
      Text(
        _.maxLines := 5,
        """
1. Game Concepts

100. General

100.1. These Magic rules apply to any Magic game with two or more players, including two-player games and multiplayer games.

100.1a A two-player game is a game that begins with only two players.

100.1b A multiplayer game is a game that begins with more than two players. See section 8, “Multiplayer Rules.”

100.2. To play, each player needs their own deck of traditional Magic cards, small items to represent any tokens and counters, and some way to clearly track life totals.

100.2a In constructed play (a way of playing in which each player creates their own deck ahead of time), each deck has a minimum deck size of 60 cards. A constructed deck may contain any number of basic land cards and no more than four of any card with a particular English name other than basic land cards. For the purposes of deck construction, cards with interchangeable names have the same English name (see rule 201.3).

100.2b In limited play (a way of playing in which each player gets the same quantity of unopened Magic product such as booster packs and creates their own deck using only this product and basic land cards), each deck has a minimum deck size of 40 cards. A limited deck may contain as many duplicates of a card as are included with the product.

100.2c Commander decks are subject to additional deckbuilding restrictions and requirements. See rule 903, “Commander,” for details.

100.2d Some formats and casual play variants allow players to use a supplementary deck of nontraditional Magic cards (see rule 108.2a). These supplementary decks have their own deck construction rules. See rule """
      )
      //-- End
    ),
    DemoPanel("Text with some styling") {
      //-- Begin: Text with some styling
      div(
        Text(
          color    := "var(--sapPositiveColor)",
          fontSize := "1.25rem",
          "Styled text"
        ),
        Text(
          color     := "var(--sapNegativeColor)",
          fontStyle := "italic",
          "Other styled text"
        )
      )
      //-- End
    }
  )

}
