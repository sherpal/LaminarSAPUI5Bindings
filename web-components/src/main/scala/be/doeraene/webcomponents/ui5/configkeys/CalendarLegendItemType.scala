package be.doeraene.webcomponents.ui5.configkeys

sealed trait CalendarLegendItemType {
  final def value: String = toString
}

object CalendarLegendItemType extends EnumerationString[CalendarLegendItemType] {

  case object None       extends CalendarLegendItemType
  case object Working    extends CalendarLegendItemType
  case object NonWorking extends CalendarLegendItemType
  case object Type01     extends CalendarLegendItemType
  case object Type02     extends CalendarLegendItemType
  case object Type03     extends CalendarLegendItemType
  case object Type04     extends CalendarLegendItemType
  case object Type05     extends CalendarLegendItemType
  case object Type06     extends CalendarLegendItemType
  case object Type07     extends CalendarLegendItemType
  case object Type08     extends CalendarLegendItemType
  case object Type09     extends CalendarLegendItemType
  case object Type10     extends CalendarLegendItemType
  case object Type11     extends CalendarLegendItemType
  case object Type12     extends CalendarLegendItemType
  case object Type13     extends CalendarLegendItemType
  case object Type14     extends CalendarLegendItemType
  case object Type15     extends CalendarLegendItemType
  case object Type16     extends CalendarLegendItemType
  case object Type17     extends CalendarLegendItemType
  case object Type18     extends CalendarLegendItemType
  case object Type19     extends CalendarLegendItemType
  case object Type20     extends CalendarLegendItemType

  val allValues: List[CalendarLegendItemType] = deriveAllValues

  def valueOf(value: CalendarLegendItemType): String = value.value
}
