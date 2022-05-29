package be.doeraene.webcomponents.ui5.configkeys

sealed trait WrappingType {
  def value: String = toString
}

object WrappingType extends EnumerationString[WrappingType] {

  case object None extends WrappingType
  case object Normal extends WrappingType

  override val allValues: List[WrappingType] = List(None, Normal)

  override def valueOf(value: WrappingType): String = value.value

}
