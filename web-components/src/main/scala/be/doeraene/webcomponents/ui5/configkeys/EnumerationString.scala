package be.doeraene.webcomponents.ui5.configkeys

import com.raquo.laminar.codecs.Codec
import scala.deriving.Mirror
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.api.L.*

trait EnumerationString[Value] {

  def valueOf(value: Value): String

  /* Helper method to derive all the values, in the simple (but ubiquitous) case where the Value type is an "enum" */
  protected def deriveAllValues(using mirror: Mirror.Of[Value])(using
      tupleValue: EnumerationString.TupleValueOf[mirror.MirroredElemTypes]
  )(using EnumerationString.AllSubtypesOf[mirror.MirroredElemTypes, Value]): List[Value] =
    tupleValue.toListOf[Value]

  val allValues: List[Value]

  lazy val valueFromString: PartialFunction[String, Value] = allValues.map(v => valueOf(v) -> v).toMap

  final def fromString(s: String): Option[Value] = valueFromString.lift(s)

  object AsStringCodec extends Codec[Value, String] {
    def encode(value: Value): String = valueOf(value)
    def decode(s: String): Value =
      fromString(s).getOrElse(throw new IllegalArgumentException(s"$s is not a valid value for $this"))
  }

  inline def asHtmlAttr(name: String): HtmlAttr[Value] = htmlAttr(name, AsStringCodec)

}

object EnumerationString {

  case class TupleValueOf[T <: Tuple](value: T) {
    def toListOf[Upper](using AllSubtypesOf[T, Upper]): List[Upper] =
      value.toArray.toList.map(_.asInstanceOf[Upper])
  }

  given TupleValueOf[EmptyTuple] = TupleValueOf(EmptyTuple)

  given [Head, Tail <: Tuple](using head: Mirror.Of[Head], tail: TupleValueOf[Tail])(using
      ev: head.type <:< Mirror.Singleton
  ): TupleValueOf[Head *: Tail] =
    TupleValueOf(ev(head).fromProduct(EmptyTuple).asInstanceOf[Head] *: tail.value)

  sealed trait AllSubtypesOf[T <: Tuple, Upper]

  object AllSubtypesOf {
    given [Upper]: AllSubtypesOf[EmptyTuple, Upper] = new AllSubtypesOf {}

    given [Head, Tail <: Tuple, Upper](using
        Head <:< Upper,
        AllSubtypesOf[Tail, Upper]
    ): AllSubtypesOf[Head *: Tail, Upper] =
      new AllSubtypesOf {}
  }

}
