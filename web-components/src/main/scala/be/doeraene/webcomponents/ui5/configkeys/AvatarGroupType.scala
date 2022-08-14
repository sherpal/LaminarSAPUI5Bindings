package be.doeraene.webcomponents.ui5.configkeys

sealed trait AvatarGroupType {
  def value: String = toString
}

object AvatarGroupType extends EnumerationString[AvatarGroupType] {
  case object Group extends AvatarGroupType
  case object Individual extends AvatarGroupType

  val allValues: List[AvatarGroupType] = List(Group, Individual)

  def valueOf(value: AvatarGroupType): String = value.value

}
