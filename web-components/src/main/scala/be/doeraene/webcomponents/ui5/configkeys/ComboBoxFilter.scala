package be.doeraene.webcomponents.ui5.configkeys

sealed trait ComboBoxFilter {
  final def value: String = toString
}

object ComboBoxFilter extends EnumerationString[ComboBoxFilter] {

  /** Available options are: StartsWithPerTerm, StartsWith and Contains. */
  case object StartsWithPerTerm extends ComboBoxFilter
  case object StartsWith extends ComboBoxFilter
  case object Contains extends ComboBoxFilter

  val allValues: List[ComboBoxFilter] = List(StartsWithPerTerm, StartsWith, Contains)

  def valueOf(filter: ComboBoxFilter): String = filter.value
}
