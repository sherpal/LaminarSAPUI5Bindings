package be.doeraene.webcomponents.ui5.configkeys

import com.raquo.domtypes.generic.codecs.Codec

/** Defines the component design.
  *
  * Note: Available options are Default, Subtle, and Emphasized.
  */
trait LinkDesign

object LinkDesign extends EnumerationString[LinkDesign] {
  case object Default extends LinkDesign
  case object Subtle extends LinkDesign
  case object Emphasized extends LinkDesign

  val allValues: List[LinkDesign] = List(Default, Subtle, Emphasized)

  def valueOf(value: LinkDesign): String = value.toString

}
