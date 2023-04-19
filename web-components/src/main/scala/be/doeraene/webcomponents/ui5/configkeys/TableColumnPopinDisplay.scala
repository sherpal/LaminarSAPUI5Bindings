package be.doeraene.webcomponents.ui5.configkeys

import scala.scalajs.js

@js.native
trait TableColumnPopinDisplay extends js.Any

object TableColumnPopinDisplay extends EnumerationString[TableColumnPopinDisplay] {

  private def _tableColumnPopinDisplay(value: String): TableColumnPopinDisplay =
    value.asInstanceOf[TableColumnPopinDisplay]

  def Block: TableColumnPopinDisplay  = _tableColumnPopinDisplay("Block")
  def Inline: TableColumnPopinDisplay = _tableColumnPopinDisplay("Inline")

  def valueOf(value: TableColumnPopinDisplay): String = value.asInstanceOf[String]

  val allValues: List[TableColumnPopinDisplay] = List(Block, Inline)

}
