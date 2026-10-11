package be.doeraene.webcomponents.ui5.configkeys

/** Menu item group check modes.
  *
  * @since UI5 2.12.0
  */
sealed trait MenuItemGroupCheckMode {
  final def value: String = toString
}

object MenuItemGroupCheckMode extends EnumerationString[MenuItemGroupCheckMode] {

  /** Multiple items check mode (multiple items in a group can be checked at a time) */
  case object Multiple extends MenuItemGroupCheckMode
  /** default type (items in a group cannot be checked) */
  case object None     extends MenuItemGroupCheckMode
  /** Single item check mode (only one item in a group can be checked at a time) */
  case object Single   extends MenuItemGroupCheckMode

  val allValues: List[MenuItemGroupCheckMode] = deriveAllValues

  def valueOf(value: MenuItemGroupCheckMode): String = value.value

}
