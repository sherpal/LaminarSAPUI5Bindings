package be.doeraene.webcomponents.ui5.configkeys

sealed trait ValueState {
  final def value: String = toString
}

object ValueState extends EnumerationString[ValueState] {

  case object None extends ValueState
  case object Error extends ValueState
  case object Warning extends ValueState
  case object Success extends ValueState
  case object Information extends ValueState

  val allValues: List[ValueState] = List(None, Error, Warning, Success, Information)

  def valueOf(value: ValueState): String = value.value
}
