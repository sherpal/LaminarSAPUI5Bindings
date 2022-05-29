package be.doeraene.webcomponents.ui5.configkeys

sealed trait TimelineLayout {
  final def value: String = toString
}

object TimelineLayout extends EnumerationString[TimelineLayout] {
  case object Vertical extends TimelineLayout
  case object Horizontal extends TimelineLayout

  val allValues: List[TimelineLayout] = List(Vertical, Horizontal)

  def valueOf(timelineLayout: TimelineLayout): String = timelineLayout.value
}
