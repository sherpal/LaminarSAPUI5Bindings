package be.doeraene.webcomponents.ui5.configkeys

import com.raquo.laminar.codecs.Codec

sealed trait ButtonBadgeDesign {
  def value: String = toString
}

object ButtonBadgeDesign extends EnumerationString[ButtonBadgeDesign] {

  case object InlineText   extends ButtonBadgeDesign
  case object OverlayText  extends ButtonBadgeDesign
  case object AttentionDot extends ButtonBadgeDesign

  val allValues: List[ButtonBadgeDesign] = deriveAllValues

  def valueOf(value: ButtonBadgeDesign): String = value.value

}
