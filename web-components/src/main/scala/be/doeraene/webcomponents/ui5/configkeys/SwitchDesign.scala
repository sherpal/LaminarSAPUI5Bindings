package be.doeraene.webcomponents.ui5.configkeys

sealed trait SwitchDesign {
  def value: String = toString
}

object SwitchDesign extends EnumerationString[SwitchDesign] {

  case object Textual extends SwitchDesign
  case object Graphical extends SwitchDesign

  val allValues: List[SwitchDesign] = List(Textual, Graphical)

  def valueOf(value: SwitchDesign): String = value.value

}
