package be.doeraene.webcomponents.ui5.configkeys

sealed trait ValueState {
  final def value: String = toString
}

object ValueState extends EnumerationString[ValueState] {

  case object None        extends ValueState
  case object Negative    extends ValueState
  case object Critical    extends ValueState
  case object Positive    extends ValueState
  case object Information extends ValueState

  @deprecated("Error value state has been renamed to Negative", since = "2.0.0")
  def Error = Negative

  @deprecated("Warning value state has been renamed to Critical", since = "2.0.0")
  def Warning = Critical

  @deprecated("Success value state has been renamed to Positive", since = "2.0.0")
  def Success = Positive

  val allValues: List[ValueState] = deriveAllValues

  def valueOf(value: ValueState): String = value.value
}
