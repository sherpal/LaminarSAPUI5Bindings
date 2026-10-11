package be.doeraene.webcomponents.ui5.configkeys

/** Available HeroBanner actions placement options. Defines where the actions slot is rendered within the hero banner header area.
  *
  * @since UI5 2.23.0
  */
sealed trait HeroBannerActionsPlacement {
  final def value: String = toString
}

object HeroBannerActionsPlacement extends EnumerationString[HeroBannerActionsPlacement] {

  /** Places the actions below the header text, aligned to the start. */
  case object BottomStart extends HeroBannerActionsPlacement
  /** Places the actions to the right of the header text, aligned to the top of the header row. */
  case object TopEnd      extends HeroBannerActionsPlacement

  val allValues: List[HeroBannerActionsPlacement] = deriveAllValues

  def valueOf(value: HeroBannerActionsPlacement): String = value.value

}
