package be.doeraene.webcomponents.ui5.configkeys

sealed trait PopoverPlacementType {
  final def value: String = toString
}

object PopoverPlacementType extends EnumerationString[PopoverPlacementType] {

  case object Left extends PopoverPlacementType
  case object Right extends PopoverPlacementType
  case object Top extends PopoverPlacementType
  case object Bottom extends PopoverPlacementType

  val allValues: List[PopoverPlacementType] = List(Left, Right, Top, Bottom)

  def valueOf(value: PopoverPlacementType): String = value.value

}
