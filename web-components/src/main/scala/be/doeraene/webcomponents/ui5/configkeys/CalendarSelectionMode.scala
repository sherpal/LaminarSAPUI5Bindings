package be.doeraene.webcomponents.ui5.configkeys

sealed trait CalendarSelectionMode {
  def value: String = toString
}

object CalendarSelectionMode extends EnumerationString[CalendarSelectionMode] {
  case object Single extends CalendarSelectionMode
  case object Range extends CalendarSelectionMode
  case object Multiple extends CalendarSelectionMode

  val allValues: List[CalendarSelectionMode] = List(Single, Range, Multiple)

  def valueOf(value: CalendarSelectionMode): String = value.value
}
