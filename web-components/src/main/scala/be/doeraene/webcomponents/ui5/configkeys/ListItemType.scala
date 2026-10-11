package be.doeraene.webcomponents.ui5.configkeys

sealed trait ListItemType {
  final def value: String = toString
}

object ListItemType extends EnumerationString[ListItemType] {

  case object Active     extends ListItemType
  case object Inactive   extends ListItemType
  case object Detail     extends ListItemType
  case object Navigation extends ListItemType

  /** Indicates the list item does not have any active feedback when item is pressed, but the item can still be
    * selected when a selection mode is active. The `item-click` event is not fired for items of this type.
    *
    * @since UI5 2.26.0
    */
  case object InactiveSelectable extends ListItemType

  val allValues: List[ListItemType] = deriveAllValues

  def valueOf(value: ListItemType): String = value.value

}
