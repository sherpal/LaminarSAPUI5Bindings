package be.doeraene.webcomponents.ui5.configkeys

sealed trait TextOverflowMode {
  final def value: String = toString
}

object TextOverflowMode extends EnumerationString[TextOverflowMode] {
  case object InPlace extends TextOverflowMode
  case object Popover extends TextOverflowMode

  val allValues: List[TextOverflowMode] = deriveAllValues

  def valueOf(value: TextOverflowMode): String = value.value
}
