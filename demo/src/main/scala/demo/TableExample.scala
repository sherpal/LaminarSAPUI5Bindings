package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub, MTG}
import org.scalajs.dom

import scala.scalajs.js

object TableExample extends Example("Table") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic Table") {
      //-- Begin: Basic Table
      Table(
        _.overflowMode := TableOverflowMode.Scroll,
        _.slots.headerRow := Table.headerRow(
          _.cell(_.width    := "300px", span("Name")),
          _.cell(_.width    := "200px", "Type"),
          _.cell(_.minWidth := "300px", "Cost"),
          _.cell(_.width    := "100px", "Comment"),
          _.cell(_.minWidth := "220px", "Image Url")
        ),
        MTG.cards.map { card =>
          Table.row(
            _.cell(card.name),
            _.cell(card.tpe),
            _.cell(card.cost),
            _.cell(card.comment),
            _.cell(MTG.cardImages.get(card.name).map(url => Link("Image", _.href := url)))
          )
        }
      )
      //-- End
    },
    DemoPanel("No Data") {
      //-- Begin: No Data
      Table(
        _.slots.headerRow := Table.headerRow(
          _.cell(span("Name")),
          _.cell("Type"),
          _.cell("Cost"),
          _.cell("Comment"),
          _.cell("Image Url")
        ),
        _.slots.noData := IllustratedMessage(_.name := IllustratedMessageType.NoData)
      )
      //-- End
    },
    DemoPanel("Interactive rows") {
      //-- Begin: Interactive rows
      val clickedRowBus = new EventBus[String]
      div(
        Toast(
          _.showFromTextEvents(
            clickedRowBus.events.map(key => s"Row with key `$key` has been clicked on!")
          )
        ),
        Table(
          _.events.onRowClick.map(_.detail.row.rowKey) --> clickedRowBus.writer,
          _.slots.headerRow := Table.headerRow(
            _.cell(span("Name")),
            _.cell("Type"),
            _.cell("Cost"),
            _.cell("Comment"),
            _.cell("Image Url")
          ),
          MTG.cards.map { card =>
            Table.row(
              _.rowKey      := card.name,
              _.interactive := true,
              _.cell(card.name),
              _.cell(card.tpe),
              _.cell(card.cost),
              _.cell(card.comment),
              _.cell(MTG.cardImages.get(card.name).map(url => Link("Image", _.href := url)))
            )
          }
        )
      )
      //-- End
    },
    DemoPanel("Alternate row colors") {
      //-- Begin: Alternate row colors
      Table(
        _.alternateRowColors := true,
        _.slots.headerRow := Table.headerRow(
          _.cell("Name"),
          _.cell("Type"),
          _.cell("Cost")
        ),
        MTG.cards.map { card =>
          Table.row(
            _.cell(card.name),
            _.cell(card.tpe),
            _.cell(card.cost)
          )
        }
      )
      //-- End
    },
    DemoPanel("Group rows") {
      //-- Begin: Group rows
      val cardsByType = MTG.cards.groupBy(_.tpe).toList.sortBy(_._1)
      Table(
        _.slots.headerRow := Table.headerRow(
          _.cell("Name"),
          _.cell("Cost"),
          _.cell("Comment")
        ),
        cardsByType.flatMap { case (tpe, cards) =>
          Table.groupRow(s"Type: $tpe (${cards.length})") :: cards.map { card =>
            Table.row(
              _.cell(card.name),
              _.cell(card.cost),
              _.cell(card.comment)
            )
          }
        }
      )
      //-- End
    },
    DemoPanel("Merged cells") {
      //-- Begin: Merged cells
      val sortedCards = MTG.cards.sortBy(_.tpe)
      Table(
        _.slots.headerRow := Table.headerRow(
          _.cell("Type"),
          _.cell("Name"),
          _.cell("Cost")
        ),
        sortedCards.zip(None :: sortedCards.map(Some(_))).map { case (card, maybePrevious) =>
          Table.row(
            // the cell keeps its value (for accessibility), but is visually merged with the one above
            _.cell(_.merged := maybePrevious.exists(_.tpe == card.tpe), card.tpe),
            _.cell(card.name),
            _.cell(card.cost)
          )
        }
      )
      //-- End
    },
    DemoPanel("Multi selection") {
      //-- Begin: Multi selection
      // Note: row keys must not contain spaces
      def rowKeyOf(card: MTG.Card): String = card.name.replace(" ", "_")

      val selectedKeysVar: Var[List[String]] = Var(List(rowKeyOf(MTG.cards.head)))
      div(
        div(child.text <-- selectedKeysVar.signal.map(keys => s"Selected row keys: ${keys.mkString(", ")}")),
        Table(
          _.slots.features := TableSelectionMulti(
            _.headerSelector := TableSelectionMultiHeaderSelector.ClearAll,
            _.selected <-- selectedKeysVar.signal,
            _.events.onChange.map(_.target.selected) --> selectedKeysVar.writer
          ),
          _.slots.headerRow := Table.headerRow(
            _.cell("Name"),
            _.cell("Type"),
            _.cell("Cost")
          ),
          MTG.cards.map { card =>
            Table.row(
              _.rowKey := rowKeyOf(card),
              _.cell(card.name),
              _.cell(card.tpe),
              _.cell(card.cost)
            )
          }
        )
      )
      //-- End
    },
    DemoPanel("Row actions and header cell AI action") {
      //-- Begin: Row actions and header cell AI action
      val messagesBus = new EventBus[String]
      div(
        Toast(_.showFromTextEvents(messagesBus.events)),
        Table(
          _.rowActionCount := 2,
          _.events.onRowActionClick.map { event =>
            s"Action `${event.detail.action.getAttribute("text")}` clicked on row `${event.detail.row.rowKey}`"
          } --> messagesBus.writer,
          _.slots.headerRow := Table.headerRow(
            _.cell(
              "Name",
              _.slots.action := TableHeaderCell.actionAI(
                _.events.onClick.mapTo("AI action clicked on column `Name`") --> messagesBus.writer
              )
            ),
            _.cell("Type"),
            _.cell("Cost")
          ),
          MTG.cards.take(5).map { card =>
            Table.row(
              _.rowKey := card.name.replace(" ", "_"),
              _.cell(card.name),
              _.cell(card.tpe),
              _.cell(card.cost),
              _.slots.actions := List(
                Table.row.action(_.icon := IconName.edit, _.text := "Edit"),
                Table.row.action(_.icon := IconName.delete, _.text := "Delete")
              )
            )
          }
        )
      )
      //-- End
    },
    DemoPanel("Virtualized table") {
      //-- Begin: Virtualized table
      val rowCount  = 10000
      val rowHeight = 32
      // range of rows that should currently be rendered, communicated by the virtualizer
      val rangeVar: Var[(Int, Int)] = Var((0, 20))
      Table(
        height := "320px",
        _.overflowMode := TableOverflowMode.Scroll,
        _.slots.features := TableVirtualizer(
          _.rowCount  := rowCount,
          _.rowHeight := rowHeight,
          _.events.onRangeChange.map(event => (event.detail.first, event.detail.last)) --> rangeVar.writer
        ),
        _.slots.headerRow := Table.headerRow(
          _.sticky := true,
          _.cell("Row number"),
          _.cell("Card")
        ),
        children <-- rangeVar.signal.map { case (first, last) =>
          (first until last).map { index =>
            Table.row(
              _.position := index,
              height     := s"${rowHeight}px",
              _.cell(s"Row ${index + 1}"),
              _.cell(MTG.cards(index % MTG.cards.length).name)
            )
          }
        }
      )
      //-- End
    }
  )

}
