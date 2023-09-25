package be.doeraene.webcomponents.ui5.configkeys

sealed trait SegmentedButtonMode {
  def value: String = toString
}

object SegmentedButtonMode extends EnumerationString[SegmentedButtonMode] {
  case object SingleSelect extends SegmentedButtonMode
  case object MultiSelect extends SegmentedButtonMode

  val allValues: List[SegmentedButtonMode] = List(SingleSelect, MultiSelect)

  def valueOf(value: SegmentedButtonMode): String = value.value
}
