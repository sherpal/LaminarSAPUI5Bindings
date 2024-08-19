package be.doeraene.webcomponents.ui5.configkeys

import com.raquo.laminar.codecs.Codec

/** Determines which illustration breakpoint variant is used.
  *
  * As IllustratedMessage adapts itself around the Illustration, the other elements of the component are displayed
  * differently on the different breakpoints/illustration sizes.
  */
sealed trait IllustratedMessageSize

object IllustratedMessageSize extends EnumerationString[IllustratedMessageSize] {
  case object Auto   extends IllustratedMessageSize
  case object Base   extends IllustratedMessageSize
  case object Dot    extends IllustratedMessageSize
  case object Spot   extends IllustratedMessageSize
  case object Dialog extends IllustratedMessageSize
  case object Scene  extends IllustratedMessageSize

  val allValues: List[IllustratedMessageSize] = deriveAllValues

  def valueOf(value: IllustratedMessageSize): String = value.toString

}
