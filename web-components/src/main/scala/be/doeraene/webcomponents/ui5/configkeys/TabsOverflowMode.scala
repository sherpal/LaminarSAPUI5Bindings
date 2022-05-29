package be.doeraene.webcomponents.ui5.configkeys

trait TabsOverflowMode {
  final def value: String = toString
}

object TabsOverflowMode extends EnumerationString[TabsOverflowMode] {
  case object End extends TabsOverflowMode
  case object StartAndEnd extends TabsOverflowMode

  val allValues: List[TabsOverflowMode] = List(End, StartAndEnd)

  def valueOf(value: TabsOverflowMode): String = value.value
}
