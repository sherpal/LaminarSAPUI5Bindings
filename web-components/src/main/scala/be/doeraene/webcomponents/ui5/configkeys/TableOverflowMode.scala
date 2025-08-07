package be.doeraene.webcomponents.ui5.configkeys

sealed trait TableOverflowMode {
  final def value: String = toString
}

object TableOverflowMode extends EnumerationString[TableOverflowMode] {
  case object Scroll extends TableOverflowMode
  case object Popin  extends TableOverflowMode

  val allValues: List[TableOverflowMode] = deriveAllValues

  def valueOf(value: TableOverflowMode): String = value.value
}
