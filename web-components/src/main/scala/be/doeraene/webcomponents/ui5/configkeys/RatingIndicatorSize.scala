package be.doeraene.webcomponents.ui5.configkeys

sealed trait RatingIndicatorSize {
  def value: String = toString
}

object RatingIndicatorSize extends EnumerationString[RatingIndicatorSize] {

  case object S extends RatingIndicatorSize
  case object M extends RatingIndicatorSize
  case object L extends RatingIndicatorSize

  override def valueOf(value: RatingIndicatorSize): String = value.value

  override val allValues: List[RatingIndicatorSize] = deriveAllValues

}
