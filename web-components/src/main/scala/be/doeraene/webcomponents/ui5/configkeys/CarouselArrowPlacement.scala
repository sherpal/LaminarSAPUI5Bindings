package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.EnumerationString

sealed trait CarouselArrowsPlacement {
  def value: String = toString
}

object CarouselArrowsPlacement extends EnumerationString[CarouselArrowsPlacement] {

  case object Content extends CarouselArrowsPlacement
  case object Navigation extends CarouselArrowsPlacement

  val allValues: List[CarouselArrowsPlacement] = List(Content, Navigation)

  override def valueOf(value: CarouselArrowsPlacement): String = value.value

}
