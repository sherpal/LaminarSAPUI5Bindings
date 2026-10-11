package be.doeraene.webcomponents.ui5.configkeys

/** ListItem accessible roles.
  *
  * @since UI5 2.9.0
  */
sealed trait BarAccessibleRole {
  final def value: String = toString
}

object BarAccessibleRole extends EnumerationString[BarAccessibleRole] {

  /** Represents the ARIA role "none". */
  case object None    extends BarAccessibleRole
  /** Represents the ARIA role "toolbar". */
  case object Toolbar extends BarAccessibleRole

  val allValues: List[BarAccessibleRole] = deriveAllValues

  def valueOf(value: BarAccessibleRole): String = value.value

}
