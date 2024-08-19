package be.doeraene.webcomponents.ui5.configkeys

/** Defines the HTML type of the component. Available options are: Text, Email, Number, Password, Tel, and URL.
  */
sealed trait InputType {
  final def value: String = toString
}

object InputType extends EnumerationString[InputType] {
  case object Text     extends InputType
  case object Email    extends InputType
  case object Number   extends InputType
  case object Password extends InputType
  case object Tel      extends InputType
  case object Url      extends InputType
  case object Search   extends InputType

  val allValues: List[InputType] = deriveAllValues

  def valueOf(value: InputType): String = value.value
}
