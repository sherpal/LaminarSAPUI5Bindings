package be.doeraene.webcomponents.ui5.configkeys

import scala.util.hashing.Hashing.Default

sealed trait CalendarWeekNumbering:
  final def value: String = toString

object CalendarWeekNumbering extends EnumerationString[CalendarWeekNumbering] {

  case object Default            extends CalendarWeekNumbering
  case object ISO_8601           extends CalendarWeekNumbering
  case object MiddleEastern      extends CalendarWeekNumbering
  case object WesternTraditional extends CalendarWeekNumbering

  val allValues: List[CalendarWeekNumbering] = deriveAllValues

  def valueOf(value: CalendarWeekNumbering): String = value.value
}
