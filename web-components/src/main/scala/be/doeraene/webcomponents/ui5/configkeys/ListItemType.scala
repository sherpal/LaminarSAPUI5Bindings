package be.doeraene.webcomponents.ui5.configkeys

sealed trait ListItemType {
  final def value: String = toString
}

object ListItemType extends EnumerationString[ListItemType] {

  case object Active     extends ListItemType
  case object Inactive   extends ListItemType
  case object Detail     extends ListItemType
  case object Navigation extends ListItemType

  val allValues: List[ListItemType] = deriveAllValues

  def valueOf(value: ListItemType): String = value.value

}
