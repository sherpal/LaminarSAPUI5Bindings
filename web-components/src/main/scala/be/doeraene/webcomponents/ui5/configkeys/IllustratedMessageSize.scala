package be.doeraene.webcomponents.ui5.configkeys

import com.raquo.laminar.codecs.Codec

/** Determines which illustration breakpoint variant is used.
  *
  * As IllustratedMessage adapts itself around the Illustration, the other elements of the component are displayed
  * differently on the different breakpoints/illustration sizes.
  */
sealed trait IllustratedMessageSize

object IllustratedMessageSize extends EnumerationString[IllustratedMessageSize] {
  /** Automatically decides the illustration size depending on the width of the component. */
  case object Auto extends IllustratedMessageSize

  /** Base illustration size: only the text and the buttons are displayed (no illustration). */
  case object Base extends IllustratedMessageSize

  /** Extra small illustration size (since UI5 2.10.0). */
  case object ExtraSmall extends IllustratedMessageSize

  /** Small illustration size (since UI5 2.10.0). */
  case object Small extends IllustratedMessageSize

  /** Medium illustration size (since UI5 2.10.0). */
  case object Medium extends IllustratedMessageSize

  /** Large illustration size (since UI5 2.10.0). */
  case object Large extends IllustratedMessageSize

  /** Deprecated since UI5 2.10.0, use [[ExtraSmall]] instead. */
  case object Dot extends IllustratedMessageSize

  /** Deprecated since UI5 2.10.0, use [[Small]] instead. */
  case object Spot extends IllustratedMessageSize

  /** Deprecated since UI5 2.10.0, use [[Medium]] instead. */
  case object Dialog extends IllustratedMessageSize

  /** Deprecated since UI5 2.10.0, use [[Large]] instead. */
  case object Scene extends IllustratedMessageSize

  val allValues: List[IllustratedMessageSize] = deriveAllValues

  def valueOf(value: IllustratedMessageSize): String = value.toString

}
