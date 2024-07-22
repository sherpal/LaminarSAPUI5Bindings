package be.doeraene.webcomponents.ui5.configkeys

import com.raquo.laminar.codecs.Codec
import scala.deriving.Mirror

/** Defines the component design.
  */
sealed trait BadgeDesign

object BadgeDesign extends EnumerationString[BadgeDesign] {
  case object Set1        extends BadgeDesign
  case object Set2        extends BadgeDesign
  case object Set3        extends BadgeDesign
  case object Neutral     extends BadgeDesign
  case object Information extends BadgeDesign
  case object Positive    extends BadgeDesign
  case object Negative    extends BadgeDesign
  case object Critical    extends BadgeDesign

  val allValues: List[BadgeDesign] = List(Set1, Set2, Set3, Neutral, Information, Positive, Negative, Critical)

  def valueOf(value: BadgeDesign): String = value.toString

}
