package be.doeraene.webcomponents.ui5.configkeys

import scala.scalajs.js

@js.native
sealed trait IconDesign extends js.Any

object IconDesign extends EnumerationString[IconDesign] {

  private def _iconDesign(value: String): IconDesign = value.asInstanceOf[IconDesign]

  val Contrast: IconDesign       = _iconDesign("Contrast")
  val Critical: IconDesign       = _iconDesign("Critical")
  val Default: IconDesign        = _iconDesign("Default")
  val Information: IconDesign    = _iconDesign("Information")
  val Negative: IconDesign       = _iconDesign("Negative")
  val Neutral: IconDesign        = _iconDesign("Neutral")
  val NonInteractive: IconDesign = _iconDesign("NonInteractive")
  val Positive: IconDesign       = _iconDesign("Positive")

  val allValues: List[IconDesign] = List(
    Contrast,
    Critical,
    Default,
    Information,
    Negative,
    Neutral,
    NonInteractive,
    Positive
  )

  def valueOf(value: IconDesign): String = value.asInstanceOf[String]

}
