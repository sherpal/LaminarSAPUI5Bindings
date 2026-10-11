package be.doeraene.webcomponents.ui5.configkeys

/** Available HeroBanner columns ratio options. Defines how the two content blocks are sized relative to each other within the hero banner. When no value is set, the content renders in a single column.
  *
  * @since UI5 2.23.0
  */
sealed trait HeroBannerColumnsRatio {
  final def value: String = toString
}

object HeroBannerColumnsRatio extends EnumerationString[HeroBannerColumnsRatio] {

  /** Two equal columns. Both content blocks share the available width equally. */
  case object Equal      extends HeroBannerColumnsRatio
  /** Two unequal columns. The first content block takes two-thirds of the width, the second takes one-third. */
  case object FirstWider extends HeroBannerColumnsRatio

  val allValues: List[HeroBannerColumnsRatio] = deriveAllValues

  def valueOf(value: HeroBannerColumnsRatio): String = value.value

}
