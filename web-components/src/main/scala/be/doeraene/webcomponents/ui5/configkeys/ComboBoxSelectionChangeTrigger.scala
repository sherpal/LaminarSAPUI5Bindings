package be.doeraene.webcomponents.ui5.configkeys

/** Describes the source of a `selection-change` event fired by the `ui5-combobox`.
  *
  * @since UI5 2.24.0
  */
sealed trait ComboBoxSelectionChangeTrigger {
  final def value: String = toString
}

object ComboBoxSelectionChangeTrigger extends EnumerationString[ComboBoxSelectionChangeTrigger] {

  /** The selection was made by type-ahead (auto-completion while typing). */
  case object Typeahead extends ComboBoxSelectionChangeTrigger
  /** The selection was made by clicking on an item. */
  case object Click     extends ComboBoxSelectionChangeTrigger
  /** The selection was made with keyboard navigation. */
  case object Keyboard  extends ComboBoxSelectionChangeTrigger

  val allValues: List[ComboBoxSelectionChangeTrigger] = deriveAllValues

  def valueOf(value: ComboBoxSelectionChangeTrigger): String = value.value

}
