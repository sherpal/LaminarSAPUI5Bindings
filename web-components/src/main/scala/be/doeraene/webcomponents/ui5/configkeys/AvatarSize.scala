package be.doeraene.webcomponents.ui5.configkeys

/** Defines predefined size of the component.
  *
  * Available options are: XS S M L XL
  */
sealed trait AvatarSize {
  final def value: String = toString
}

object AvatarSize extends EnumerationString[AvatarSize] {
  case object XS extends AvatarSize
  case object S extends AvatarSize
  case object M extends AvatarSize
  case object L extends AvatarSize
  case object XL extends AvatarSize

  val allValues: List[AvatarSize] = List(XS, S, M, L, XL)

  def valueOf(value: AvatarSize): String = value.value
}
