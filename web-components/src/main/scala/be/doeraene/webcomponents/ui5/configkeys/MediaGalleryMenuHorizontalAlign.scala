package be.doeraene.webcomponents.ui5.configkeys

sealed trait MediaGalleryMenuHorizontalAlign {
  def value: String = toString
}

object MediaGalleryMenuHorizontalAlign extends EnumerationString[MediaGalleryMenuHorizontalAlign] {
  case object Left extends MediaGalleryMenuHorizontalAlign
  case object Right extends MediaGalleryMenuHorizontalAlign

  val allValues: List[MediaGalleryMenuHorizontalAlign] = List(Left, Right)

  def valueOf(value: MediaGalleryMenuHorizontalAlign): String = value.value
}
