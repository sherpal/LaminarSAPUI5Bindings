package be.doeraene.webcomponents.ui5.configkeys

sealed trait BusyIndicatorTextPlacement {
  final def value: String = toString
}

object BusyIndicatorTextPlacement extends EnumerationString[BusyIndicatorTextPlacement] {

  case object Top    extends BusyIndicatorTextPlacement
  case object Bottom extends BusyIndicatorTextPlacement

  val allValues: List[BusyIndicatorTextPlacement] = deriveAllValues

  def valueOf(value: BusyIndicatorTextPlacement): String = value.value
}
