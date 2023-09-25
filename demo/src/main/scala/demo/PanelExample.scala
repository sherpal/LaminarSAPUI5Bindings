package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object PanelExample extends Example("Panel") {

  private val countries = List("Argentina", "Belgium", "Bulgaria", "Canada", "Columbia", "Croatia", "Denmark")

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic Panel")(
      //-- Begin: Basic Panel
      Panel(
        width        := "100%",
        _.headerText := "Both expandable and expanded",
        h1("I am a native heading!"),
        Label(_.wrappingType := WrappingType.Normal, "Short text."),
        br(),
        Label(_.wrappingType := WrappingType.Normal, "Another text."),
        p(
          "Aute ullamco officia fugiat culpa do tempor tempor aute excepteur magna. Quis velit adipisicing excepteur " +
            "do eu duis elit. Sunt ea pariatur nulla est laborum proident sunt labore commodo Lorem laboris nisi Lorem."
        )
      )
      //-- End
    ),
    DemoPanel("Panel with List")(
      //-- Begin: Panel with List
      Panel(
        _.headerText := "Select your country",
        width        := "100%",
        UList(
          _.mode := ListMode.MultiSelect,
          countries.map(country => UList.item(country))
        )
      )
      //-- End
    ),
    DemoPanel("Fixed Panel (Can't be Collapsed/Expanded)")(
      //-- Begin: Fixed Panel (Can't be Collapsed/Expanded)
      Panel(
        _.fixed      := true,
        _.headerText := "Country Of Birth",
        UList(
          _.mode := ListMode.SingleSelectBegin,
          countries.map(country => UList.item(country))
        )
      )
      //-- End
    ),
    DemoPanel("Panel with Custom Header")(
      //-- Begin: Panel with Custom Header
      div(
        styleTag("""
      |.header {
      |		display: flex;
      |		align-items: center;
      |		justify-content: space-between;
      |		width: 100%;
      |}
      |""".stripMargin),
        Panel(
          width := "100%",
          _.slots.header := div(
            className := "header",
            h1("Countries"),
            div(
              Button("Edit"),
              Button(_.design := ButtonDesign.Emphasized, "Add"),
              Button(_.design := ButtonDesign.Negative, "Remove")
            )
          ),
          UList(
            _.mode := ListMode.MultiSelect,
            countries.map(country => UList.item(country))
          )
        )
      )
      //-- End
    ),
    DemoPanel("Sticky Header")(
      //-- Begin: Sticky Header
      Panel(
        height         := "500px",
        _.stickyHeader := true,
        _.headerText   := "Rules",
        p(
          """
            |1. Game Concepts
            |
            |100. General
            |
            |100.1. These Magic rules apply to any Magic game with two or more players, including two-player games and multiplayer games.
            |
            |100.1a A two-player game is a game that begins with only two players.
            |
            |100.1b A multiplayer game is a game that begins with more than two players. See section 8, “Multiplayer Rules.”
            |
            |100.2. To play, each player needs their own deck of traditional Magic cards, small items to represent any tokens and counters, and some way to clearly track life totals.
            |
            |100.2a In constructed play (a way of playing in which each player creates their own deck ahead of time), each deck has a minimum deck size of 60 cards. A constructed deck may contain any number of basic land cards and no more than four of any card with a particular English name other than basic land cards. For the purposes of deck construction, cards with interchangeable names have the same English name (see rule 201.3).
            |
            |100.2b In limited play (a way of playing in which each player gets the same quantity of unopened Magic product such as booster packs and creates their own deck using only this product and basic land cards), each deck has a minimum deck size of 40 cards. A limited deck may contain as many duplicates of a card as are included with the product.
            |
            |100.2c Commander decks are subject to additional deckbuilding restrictions and requirements. See rule 903, “Commander,” for details.
            |
            |100.2d Some formats and casual play variants allow players to use a supplementary deck of nontraditional Magic cards (see rule 108.2a). These supplementary decks have their own deck construction rules. See rule 717, “Attraction Cards;” rule 901, “Planechase;” and rule 904, “Archenemy.”
            |
            |100.3. Some cards require coins or traditional dice. Some casual variants require additional items, such as specially designated cards, nontraditional Magic cards, and specialized dice.
            |
            |100.4. Each player may also have a sideboard, which is a group of additional cards the player may use to modify their deck between games of a match.
            |
            |100.4a In constructed play, a sideboard may contain no more than fifteen cards. The four-card limit (see rule 100.2a) applies to the combined deck and sideboard.
            |
            |100.4b In limited play involving individual players, all cards in a player’s card pool not included in their deck are in that player’s sideboard.
            |
            |100.4c In limited play involving the Two-Headed Giant multiplayer variant, all cards in a team’s card pool but not in either player’s deck are in that team’s sideboard.
            |
            |100.4d In limited play involving other multiplayer team variants, each card in a team’s card pool but not in any player’s deck is assigned to the sideboard of one of those players. Each player has their own sideboard; cards may not be transferred between players.
            |
            |100.5. If a deck must contain at least a certain number of cards, that number is referred to as a minimum deck size. There is no maximum deck size for non-Commander decks.
            |
            |100.6. Most Magic tournaments (organized play activities where players compete against other players to win prizes) have additional rules covered in the Magic: The Gathering Tournament Rules (found at WPN.Wizards.com/en/resources/rules-documents). These rules may limit the use of some cards, including barring all cards from some older sets.
            |
            |100.6a Tournaments usually consist of a series of matches. A two-player match usually involves playing until one player has won two games. A multiplayer match usually consists of only one game.
            |
            |100.6b Players can use the Magic Store & Event Locator at Wizards.com/Locator to find tournaments in their area.
            |
            |100.7. Certain cards are intended for casual play and may have features and text that aren’t covered by these rules. These include Mystery Booster playtest cards, promotional cards and cards in “Un-sets” that were printed with a silver border, and cards in the Unfinity™ expansion that have an acorn symbol at the bottom of the card.
            |
            |101. The Magic Golden Rules
            |""".stripMargin.split("\n").filterNot(_.isEmpty).flatMap(txt => List[Modifier[HtmlElement]](txt, br()))
        )
      )
      //-- End
    )
  )

}
