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
          _.cell(width    := "300px", span("Name")),
          _.cell(width    := "200px", "Type"),
          _.cell(minWidth := "300px", "Cost"),
          _.cell(width    := "100px", "Comment"),
          _.cell(minWidth := "220px", "Image Url")
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
    }
  )

}
