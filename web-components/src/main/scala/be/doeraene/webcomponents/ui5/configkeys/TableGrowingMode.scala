package be.doeraene.webcomponents.ui5.configkeys

trait TableGrowingMode {
  final def value: String = toString
}

object TableGrowingMode extends EnumerationString[TableGrowingMode] {

  case object Button extends TableGrowingMode
  case object Scroll extends TableGrowingMode
  case object None extends TableGrowingMode

  val allValues: List[TableGrowingMode] = List(Button, Scroll, None)

  def valueOf(value: TableGrowingMode): String = value.value

}
