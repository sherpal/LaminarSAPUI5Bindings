package be.doeraene.webcomponents.ui5.configkeys

sealed trait PopoverVerticalAlign {
  def value: String = toString

}

object PopoverVerticalAlign extends EnumerationString[PopoverVerticalAlign] {
  case object Center extends PopoverVerticalAlign
  case object Top extends PopoverVerticalAlign
  case object Bottom extends PopoverVerticalAlign
  case object Stretch extends PopoverVerticalAlign

  val allValues: List[PopoverVerticalAlign] = List(Center, Top, Bottom, Stretch)

  def valueOf(value: PopoverVerticalAlign): String = value.value
}
