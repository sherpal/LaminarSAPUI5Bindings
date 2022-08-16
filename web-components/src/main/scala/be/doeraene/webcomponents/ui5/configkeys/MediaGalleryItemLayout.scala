package be.doeraene.webcomponents.ui5.configkeys

sealed trait MediaGalleryItemLayout {
  def value: String = toString
}

object MediaGalleryItemLayout extends EnumerationString[MediaGalleryItemLayout] {
  case object Square extends MediaGalleryItemLayout
  case object Wide extends MediaGalleryItemLayout

  val allValues: List[MediaGalleryItemLayout] = List(Square, Wide)

  def valueOf(value: MediaGalleryItemLayout): String = value.value
}
