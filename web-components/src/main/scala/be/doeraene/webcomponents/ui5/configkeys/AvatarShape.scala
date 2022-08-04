package be.doeraene.webcomponents.ui5.configkeys

sealed trait AvatarShape {
  def name: String = toString
}

object AvatarShape extends EnumerationString[AvatarShape] {
  case object Circle extends AvatarShape
  case object Square extends AvatarShape

  def valueOf(value: AvatarShape): String = value.name

  val allValues: List[AvatarShape] = List(Circle, Square)
}
