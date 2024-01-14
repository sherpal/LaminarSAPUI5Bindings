package be.doeraene.webcomponents.ui5.eventtypes

import scala.scalajs.js
import be.doeraene.webcomponents.ui5.scaladsl.colour.Colour

trait HasColor extends js.Object {
  def color: String
}

object HasColor {
  extension (hasColor: HasColor) {
    def scalaColour: Colour = Colour.fromString(hasColor.color)
  }
}
