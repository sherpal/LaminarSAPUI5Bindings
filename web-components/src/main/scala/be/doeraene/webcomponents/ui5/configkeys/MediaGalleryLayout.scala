package be.doeraene.webcomponents.ui5.configkeys

sealed trait MediaGalleryLayout {
  def value: String = toString
}

object MediaGalleryLayout extends EnumerationString[MediaGalleryLayout] {
  case object Auto extends MediaGalleryLayout
  case object Vertical extends MediaGalleryLayout
  case object Horizontal extends MediaGalleryLayout

  val allValues: List[MediaGalleryLayout] = List(Auto, Vertical, Horizontal)

  def valueOf(value: MediaGalleryLayout): String = value.value

}
