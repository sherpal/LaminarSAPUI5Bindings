package be.doeraene.webcomponents.ui5.configkeys

sealed trait SegmentedButtonMode {
  def value: String = toString
}

object SegmentedButtonMode extends EnumerationString[SegmentedButtonMode] {
  case object Single   extends SegmentedButtonMode
  case object Multiple extends SegmentedButtonMode

  @deprecated("SingleSelect segmented button mode has been renamed to Single", since = "2.0.0")
  def SingleSelect = Single
  @deprecated("MultiSelect segmented button mode has been renamed to Multiple", since = "2.0.0")
  def MultiSelect = Multiple

  val allValues: List[SegmentedButtonMode] = deriveAllValues

  def valueOf(value: SegmentedButtonMode): String = value.value
}
