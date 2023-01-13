package be.doeraene.webcomponents.ui5.configkeys

import com.raquo.laminar.codecs.Codec

object EmbeddingAsIsCodec {
  def apply[T, U <: T]: Codec[U, T] = new Codec[U, T] {
    def decode(domValue: T): U   = domValue.asInstanceOf[U]
    def encode(scalaValue: U): T = scalaValue
  }
}
