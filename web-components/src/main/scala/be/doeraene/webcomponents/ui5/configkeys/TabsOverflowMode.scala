package be.doeraene.webcomponents.ui5.configkeys

sealed trait TabsOverflowMode {
  final def value: String = toString
}

object TabsOverflowMode extends EnumerationString[TabsOverflowMode] {
  case object End         extends TabsOverflowMode
  case object StartAndEnd extends TabsOverflowMode

  val allValues: List[TabsOverflowMode] = deriveAllValues

  def valueOf(value: TabsOverflowMode): String = value.value
}
