package be.doeraene.webcomponents.ui5.configkeys

import com.raquo.domtypes.generic.codecs.Codec

sealed trait ButtonDesign

object ButtonDesign extends EnumerationString[ButtonDesign] {

  case object Default extends ButtonDesign
  case object Emphasized extends ButtonDesign
  case object Positive extends ButtonDesign
  case object Negative extends ButtonDesign
  case object Transparent extends ButtonDesign
  case object Attention extends ButtonDesign

  val allValues: List[ButtonDesign] = List(
    Default,
    Emphasized,
    Positive,
    Negative,
    Transparent,
    Attention
  )

  def valueOf(value: ButtonDesign): String = value.toString

}
