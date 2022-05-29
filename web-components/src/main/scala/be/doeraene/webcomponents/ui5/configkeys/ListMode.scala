package be.doeraene.webcomponents.ui5.configkeys

sealed trait ListMode {
  final def value: String = toString
}

object ListMode extends EnumerationString[ListMode] {

  case object None extends ListMode
  case object SingleSelect extends ListMode
  case object SingleSelectBegin extends ListMode
  case object SingleSelectEnd extends ListMode
  case object MultiSelect extends ListMode
  case object Delete extends ListMode

  val allValues: List[ListMode] = List(None, SingleSelect, SingleSelectBegin, SingleSelectEnd, MultiSelect, Delete)

  def valueOf(value: ListMode): String = value.value

}
