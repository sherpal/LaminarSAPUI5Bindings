package be.doeraene.webcomponents.ui5.configkeys

import com.raquo.laminar.codecs.Codec
import scala.deriving.Mirror

/** Defines the component design.
  */
sealed trait TagDesign

object TagDesign extends EnumerationString[TagDesign] {
  case object Set1        extends TagDesign
  case object Set2        extends TagDesign
  case object Neutral     extends TagDesign
  case object Information extends TagDesign
  case object Positive    extends TagDesign
  case object Negative    extends TagDesign
  case object Critical    extends TagDesign

  val allValues: List[TagDesign] = deriveAllValues

  def valueOf(value: TagDesign): String = value.toString

}
