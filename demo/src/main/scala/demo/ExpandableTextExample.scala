package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object ExpandableTextExample extends Example("ExpandableText") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic text")(
      //-- Begin: Basic text
      ExpandableText(
        _.text := "103.1. At the start of a game, the players determine which one of them will choose who takes the first turn. In the first game of a match (including a single-game match), the players may use any mutually agreeable method (flipping a coin, rolling dice, etc.) to do so. In a match of several games, the loser of the previous game chooses who takes the first turn. If the previous game was a draw, the player who made the choice in that game makes the choice in this game. The player chosen to take the first turn is the starting player. The game’s default turn order begins with the starting player and proceeds clockwise."
      )
      //-- End
    ),
    DemoPanel("Text show truncated in popover")(
      //-- Begin: Text show truncated in popover
      ExpandableText(
        _.overflowMode := TextOverflowMode.Popover,
        _.text := "103.1. At the start of a game, the players determine which one of them will choose who takes the first turn. In the first game of a match (including a single-game match), the players may use any mutually agreeable method (flipping a coin, rolling dice, etc.) to do so. In a match of several games, the loser of the previous game chooses who takes the first turn. If the previous game was a draw, the player who made the choice in that game makes the choice in this game. The player chosen to take the first turn is the starting player. The game’s default turn order begins with the starting player and proceeds clockwise."
      )
      //-- End
    )
  )

}
