package be.doeraene.webcomponents.ui5.configkeys

/** Different types of ViewSettings.
  */
sealed trait ViewSettingsDialogMode {
  final def value: String = toString
}

object ViewSettingsDialogMode extends EnumerationString[ViewSettingsDialogMode] {

  /** Filter type */
  case object Filter extends ViewSettingsDialogMode
  /** Group type */
  case object Group  extends ViewSettingsDialogMode
  /** Default type */
  case object Sort   extends ViewSettingsDialogMode

  val allValues: List[ViewSettingsDialogMode] = deriveAllValues

  def valueOf(value: ViewSettingsDialogMode): String = value.value

}
