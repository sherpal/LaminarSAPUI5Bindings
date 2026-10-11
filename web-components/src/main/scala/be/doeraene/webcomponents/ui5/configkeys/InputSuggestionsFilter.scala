package be.doeraene.webcomponents.ui5.configkeys

/** Different filtering types of the Input.
  */
sealed trait InputSuggestionsFilter {
  final def value: String = toString
}

object InputSuggestionsFilter extends EnumerationString[InputSuggestionsFilter] {

  /** Defines contains filtering. */
  case object Contains          extends InputSuggestionsFilter
  /** Removes any filtering applied while typing */
  case object None              extends InputSuggestionsFilter
  /** Defines filtering by starting symbol of item's text. */
  case object StartsWith        extends InputSuggestionsFilter
  /** Defines filtering by first symbol of each word of item's text. */
  case object StartsWithPerTerm extends InputSuggestionsFilter

  val allValues: List[InputSuggestionsFilter] = deriveAllValues

  def valueOf(value: InputSuggestionsFilter): String = value.value

}
