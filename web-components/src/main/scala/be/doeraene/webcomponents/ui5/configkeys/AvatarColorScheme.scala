package be.doeraene.webcomponents.ui5.configkeys

/** Defines the background color of the desired image.
  *
  * Available options are: Accent1 Accent2 Accent3 Accent4 Accent5 Accent6 Accent7 Accent8 Accent9 Accent10 Placeholder
  */
sealed trait AvatarColorScheme {
  def value: String = toString
}

object AvatarColorScheme extends EnumerationString[AvatarColorScheme] {

  case object Accent1     extends AvatarColorScheme
  case object Accent2     extends AvatarColorScheme
  case object Accent3     extends AvatarColorScheme
  case object Accent4     extends AvatarColorScheme
  case object Accent5     extends AvatarColorScheme
  case object Accent6     extends AvatarColorScheme
  case object Accent7     extends AvatarColorScheme
  case object Accent8     extends AvatarColorScheme
  case object Accent9     extends AvatarColorScheme
  case object Accent10    extends AvatarColorScheme
  case object Placeholder extends AvatarColorScheme

  def valueOf(value: AvatarColorScheme): String = value.value

  val allValues: List[AvatarColorScheme] = deriveAllValues

}
