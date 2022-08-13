package be.doeraene.webcomponents.ui5.configkeys

sealed trait SideContentVisibility {
  def value: String = toString
}

object SideContentVisibility extends EnumerationString[SideContentVisibility] {
  case object AlwaysShow extends SideContentVisibility
  case object ShowAboveL extends SideContentVisibility
  case object ShowAboveM extends SideContentVisibility
  case object ShowAboveS extends SideContentVisibility
  case object NeverShow extends SideContentVisibility

  val allValues: List[SideContentVisibility] = List(AlwaysShow, ShowAboveL, ShowAboveM, ShowAboveS, NeverShow)

  def valueOf(value: SideContentVisibility): String = value.value
}
