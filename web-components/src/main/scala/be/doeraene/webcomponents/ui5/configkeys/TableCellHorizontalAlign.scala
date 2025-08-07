package be.doeraene.webcomponents.ui5.configkeys

sealed trait TableCellHorizontalAlign {
  def value: String = toString
}

object TableCellHorizontalAlign extends EnumerationString[TableCellHorizontalAlign] {
  case object Center extends TableCellHorizontalAlign
  case object Start  extends TableCellHorizontalAlign
  case object End    extends TableCellHorizontalAlign
  case object Left   extends TableCellHorizontalAlign
  case object Right  extends TableCellHorizontalAlign

  val allValues: List[TableCellHorizontalAlign] = deriveAllValues

  def valueOf(value: TableCellHorizontalAlign): String = value.value
}
