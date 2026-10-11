package be.doeraene.webcomponents.ui5.configkeys

/** Different types of NotificationListItemImportance.
  */
sealed trait NotificationListItemImportance {
  final def value: String = toString
}

object NotificationListItemImportance extends EnumerationString[NotificationListItemImportance] {

  case object Important extends NotificationListItemImportance
  case object Standard  extends NotificationListItemImportance

  val allValues: List[NotificationListItemImportance] = deriveAllValues

  def valueOf(value: NotificationListItemImportance): String = value.value

}
