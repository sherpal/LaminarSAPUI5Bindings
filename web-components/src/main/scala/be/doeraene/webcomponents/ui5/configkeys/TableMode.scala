package be.doeraene.webcomponents.ui5.configkeys

sealed trait TableMode {
  final def value: String = toString
}

object TableMode extends EnumerationString[TableMode] {

  case object MultiSelect extends TableMode
  case object SingleSelect extends TableMode
  case object None extends TableMode

  val allValues: List[TableMode] = List(MultiSelect, SingleSelect, None)

  def valueOf(value: TableMode): String = value.value
}
