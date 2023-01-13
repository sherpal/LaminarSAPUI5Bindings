package be.doeraene.webcomponents.ui5.scaladsl.colour

import com.raquo.laminar.codecs.Codec
import org.scalajs.dom

/** Facility class to working with colours.
  *
  * Its most interesting methods are `rgb` and `rgba` which produces valid CSS strings for representing the colour.
  *
  * There are two concrete implementations of this trait: [[RGBAColour]] and [[RGBColour]], although you should not
  * really use them directly. In the future, there might be a [[HSLColour]] and [[HSLAColour]].
  */
trait Colour {

  def red: Int
  def green: Int
  def blue: Int
  def alpha: Double

  /** Adds, or change alpha level for this [[Colour]]. */
  def withAlpha(newAlpha: Double): RGBAColour

  /** Removes the alpha level for this [[Colour]]. This is semantically equivalent to `withAlpha(1.0)`.
    */
  def withoutAlpha: RGBColour

  /** Int representation of this [[Colour]], from 0 to 65535 */
  def intColour: Int = (red << 16) + (green << 8) + blue

  /** CSS String representation of this [[Colour]], in the form `rgb(r, g, b)` */
  def rgb: String = s"rgb($red, $green, $blue)"

  /** CSS String representation of this [[Colour]], in the form `rgba(r, g, b, a)` */
  def rgba: String = s"rgba($red, $green, $blue,$alpha)"

  /** Luma value corresponding to this [[Colour]]. */
  def luma: Int = (0.2126 * red + 0.7152 * green + 0.0722 * blue).toInt

  /** Returns whether this colour is "bright", by applying a cut-off on the luma at 128. */
  def isBright: Boolean = luma > 128

  /** Returns black or white, depending on what text colour would be better suited for writing on this [[Colour]]. */
  def matchingTextColour: Colour = if (isBright) Colour.black else Colour.white

  /** Semantic alias for `withAlpha(1.0)`. */
  def asRGBAColour: RGBAColour

}

object Colour {

  def apply(red: Int, green: Int, blue: Int, alpha: Double): Colour =
    RGBAColour(red, green, blue, alpha)

  def apply(red: Int, green: Int, blue: Int): Colour = RGBColour(red, green, blue)

  /** Transforms an [[Int]] value in the [0, 65535] range into its corresponding [[Colour]]. */
  def fromIntColour(colour: Int): Colour = apply(
    colour >> 16,
    (colour % (256 << 8)) / 256,
    colour % 256
  )

  /** Turns a valid CSS string into its corresponding [[Colour]].
    *
    * Behaviour for invalid strings is undefined.
    */
  def fromString(str: String)(using cache: FromStringColourCache): Colour = cache.fromString(str)

  // some predefined colours
  val black: Colour        = fromIntColour(0)
  val white: Colour        = fromIntColour(0xffffff)
  val red: Colour          = fromIntColour(0xff0000)
  val green: Colour        = fromIntColour(0x00ff00)
  val blue: Colour         = fromIntColour(0x0000ff)
  val yellow: Colour       = fromIntColour(0xffff00)
  val fuchsia: Colour      = fromIntColour(0xff00ff)
  val aqua: Colour         = fromIntColour(0x00ffff)
  val gray: Colour         = fromIntColour(0xc0c0c0)
  val orange: Colour       = fromIntColour(0xff9900)
  val brown: Colour        = fromIntColour(0x996633)
  val lightGreen: Colour   = fromIntColour(0x00cc99)
  val electricBlue: Colour = fromIntColour(0x6666ff)

  val someColours: Vector[Colour] = Vector(
    red,
    green,
    blue,
    yellow,
    fuchsia,
    aqua,
    orange,
    brown,
    lightGreen,
    electricBlue
  )

  /** Creates an infinite [[LazyList]] of rotating colours. */
  def repeatedColours: LazyList[Colour] = LazyList.continually(someColours).flatten

  object AsStringCodec extends Codec[Colour, String] {
    override def encode(colour: Colour): String = colour match {
      case rgba: RGBAColour => rgba.rgba
      case rgb: RGBColour   => rgb.rgb
    }
    override def decode(string: String): Colour = fromString(string)
  }

}
