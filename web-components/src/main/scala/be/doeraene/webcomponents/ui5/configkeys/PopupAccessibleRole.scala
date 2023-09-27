package be.doeraene.webcomponents.ui5.configkeys

sealed trait PopupAccessibleRole {
  final def value: String = toString
}

object PopupAccessibleRole extends EnumerationString[PopupAccessibleRole] {

  case object AlertDialog extends PopupAccessibleRole
  case object Dialog      extends PopupAccessibleRole
  case object None        extends PopupAccessibleRole

  val allValues: List[PopupAccessibleRole] = List(AlertDialog, Dialog, None)

  def valueOf(value: PopupAccessibleRole): String = value.value

}
