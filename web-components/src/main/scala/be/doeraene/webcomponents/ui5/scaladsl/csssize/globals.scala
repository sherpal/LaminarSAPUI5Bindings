package be.doeraene.webcomponents.ui5.scaladsl.csssize

import be.doeraene.webcomponents.ui5.datatypes.JSCSSSize
import com.raquo.laminar.codecs.Codec

opaque type CSSSize = String

object CSSSize {
  def fromString(value: String): Option[CSSSize] = Option.when(JSCSSSize.isValid(value))(value)

  def auto: CSSSize    = "auto"
  def inherit: CSSSize = "inherit"

  extension (cssSize: CSSSize) {
    def value: String = cssSize
  }

  object AsStringCodec extends Codec[CSSSize, String]:
    override def decode(domValue: String): CSSSize   = domValue
    override def encode(scalaValue: CSSSize): String = scalaValue
}

extension (numeric: Int) {
  def px: CSSSize  = s"${numeric}px"
  def em: CSSSize  = s"${numeric}em"
  def rem: CSSSize = s"${numeric}rem"
  def % : CSSSize  = s"${numeric}%"
}
