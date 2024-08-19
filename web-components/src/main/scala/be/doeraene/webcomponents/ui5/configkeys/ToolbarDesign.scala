package be.doeraene.webcomponents.ui5.configkeys

sealed trait ToolbarDesign {
  final def value: String = toString
}

object ToolbarDesign extends EnumerationString[ToolbarDesign] {

  case object Solid       extends ToolbarDesign
  case object Transparent extends ToolbarDesign

  val allValues: List[ToolbarDesign] = deriveAllValues

  def valueOf(value: ToolbarDesign): String = value.value
}
