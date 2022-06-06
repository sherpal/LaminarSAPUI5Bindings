package be.doeraene.webcomponents.ui5.configkeys

sealed trait PanelAccessibleRole {
  final def value: String = toString
}

object PanelAccessibleRole extends EnumerationString[PanelAccessibleRole] {

  case object Form extends PanelAccessibleRole
  case object Region extends PanelAccessibleRole
  case object Complementary extends PanelAccessibleRole

  val allValues: List[PanelAccessibleRole] = List(Form, Region, Complementary)

  def valueOf(value: PanelAccessibleRole): String = value.value

}
