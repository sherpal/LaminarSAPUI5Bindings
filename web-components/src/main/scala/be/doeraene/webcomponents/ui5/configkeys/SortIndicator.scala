package be.doeraene.webcomponents.ui5.configkeys

sealed trait SortIndicator {
  def value: String = toString
}

object SortIndicator extends EnumerationString[SortIndicator] {
  case object Ascending  extends SortIndicator
  case object Descending extends SortIndicator

  val allValues = deriveAllValues

  def valueOf(value: SortIndicator): String = value.value
}
