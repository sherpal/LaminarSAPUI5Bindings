package be.doeraene.webcomponents.ui5.configkeys

sealed trait CarouselPageIndicatorType {
  def value: String = toString
}

object CarouselPageIndicatorType extends EnumerationString[CarouselPageIndicatorType] {

  case object Default extends CarouselPageIndicatorType
  case object Numeric extends CarouselPageIndicatorType

  val allValues: List[CarouselPageIndicatorType] = deriveAllValues

  def valueOf(value: CarouselPageIndicatorType): String = value.value

}
