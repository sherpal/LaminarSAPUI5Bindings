package be.doeraene.webcomponents.ui5.configkeys

sealed trait PopoverHorizontalAlign {
  def value: String = toString

}

object PopoverHorizontalAlign extends EnumerationString[PopoverHorizontalAlign] {
  case object Center extends PopoverHorizontalAlign
  case object Left extends PopoverHorizontalAlign
  case object Right extends PopoverHorizontalAlign
  case object Stretch extends PopoverHorizontalAlign

  val allValues: List[PopoverHorizontalAlign] = List(Center, Left, Right, Stretch)

  def valueOf(value: PopoverHorizontalAlign): String = value.value
}
