package be.doeraene.webcomponents.ui5.configkeys

sealed trait ListSeparator {
  def value: String = toString
}

object ListSeparator extends EnumerationString[ListSeparator] {

  case object All extends ListSeparator
  case object Inner extends ListSeparator
  case object None extends ListSeparator

  val allValues: List[ListSeparator] = List(All, Inner, None)

  def valueOf(value: ListSeparator): String = value.value
}
