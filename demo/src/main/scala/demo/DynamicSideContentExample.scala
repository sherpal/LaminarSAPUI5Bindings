package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object DynamicSideContentExample extends Example("DynamicSideContent") {

  val mainContent =
    "103.1. At the start of a game, the players determine which one of them will choose who takes the first turn. In " +
      "the first game of a match (including a single-game match), the players may use any mutually agreeable method " +
      "(flipping a coin, rolling dice, etc.) to do so. In a match of several games, the loser of the previous game " +
      "chooses who takes the first turn. If the previous game was a draw, the player who made the choice in that game" +
      " makes the choice in this game. The player chosen to take the first turn is the starting player. The game’s " +
      "default turn order begins with the starting player and proceeds clockwise."

  val sideContent =
    "103.4. Each player draws a number of cards equal to their starting hand size, which is normally seven. (Some " +
      "effects can modify a player’s starting hand size.) A player who is dissatisfied with their initial hand may " +
      "take a mulligan. First, the starting player declares whether they will take a mulligan. Then each other player " +
      "in turn order does the same. Once each player has made a declaration, all players who decided to take " +
      "mulligans do so at the same time. To take a mulligan, a player shuffles the cards in their hand back into their " +
      "library, draws a new hand of cards equal to their starting hand size, then puts a number of those cards equal " +
      "to the number of times that player has taken a mulligan on the bottom of their library in any order. Once a " +
      "player chooses not to take a mulligan, the remaining cards become that player’s opening hand, and that player " +
      "may not take any further mulligans. This process is then repeated until no player takes a mulligan. A player" +
      " can take mulligans until their opening hand would be zero cards, after which they may not take further" +
      " mulligans."

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Dynamic Side Content with default properties") {
      //-- Begin: Dynamic Side Content with default properties
      DynamicSideContent(
        _ => div(h1("Main Content"), p(mainContent)),
        _.slots.sideContent := div(h1("Side Content"), p(sideContent))
      )
      //-- End
    },
    DemoPanel("Dynamic Side Content with hideMainContent set") {
      //-- Begin: Dynamic Side Content with hideMainContent set
      DynamicSideContent(
        _.hideMainContent := true,
        _ => div(h1("Main Content"), p(mainContent)),
        _.slots.sideContent := div(h1("Side Content"), p(sideContent))
      )
      //-- End
    },
    DemoPanel("Dynamic Side Content with hideSideContent set") {
      //-- Begin: Dynamic Side Content with hideSideContent set
      DynamicSideContent(
        _.hideSideContent := true,
        _ => div(h1("Main Content"), p(mainContent)),
        _.slots.sideContent := div(h1("Side Content"), p(sideContent))
      )
      //-- End
    },
    DemoPanel("Dynamic Side Content with equalSplit set") {
      //-- Begin: Dynamic Side Content with equalSplit set
      DynamicSideContent(
        _.equalSplit := true,
        _ => div(h1("Main Content"), p(mainContent)),
        _.slots.sideContent := div(h1("Side Content"), p(sideContent))
      )
      //-- End
    },
    DemoPanel("Dynamic Side Content with sideContentPosition='Start'") {
      //-- Begin: Dynamic Side Content with sideContentPosition='Start'
      DynamicSideContent(
        _.sideContentPosition := SideContentPosition.Start,
        _ => div(h1("Main Content"), p(mainContent)),
        _.slots.sideContent := div(h1("Side Content"), p(sideContent))
      )
      //-- End
    },
    DemoPanel("Dynamic Side Content with sideContentFallDown='BelowXL'") {
      //-- Begin: Dynamic Side Content with sideContentFallDown='BelowXL'
      DynamicSideContent(
        _.sideContentFallDown := SideContentFallDown.BelowXL,
        _ => div(h1("Main Content"), p(mainContent)),
        _.slots.sideContent := div(h1("Side Content"), p(sideContent))
      )
      //-- End
    },
    DemoPanel("Dynamic Side Content with sideContentVisibility='ShowAboveM'") {
      //-- Begin: Dynamic Side Content with sideContentVisibility='ShowAboveM'
      DynamicSideContent(
        _.sideContentVisibility := SideContentVisibility.ShowAboveM,
        _ => div(h1("Main Content"), p(mainContent)),
        _.slots.sideContent := div(h1("Side Content"), p(sideContent))
      )
      //-- End
    },
    DemoPanel("Dynamic Side Content - toggle contents on mobile device (S size)") {
      //-- Begin: Dynamic Side Content - toggle contents on mobile device (S size)
      val toggleContentsBus: EventBus[Unit] = new EventBus

      Page(
        _ => height := "500px",
        _ => maxWidth := "360px",
        _.floatingFooter := true,
        _.hideFooter := false,
        //_ => maxWidth := "360px",
        _ =>
          DynamicSideContent(
            _ => inContext(el => toggleContentsBus.events --> Observer[Any](_ => el.ref.toggleContents())),
            _ => div(h1("Main Content"), p(mainContent)),
            _.slots.sideContent := div(h1("Side Content"), p(sideContent))
          ),
        _.slots.footer := Bar(
          _.design := BarDesign.FloatingFooter,
          _.slots.endContent := Button(
            _.design := ButtonDesign.Positive,
            _ => "Toggle Contents",
            _.events.onClick.mapTo(()) --> toggleContentsBus.writer
          )
        )
      )
      //-- End
    }
  )

}
