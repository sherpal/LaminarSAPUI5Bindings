package be.doeraene.webcomponents.ui5.configkeys

sealed trait PopoverPlacementType {
  final def value: String = toString
}

object PopoverPlacementType extends EnumerationString[PopoverPlacementType] {

  case object Start  extends PopoverPlacementType
  case object End    extends PopoverPlacementType
  case object Top    extends PopoverPlacementType
  case object Bottom extends PopoverPlacementType

  @deprecated("Left horizontal align has been renamed to Start", since = "2.0.0")
  def Left = Start
  @deprecated("Right horizontal align has been renamed to End", since = "2.0.0")
  def Right = End

  val allValues: List[PopoverPlacementType] = deriveAllValues

  def valueOf(value: PopoverPlacementType): String = value.value

}
