package be.doeraene.webcomponents.ui5.configkeys

sealed trait BusyIndicatorSize {
  def value: String = toString
}

object BusyIndicatorSize extends EnumerationString[BusyIndicatorSize] {

  case object S extends BusyIndicatorSize
  case object M extends BusyIndicatorSize
  case object L extends BusyIndicatorSize

  @deprecated("Small has been renamed to S", since = "2.0.0")
  def Small = S
  @deprecated("Medium has been renamed to M", since = "2.0.0")
  def Medium = M
  @deprecated("Large has been renamed to L", since = "2.0.0")
  def Large = L

  override def valueOf(value: BusyIndicatorSize): String = value.value

  override val allValues: List[BusyIndicatorSize] = deriveAllValues

}
