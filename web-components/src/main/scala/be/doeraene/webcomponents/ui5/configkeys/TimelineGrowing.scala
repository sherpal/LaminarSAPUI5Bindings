package be.doeraene.webcomponents.ui5.configkeys

sealed trait TimelineGrowing {
  final def value: String = toString
}

object TimelineGrowing extends EnumerationString[TimelineGrowing] {

  case object None   extends TimelineGrowing
  case object Button extends TimelineGrowing
  case object Scroll extends TimelineGrowing

  val allValues: List[TimelineGrowing] = deriveAllValues

  def valueOf(value: TimelineGrowing): String = value.value
}
