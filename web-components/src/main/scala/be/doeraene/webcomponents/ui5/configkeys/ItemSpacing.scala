package be.doeraene.webcomponents.ui5.configkeys

import scala.util.hashing.Hashing.Default

sealed trait ItemSpacing:
  final def value: String = toString

object ItemSpacing extends EnumerationString[ItemSpacing] {

  case object Normal extends ItemSpacing
  case object Large  extends ItemSpacing

  val allValues: List[ItemSpacing] = deriveAllValues

  def valueOf(value: ItemSpacing): String = value.value
}
