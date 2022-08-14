package be.doeraene.webcomponents.ui5.configkeys

sealed trait Priority {
  def value: String = toString
}

object Priority extends EnumerationString[Priority] {
  case object None extends Priority
  case object Low extends Priority
  case object Medium extends Priority
  case object High extends Priority

  val allValues: List[Priority] = List(None, Low, Medium, High)

  def valueOf(value: Priority): String = value.value
}
