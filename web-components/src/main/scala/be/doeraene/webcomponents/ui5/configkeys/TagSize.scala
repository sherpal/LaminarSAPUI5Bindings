package be.doeraene.webcomponents.ui5.configkeys

import com.raquo.laminar.codecs.Codec
import scala.deriving.Mirror

/** Defines the component size.
  */
sealed trait TagSize

object TagSize extends EnumerationString[TagSize] {
  case object S extends TagSize
  case object L extends TagSize

  val allValues: List[TagSize] = deriveAllValues

  def valueOf(value: TagSize): String = value.toString

}
