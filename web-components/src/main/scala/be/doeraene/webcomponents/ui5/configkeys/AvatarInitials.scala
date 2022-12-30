package be.doeraene.webcomponents.ui5.configkeys

import com.raquo.domtypes.generic.codecs.Codec

/** [[AvatarInitials]] are used within an Avatar instead of a picture.
  *
  * Initials in UI5 can only have one or two characters. This is enforced by this type.
  */
sealed trait AvatarInitials

object AvatarInitials {

  case class One(c: Char) extends AvatarInitials {
    def ~(other: One): Two = Two(c, other.c)
  }
  case class Two(c1: Char, c2: Char) extends AvatarInitials {
    def ~(other: One): Three = Three(c1, c2, other.c)
  }
  case class Three(c1: Char, c2: Char, c3: Char) extends AvatarInitials

  given Conversion[Char | (Char, Char) | (Char, Char, Char), AvatarInitials] with {
    def apply(c: Char | (Char, Char) | (Char, Char, Char)): AvatarInitials = c match {
      case c: Char               => One(c)
      case c: (Char, Char)       => Two(c._1, c._2)
      case c: (Char, Char, Char) => Three(c._1, c._2, c._3)
    }
  }

  object AsStringCodec extends Codec[AvatarInitials, String] {
    override def encode(scalaValue: AvatarInitials): String = scalaValue match {
      case One(c)            => c.toString
      case Two(c1, c2)       => s"$c1$c2"
      case Three(c1, c2, c3) => s"$c1$c2$c3"
    }

    override def decode(domValue: String): AvatarInitials = domValue.length match {
      case 1    => One(domValue(0))
      case 2    => Two(domValue(0), domValue(1))
      case 3    => Three(domValue(0), domValue(1), domValue(2))
      case more => throw new IllegalArgumentException(s"Invalid initials: $more")
    }
  }

}
