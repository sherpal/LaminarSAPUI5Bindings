package be.doeraene.webcomponents.ui5.configkeys

sealed trait SideContentPosition {
  def value: String = toString
}

object SideContentPosition extends EnumerationString[SideContentPosition] {
  case object Start extends SideContentPosition
  case object End extends SideContentPosition

  val allValues: List[SideContentPosition] = List(Start, End)

  def valueOf(value: SideContentPosition): String = value.value
}
