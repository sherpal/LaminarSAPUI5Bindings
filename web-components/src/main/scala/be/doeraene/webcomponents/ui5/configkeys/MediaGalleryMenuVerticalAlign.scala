package be.doeraene.webcomponents.ui5.configkeys

sealed trait MediaGalleryMenuVerticalAlign {
  def value: String = toString
}

object MediaGalleryMenuVerticalAlign extends EnumerationString[MediaGalleryMenuVerticalAlign] {
  case object Top extends MediaGalleryMenuVerticalAlign
  case object Bottom extends MediaGalleryMenuVerticalAlign

  val allValues: List[MediaGalleryMenuVerticalAlign] = List(Top, Bottom)

  def valueOf(value: MediaGalleryMenuVerticalAlign): String = value.value
}
