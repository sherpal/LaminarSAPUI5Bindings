package be.doeraene.webcomponents.ui5.configkeys

import scala.util.hashing.Hashing.Default

sealed trait NavigationLayoutMode:
  final def value: String = toString

object NavigationLayoutMode extends EnumerationString[NavigationLayoutMode] {

  case object Auto      extends NavigationLayoutMode
  case object Collapsed extends NavigationLayoutMode
  case object Expanded  extends NavigationLayoutMode

  val allValues: List[NavigationLayoutMode] = deriveAllValues

  def valueOf(value: NavigationLayoutMode): String = value.value
}
