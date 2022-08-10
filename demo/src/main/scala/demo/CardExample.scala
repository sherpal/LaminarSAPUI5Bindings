package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, MTG}

object CardExample extends Example("Card") {

  private val cardContentClassName = "card-content"
  private val medium               = "medium"

  private val statusError   = "status-error"
  private val statusWarning = "status-warning"
  private val statusSuccess = "status-success"

  private val contentPadding = "content-padding"

  def component: HtmlElement = div(
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
          _ => className := medium,
          _.slots.header := Card.header(
            _.titleText := "Magic Mana Symbols",
            _.subtitleText := "All of them",
            _.status := "3 of 6",
            _.slots.avatar := Icon(_.name := IconName.group),
            _.slots.action := Button(_.design := ButtonDesign.Transparent, _ => "View All")
          ),
          _ =>
            div(
              className := cardContentClassName,
              UList(
                _.separators := ListSeparator.None,
                _ => width := "100%",
                _ => marginBottom := "0.75rem",
                _ =>
                  MTG.manaSymbolsNames
                    .zip(MTG.manaSymbolsShortNames)
                    .take(3)
                    .map((name, shortName) =>
                      UList.Li(_.image := MTG.manaSymbolsRefs(shortName), _.description := name, _ => name)
                    )
              )
            )
        ),
        Card(
          _ => className := medium,
          _.slots.header := Card.header(
            _.titleText := "This header is interactive",
            _.interactive := true,
            _.subtitleText := "Click, press Enter or Space",
            _.status := "3 of 6",
            _.slots.avatar := Icon(_.name := IconName.group)
          ),
          _ =>
            div(
              className := cardContentClassName,
              UList(
                _.separators := ListSeparator.None,
                _ => width := "100%",
                _ => marginBottom := "0.75rem",
                _ =>
                  MTG.manaSymbolsNames
                    .zip(MTG.manaSymbolsShortNames)
                    .drop(3)
                    .map((name, shortName) =>
                      UList.Li(_.image := MTG.manaSymbolsRefs(shortName), _.description := name, _ => name)
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
          _ =>
            Table(
              _ => className := contentPadding,
              _.slots.columns := TableColumn(_ => Label(_ => "Sales Order")),
              _.slots.columns := TableColumn(_ => Label(_ => "Customer")),
              _.slots.columns := TableColumn(_ => Label(_ => "Net Amount")),
              _.slots.columns := TableColumn(
                _.minWidth := 450,
                _.popinText := "Status",
                _.demandPopin := true,
                _ => Label(_ => "Status")
              ),
              _ =>
                TableRow(
                  _.cell(_ => Label(_ => "5000010050")),
                  _.cell(_ => Label(_ => "Entertainment Argentinia")),
                  _.cell(_ => Label(_ => "6k USD")),
                  _.cell(_ => className := statusSuccess, _ => "Approved")
                ),
              _ =>
                TableRow(
                  _.cell(_ => Label(_ => "5000010051")),
                  _.cell(_ => Label(_ => "Brazil Technologies")),
                  _.cell(_ => Label(_ => "2k USD")),
                  _.cell(_ => className := statusError, _ => "Rejected")
                ),
              _ =>
                TableRow(
                  _.cell(_ => Label(_ => "5000010052")),
                  _.cell(_ => Label(_ => "Robert Brown Ent.")),
                  _.cell(_ => Label(_ => "17k USD")),
                  _.cell(_ => className := statusWarning, _ => "Pending")
                ),
            )
        )
      )
      //-- End
    )
  )

}
