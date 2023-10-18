package be.doeraene.webcomponents.ui5.configkeys

sealed trait ToolbarAlign {
  final def value: String = toString
}

object ToolbarAlign extends EnumerationString[ToolbarAlign] {
  case object End extends ToolbarAlign
  case object Start extends ToolbarAlign

  val allValues: List[ToolbarAlign] = List(End, Start)

  def valueOf(timelineLayout: ToolbarAlign): String = timelineLayout.value
}
