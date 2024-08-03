package be.doeraene.webcomponents.ui5.configkeys

import scala.compiletime.ops.int.<=

sealed trait ColourScheme {
  def value: String = toString.tail
}

object ColourScheme extends EnumerationString[ColourScheme] {

  case object _1  extends ColourScheme
  case object _2  extends ColourScheme
  case object _3  extends ColourScheme
  case object _4  extends ColourScheme
  case object _5  extends ColourScheme
  case object _6  extends ColourScheme
  case object _7  extends ColourScheme
  case object _8  extends ColourScheme
  case object _9  extends ColourScheme
  case object _10 extends ColourScheme

  inline def fromInt[N <: Int](n: N): ColourScheme =
    inline n match {
      case 1  => _1
      case 2  => _2
      case 3  => _3
      case 4  => _4
      case 5  => _5
      case 6  => _6
      case 7  => _7
      case 8  => _8
      case 9  => _9
      case 10 => _10
    }

  override val allValues: List[ColourScheme] = deriveAllValues

  override def valueOf(value: ColourScheme): String = value.value

}
