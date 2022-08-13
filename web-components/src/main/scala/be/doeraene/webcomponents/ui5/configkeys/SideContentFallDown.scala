package be.doeraene.webcomponents.ui5.configkeys

sealed trait SideContentFallDown {
  def value: String = toString
}

object SideContentFallDown extends EnumerationString[SideContentFallDown] {
  case object BelowXL extends SideContentFallDown
  case object BelowL extends SideContentFallDown
  case object BelowM extends SideContentFallDown
  case object OnMinimumWidth extends SideContentFallDown

  val allValues: List[SideContentFallDown] = List(BelowXL, BelowL, BelowM, OnMinimumWidth)

  def valueOf(value: SideContentFallDown): String = value.value
}
