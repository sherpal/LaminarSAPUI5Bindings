package demo

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub, MTG}

object CardExample extends Example("Card") {

  def webComponent: WebComponent = Card

  private val cardContentClassName = "card-content"
  private val medium               = "medium"

  private val statusError   = "status-error"
  private val statusWarning = "status-warning"
  private val statusSuccess = "status-success"

  private val contentPadding = "content-padding"

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Card with List")(
      //-- Begin: Card with List
      div(
        styleTag(s"""
                |.$cardContentClassName {
                |	display: flex;
                |	justify-content: space-around;
                |	flex-wrap: wrap;
                |}
                |
                |ui5-card.$medium {
                |  width: 30rem;
                |}
                |
                |ui5-card {
                |  margin: 1em;
                |  min-width: 18rem;
                |}
                |""".stripMargin),
        Card(
          className := medium,
          _.slots.header := Card.header(
            _.titleText := "Magic Mana Symbols",
            _.subtitleText := "All of them",
            _.status := "3 of 6",
            _.slots.avatar := Icon(_.name := IconName.group),
            _.slots.action := Button(_.design := ButtonDesign.Transparent, "View All")
          ),
          div(
            className := cardContentClassName,
            UList(
              _.separators := ListSeparator.None,
              width := "100%",
              marginBottom := "0.75rem",
              MTG.manaSymbolsNames
                .zip(MTG.manaSymbolsShortNames)
                .take(3)
                .map((name, shortName) =>
                  UList.item(_.image := MTG.manaSymbolsRefs(shortName), _.description := name, name)
                )
            )
          )
        ),
        Card(
          className := medium,
          _.slots.header := Card.header(
            _.titleText := "This header is interactive",
            _.interactive := true,
            _.subtitleText := "Click, press Enter or Space",
            _.status := "3 of 6",
            _.slots.avatar := Icon(_.name := IconName.group)
          ),
          div(
            className := cardContentClassName,
            UList(
              _.separators := ListSeparator.None,
              width := "100%",
              marginBottom := "0.75rem",
              MTG.manaSymbolsNames
                .zip(MTG.manaSymbolsShortNames)
                .drop(3)
                .map((name, shortName) =>
                  UList.item(_.image := MTG.manaSymbolsRefs(shortName), _.description := name, name)
                )
            )
          )
        )
      )
      //-- End
    ),
    DemoPanel("Card with Table")(
      //-- Begin: Card with Table
      div(
        styleTag(s"""
            |.$statusError {color: #b00;}
            |.$statusWarning {color: #e9730c;}
            |.$statusSuccess {color: #107e3e;}
            |""".stripMargin),
        Card(
          _.slots.header := Card
            .header(_.titleText := "New Purchase Orders", _.subtitleText := "Today", _.status := "3 of 15"),
          Table(
            className := contentPadding,
            _.slots.columns := TableColumn(Label("Sales Order")),
            _.slots.columns := TableColumn(Label("Customer")),
            _.slots.columns := TableColumn(Label("Net Amount")),
            _.slots.columns := TableColumn(
              _.minWidth := 450,
              _.popinText := "Status",
              _.demandPopin := true,
              Label("Status")
            ),
            TableRow(
              _.cell(Label("5000010050")),
              _.cell(Label("Entertainment Argentinia")),
              _.cell(Label("6k USD")),
              _.cell(className := statusSuccess, "Approved")
            ),
            TableRow(
              _.cell(Label("5000010051")),
              _.cell(Label("Brazil Technologies")),
              _.cell(Label("2k USD")),
              _.cell(className := statusError, "Rejected")
            ),
            TableRow(
              _.cell(Label("5000010052")),
              _.cell(Label("Robert Brown Ent.")),
              _.cell(Label("17k USD")),
              _.cell(className := statusWarning, "Pending")
            )
          )
        )
      )
      //-- End
    ),
    mtgImageWarning
  )

}
