package be.doeraene.webcomponents.ui5.configkeys

sealed trait TableSingleSelectBehaviour {
  final def value: String = toString
}

object TableSingleSelectBehaviour extends EnumerationString[TableSingleSelectBehaviour] {
  case object RowSelector extends TableSingleSelectBehaviour
  case object RowOnly     extends TableSingleSelectBehaviour

  val allValues: List[TableSingleSelectBehaviour] = deriveAllValues

  def valueOf(value: TableSingleSelectBehaviour): String = value.value
}
