package be.doeraene.webcomponents.ui5.configkeys

/** Link accessible roles.
  *
  * @since UI5 2.0.0
  */
sealed trait LinkAccessibleRole {
  final def value: String = toString
}

object LinkAccessibleRole extends EnumerationString[LinkAccessibleRole] {

  /** Represents the ARIA role "button". */
  case object Button extends LinkAccessibleRole
  /** Represents Default (link) ARIA role. */
  case object Link   extends LinkAccessibleRole

  val allValues: List[LinkAccessibleRole] = deriveAllValues

  def valueOf(value: LinkAccessibleRole): String = value.value

}
