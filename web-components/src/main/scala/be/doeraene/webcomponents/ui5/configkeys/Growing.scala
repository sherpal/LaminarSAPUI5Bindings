package be.doeraene.webcomponents.ui5.configkeys

import scala.util.hashing.Hashing.Default

sealed trait Growing:
  final def value: String = toString

object Growing extends EnumerationString[Growing] {

  case object Button extends Growing
  case object None   extends Growing

  val allValues: List[Growing] = deriveAllValues

  def valueOf(value: Growing): String = value.value
}
