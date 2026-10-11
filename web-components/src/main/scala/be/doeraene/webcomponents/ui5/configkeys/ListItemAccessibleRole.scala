package be.doeraene.webcomponents.ui5.configkeys

/** ListItem accessible roles.
  *
  * @since UI5 2.0.0
  */
sealed trait ListItemAccessibleRole {
  final def value: String = toString
}

object ListItemAccessibleRole extends EnumerationString[ListItemAccessibleRole] {

  /** Represents the ARIA role "listitem". (by default) */
  case object ListItem extends ListItemAccessibleRole
  /** Represents the ARIA role "menuitem". */
  case object MenuItem extends ListItemAccessibleRole
  /** Represents the ARIA role "none". */
  case object None     extends ListItemAccessibleRole
  /** Represents the ARIA role "option". */
  case object Option   extends ListItemAccessibleRole
  /** Represents the ARIA role "treeitem". */
  case object TreeItem extends ListItemAccessibleRole

  val allValues: List[ListItemAccessibleRole] = deriveAllValues

  def valueOf(value: ListItemAccessibleRole): String = value.value

}
