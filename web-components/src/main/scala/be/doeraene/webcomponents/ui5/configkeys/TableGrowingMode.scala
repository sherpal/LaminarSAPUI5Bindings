package be.doeraene.webcomponents.ui5.configkeys

sealed trait TableGrowingMode {
  final def value: String = toString
}

object TableGrowingMode extends EnumerationString[TableGrowingMode] {

  case object Button extends TableGrowingMode
  case object Scroll extends TableGrowingMode

  val allValues: List[TableGrowingMode] = deriveAllValues

  def valueOf(value: TableGrowingMode): String = value.value

}
