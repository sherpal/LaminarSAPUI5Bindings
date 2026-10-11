package be.doeraene.webcomponents.ui5.configkeys

/** Defines border designs.
  */
sealed trait CarouselPageIndicatorBorderDesign {
  final def value: String = toString
}

object CarouselPageIndicatorBorderDesign extends EnumerationString[CarouselPageIndicatorBorderDesign] {

  /** Specifies no border. */
  case object None  extends CarouselPageIndicatorBorderDesign
  /** A solid border color dependent on the theme. */
  case object Solid extends CarouselPageIndicatorBorderDesign

  val allValues: List[CarouselPageIndicatorBorderDesign] = deriveAllValues

  def valueOf(value: CarouselPageIndicatorBorderDesign): String = value.value

}
