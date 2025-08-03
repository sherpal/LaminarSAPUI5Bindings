package be.doeraene.webcomponents.ui5.configkeys

sealed trait OnOff:
  def value: String = toString

object OnOff extends EnumerationString[OnOff] {

  case object On  extends OnOff
  case object Off extends OnOff

  val allValues = deriveAllValues

  def valueOf(value: OnOff): String = value.value

}
