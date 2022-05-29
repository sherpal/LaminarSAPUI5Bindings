package be.doeraene.webcomponents.ui5.configkeys

/** Defines the background color of the desired image.
  *
  * Available options are: Accent1 Accent2 Accent3 Accent4 Accent5 Accent6 Accent7 Accent8 Accent9 Accent10 Placeholder
  */
sealed trait AvatarColorScheme {
  def value: String
}

object AvatarColorScheme extends EnumerationString[AvatarColorScheme] {

  case object Accent1 extends AvatarColorScheme {
    override def value: String = "Accent1"
  }

  case object Accent2 extends AvatarColorScheme {
    override def value: String = "Accent2"
  }

  case object Accent3 extends AvatarColorScheme {
    override def value: String = "Accent3"
  }

  case object Accent4 extends AvatarColorScheme {
    override def value: String = "Accent4"
  }

  case object Accent5 extends AvatarColorScheme {
    override def value: String = "Accent5"
  }

  case object Accent6 extends AvatarColorScheme {
    override def value: String = "Accent6"
  }

  case object Accent7 extends AvatarColorScheme {
    override def value: String = "Accent7"
  }

  case object Accent8 extends AvatarColorScheme {
    override def value: String = "Accent8"
  }

  case object Accent9 extends AvatarColorScheme {
    override def value: String = "Accent9"
  }

  case object Accent10 extends AvatarColorScheme {
    override def value: String = "Accent10"
  }

  case object Placeholder extends AvatarColorScheme {
    override def value: String = "Placeholder"
  }

  def valueOf(value: AvatarColorScheme): String = value.toString

  val allValues: List[AvatarColorScheme] = List(
    Accent1,
    Accent2,
    Accent3,
    Accent4,
    Accent5,
    Accent6,
    Accent7,
    Accent8,
    Accent9,
    Accent10,
    Placeholder
  )

}
