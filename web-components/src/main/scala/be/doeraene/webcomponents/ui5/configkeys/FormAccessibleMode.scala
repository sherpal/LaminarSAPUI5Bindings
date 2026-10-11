package be.doeraene.webcomponents.ui5.configkeys

/** Accessibility modes of the Form. Based on the mode, the Form and its items will render different HTML elements and ARIA attributes, which are appropriate for the use-case. **Usage:** - "Display" mode should be used when the form consists of non-editable (e.g. texts) form items. - "Edit" mode should be used when the form consists of editable (e.g. input fields) form items.
  *
  * @since UI5 2.16.0
  */
sealed trait FormAccessibleMode {
  final def value: String = toString
}

object FormAccessibleMode extends EnumerationString[FormAccessibleMode] {

  /** Display mode. */
  case object Display extends FormAccessibleMode
  /** Edit mode. */
  case object Edit    extends FormAccessibleMode

  val allValues: List[FormAccessibleMode] = deriveAllValues

  def valueOf(value: FormAccessibleMode): String = value.value

}
