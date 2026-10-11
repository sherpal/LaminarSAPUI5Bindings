package be.doeraene.webcomponents.ui5.configkeys

/** Selectors of the table header row in multi-selection scenarios.
  *
  * @since UI5 2.12
  */
sealed trait TableSelectionMultiHeaderSelector {
  final def value: String = toString
}

object TableSelectionMultiHeaderSelector extends EnumerationString[TableSelectionMultiHeaderSelector] {

  /** Renders an icon in the table header row that removes the selection of all rows. */
  case object ClearAll  extends TableSelectionMultiHeaderSelector
  /** Renders a checkbox in the table header row that toggles the selection of all rows. */
  case object SelectAll extends TableSelectionMultiHeaderSelector

  val allValues: List[TableSelectionMultiHeaderSelector] = deriveAllValues

  def valueOf(value: TableSelectionMultiHeaderSelector): String = value.value

}
