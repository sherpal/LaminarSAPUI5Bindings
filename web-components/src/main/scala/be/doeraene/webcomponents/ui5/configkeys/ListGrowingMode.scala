package be.doeraene.webcomponents.ui5.configkeys

trait ListGrowingMode {
  def value: String = toString
}

object ListGrowingMode extends EnumerationString[ListGrowingMode] {
  case object None extends ListGrowingMode
  case object Button extends ListGrowingMode
  case object Scroll extends ListGrowingMode

  val allValues: List[ListGrowingMode] = List(None, Button, Scroll)

  def valueOf(value: ListGrowingMode): String = value.value
}
