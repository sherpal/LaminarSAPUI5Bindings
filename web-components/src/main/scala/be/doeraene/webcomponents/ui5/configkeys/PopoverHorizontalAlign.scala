package be.doeraene.webcomponents.ui5.configkeys

sealed trait PopoverHorizontalAlign {
  def value: String = toString

}

object PopoverHorizontalAlign extends EnumerationString[PopoverHorizontalAlign] {
  case object Center  extends PopoverHorizontalAlign
  case object Start   extends PopoverHorizontalAlign
  case object End     extends PopoverHorizontalAlign
  case object Stretch extends PopoverHorizontalAlign

  @deprecated("Left horizontal align has been renamed to Start", since = "2.0.0")
  def Left = Start
  @deprecated("Right horizontal align has been renamed to End", since = "2.0.0")
  def Right = End

  val allValues: List[PopoverHorizontalAlign] = deriveAllValues

  def valueOf(value: PopoverHorizontalAlign): String = value.value
}
