package be.doeraene.webcomponents.ui5.configkeys

sealed trait InteractiveAreaSize {
  final def value: String = toString
}

object InteractiveAreaSize extends EnumerationString[InteractiveAreaSize] {

  case object Normal extends InteractiveAreaSize
  case object Large  extends InteractiveAreaSize

  val allValues: List[InteractiveAreaSize] = deriveAllValues

  def valueOf(value: InteractiveAreaSize): String = value.value
}
