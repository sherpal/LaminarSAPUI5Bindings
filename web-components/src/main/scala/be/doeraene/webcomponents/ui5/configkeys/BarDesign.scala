package be.doeraene.webcomponents.ui5.configkeys

sealed trait BarDesign {
  final def value: String = toString
}

object BarDesign extends EnumerationString[BarDesign] {

  case object Header extends BarDesign
  case object Subheader extends BarDesign
  case object Footer extends BarDesign
  case object FloatingFooter extends BarDesign

  val allValues: List[BarDesign] = List(Header, Subheader, Footer, FloatingFooter)

  def valueOf(value: BarDesign): String = value.value
}
