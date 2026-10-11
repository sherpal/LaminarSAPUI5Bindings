package be.doeraene.webcomponents.ui5.configkeys

/** Defines background designs.
  */
sealed trait CarouselBackgroundDesign {
  final def value: String = toString
}

object CarouselBackgroundDesign extends EnumerationString[CarouselBackgroundDesign] {

  /** A solid background color dependent on the theme. */
  case object Solid       extends CarouselBackgroundDesign
  /** A translucent background depending on the opacity value of the theme. */
  case object Translucent extends CarouselBackgroundDesign
  /** Transparent background. */
  case object Transparent extends CarouselBackgroundDesign

  val allValues: List[CarouselBackgroundDesign] = deriveAllValues

  def valueOf(value: CarouselBackgroundDesign): String = value.value

}
