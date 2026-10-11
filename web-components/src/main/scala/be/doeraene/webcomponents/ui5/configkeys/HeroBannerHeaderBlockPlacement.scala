package be.doeraene.webcomponents.ui5.configkeys

/** Available HeroBanner header text block placement options. Defines the vertical placement of the header text block within the banner header area.
  *
  * @since UI5 2.23.0
  */
sealed trait HeroBannerHeaderBlockPlacement {
  final def value: String = toString
}

object HeroBannerHeaderBlockPlacement extends EnumerationString[HeroBannerHeaderBlockPlacement] {

  /** Places the header text block at the bottom of the header area. */
  case object Bottom extends HeroBannerHeaderBlockPlacement
  /** Places the header text block at the top of the header area. */
  case object Top    extends HeroBannerHeaderBlockPlacement

  val allValues: List[HeroBannerHeaderBlockPlacement] = deriveAllValues

  def valueOf(value: HeroBannerHeaderBlockPlacement): String = value.value

}
