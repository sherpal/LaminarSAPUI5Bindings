package be.doeraene.webcomponents.ui5.configkeys

/** SideNavigationItem designs.
  *
  * @since UI5 2.7.0
  */
sealed trait SideNavigationItemDesign {
  final def value: String = toString
}

object SideNavigationItemDesign extends EnumerationString[SideNavigationItemDesign] {

  /** Design for items that trigger an action, such as opening a dialog. **Note:** Items with this design must not have sub-items. **Note:** Items that open a dialog must set `hasPopup="dialog"` via `accessibilityAttributes` property. */
  case object Action  extends SideNavigationItemDesign
  /** Design for items that perform navigation, contain navigation child items, or both. */
  case object Default extends SideNavigationItemDesign

  val allValues: List[SideNavigationItemDesign] = deriveAllValues

  def valueOf(value: SideNavigationItemDesign): String = value.value

}
