package be.doeraene.webcomponents.ui5.configkeys

import com.raquo.laminar.codecs.Codec
import scala.deriving.Mirror

trait EnumerationString[Value] {

  def valueOf(value: Value): String

  val allValues: List[Value]

  lazy val valueFromString: PartialFunction[String, Value] = allValues.map(v => valueOf(v) -> v).toMap

  final def fromString(s: String): Option[Value] = valueFromString.lift(s)

  object AsStringCodec extends Codec[Value, String] {
    def encode(value: Value): String = valueOf(value)
    def decode(s: String): Value =
      fromString(s).getOrElse(throw new IllegalArgumentException(s"$s is not a valid value for $this"))
  }

}
