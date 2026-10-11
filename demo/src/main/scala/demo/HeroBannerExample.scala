package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object HeroBannerExample extends Example("HeroBanner") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    MessageStrip(
      _.design := MessageStripDesign.Information,
      "The HeroBanner component is experimental in UI5 and its API may change."
    ),
    DemoPanel("Basic Hero Banner") {
      //-- Begin: Basic Hero Banner
      HeroBanner(
        _.overlineText := "Monday, 12 October",
        _.headerText   := "Hello, Jace",
        _.slots.actions := List(
          Button(_.design := ButtonDesign.Transparent, _.icon := IconName.`action-settings`, _.tooltip := "Settings"),
          Button(_.design := ButtonDesign.Emphasized, "New Deck")
        ),
        Text("You have 3 tournaments coming up this week. Good luck!")
      )
      //-- End
    },
    DemoPanel("Hero Banner with two columns") {
      //-- Begin: Hero Banner with two columns
      val columnsRatioVar     = Var[HeroBannerColumnsRatio](HeroBannerColumnsRatio.FirstWider)
      val actionsPlacementVar = Var[HeroBannerActionsPlacement](HeroBannerActionsPlacement.TopEnd)

      def kpiCard(title: String, value: String) = Card(
        _.slots.header := Card.header(_.titleText := title),
        div(padding := "1rem", Title.h3(value))
      )

      div(
        div(
          display.flex,
          alignItems.center,
          columnGap    := "0.5rem",
          marginBottom := "1rem",
          Label("Columns ratio:"),
          HeroBannerColumnsRatio.allValues.map(ratio =>
            Button(
              ratio.value,
              _.design <-- columnsRatioVar.signal.map(current =>
                if current == ratio then ButtonDesign.Emphasized else ButtonDesign.Default
              ),
              _.events.onClick.mapTo(ratio) --> columnsRatioVar.writer
            )
          ),
          Label("Actions placement:"),
          HeroBannerActionsPlacement.allValues.map(placement =>
            Button(
              placement.value,
              _.design <-- actionsPlacementVar.signal.map(current =>
                if current == placement then ButtonDesign.Emphasized else ButtonDesign.Default
              ),
              _.events.onClick.mapTo(placement) --> actionsPlacementVar.writer
            )
          )
        ),
        HeroBanner(
          _.overlineText := "Welcome back",
          _.headerText   := "Hello, Liliana",
          _.columnsRatio <-- columnsRatioVar.signal,
          _.actionsPlacement <-- actionsPlacementVar.signal,
          _.slots.actions := Button(_.design := ButtonDesign.Emphasized, "Open Collection"),
          div(
            display.flex,
            columnGap := "1rem",
            kpiCard("Cards owned", "1 342"),
            kpiCard("Decks", "12")
          ),
          _.slots.endContent := kpiCard("Collection value", "4 210 €")
        )
      )
      //-- End
    }
  )

}
