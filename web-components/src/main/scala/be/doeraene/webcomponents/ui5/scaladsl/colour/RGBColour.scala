package be.doeraene.webcomponents.ui5.scaladsl.colour

import scala.language.implicitConversions
import scala.util.Random

final case class RGBColour(red: Int, green: Int, blue: Int) extends Colour {
  def alpha: Double                        = 1
  def withAlpha(alpha: Double): RGBAColour = RGBAColour(red, green, blue, alpha)
  def withoutAlpha: RGBColour              = this
  def asRGBAColour: RGBAColour             = withAlpha(1.0)
}

object RGBColour {

  implicit def asRGBA(rgb: RGBColour): RGBAColour = rgb.withAlpha(1.0)

}
