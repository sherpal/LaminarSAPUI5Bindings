package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object PageExample extends Example("Page") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Page with floating footer") {
      //-- Begin: Page with floating footer
      Page(
        _.floatingFooter := true,
        height := "500px",
        width := "500px",
        _.slots.header := Bar(
          _.design := BarDesign.Header,
          _.slots.startContent := Button(_.tooltip := "Go Home", _.icon := IconName.home),
          _.slots.endContent := Button(_.tooltip := "Settings", _.icon := IconName.`action-settings`),
          "Page Title"
        ),
                  div(
            overflowY := "auto",
            p(
              "103.3. Each player begins the game with a starting life total of 20. Some variant games have different starting life totals."
            ),
            p("103.3a In a Two-Headed Giant game, each team’s starting life total is 30."),
            p(
              "103.3b In a Vanguard game, each player’s starting life total is 20 plus or minus the life modifier of their vanguard card."
            ),
            p("103.3c In a Commander game, each player’s starting life total is 40."),
            p(
              "103.3d In a two-player Brawl game, each player’s starting life total is 25. In a multiplayer Brawl game, each player’s starting life total is 30."
            ),
            p("103.3e In an Archenemy game, the archenemy’s starting life total is 40.")
          ),
        _.slots.footer := Bar(
          _.design := BarDesign.FloatingFooter,
          _.slots.startContent := Button(_.design := ButtonDesign.Transparent, _.icon := IconName.home),
          _.slots.endContent := Button(_.design := ButtonDesign.Positive, "Accept"),
          _.slots.endContent := Button(_.design := ButtonDesign.Negative, "Reject"),
          _.slots.endContent := Button(_.design := ButtonDesign.Transparent, "Cancel")
        )
      )
      //-- End
    }
  )

}
