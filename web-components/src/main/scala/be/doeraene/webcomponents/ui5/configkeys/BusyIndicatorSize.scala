package be.doeraene.webcomponents.ui5.configkeys

sealed trait BusyIndicatorSize {
  def value: String = toString
}

object BusyIndicatorSize extends EnumerationString[BusyIndicatorSize] {

  case object Small extends BusyIndicatorSize
  case object Medium extends BusyIndicatorSize
  case object Large extends BusyIndicatorSize

  override def valueOf(value: BusyIndicatorSize): String = value.value

  override val allValues: List[BusyIndicatorSize] = List(Small, Medium, Large)

}
