package be.doeraene.webcomponents.ui5.configkeys

sealed trait CarouselPageIndicatorStyle {
  def value: String = toString
}

object CarouselPageIndicatorStyle extends EnumerationString[CarouselPageIndicatorStyle] {

  case object Default extends CarouselPageIndicatorStyle
  case object Numeric extends CarouselPageIndicatorStyle

  val allValues: List[CarouselPageIndicatorStyle] = List(Default, Numeric)

  def valueOf(value: CarouselPageIndicatorStyle): String = value.value

}
