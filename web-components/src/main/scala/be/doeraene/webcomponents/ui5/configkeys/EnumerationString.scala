package be.doeraene.webcomponents.ui5.configkeys

import com.raquo.laminar.codecs.Codec
import scala.deriving.Mirror
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.api.L.*

import scala.scalajs.js
import scala.scalajs.js.JSConverters.*

trait EnumerationString[Value] {

  def valueOf(value: Value): String

  /* Helper method to derive all the values, in the simple (but ubiquitous) case where the Value type is an "enum" */
  protected def deriveAllValues(using mirror: Mirror.Of[Value])(using
      tupleValue: EnumerationString.TupleValueOf[mirror.MirroredElemTypes]
  )(using EnumerationString.AllSubtypesOf[mirror.MirroredElemTypes, Value] =:= true): List[Value] =
    tupleValue.toListOf[Value]

  val allValues: List[Value]

  lazy val valueFromString: js.Map[String, Value] =
    js.Map(allValues.map(v => valueOf(v) -> v)*)

  final def fromString(s: String): Option[Value] = valueFromString.get(s)

  object AsStringCodec extends Codec[Value, String] {
    def encode(value: Value): String = valueOf(value)
    def decode(s: String): Value =
      fromString(s).getOrElse(throw new IllegalArgumentException(s"$s is not a valid value for $this"))
  }

  inline def asHtmlAttr(name: String): HtmlAttr[Value] = htmlAttr(name, AsStringCodec)

}

object EnumerationString {

  case class TupleValueOf[T <: Tuple](value: T) {
    def toListOf[Upper](using AllSubtypesOf[T, Upper] =:= true): List[Upper] =
      value.toArray.toList.map(_.asInstanceOf[Upper])
  }

  given TupleValueOf[EmptyTuple] = TupleValueOf(EmptyTuple)

  given [Head, Tail <: Tuple](using head: Mirror.Of[Head], tail: TupleValueOf[Tail])(using
      ev: head.type <:< (Mirror.Singleton { type MirroredMonoType = Head })
  ): TupleValueOf[Head *: Tail] =
    TupleValueOf(ev(head).fromProduct(EmptyTuple) *: tail.value)

  type AllSubtypesOf[T <: Tuple, Upper] = ForAll[T, [t] =>> IsSubtype[t, Upper]]

  type IsSubtype[T, U] <: Boolean = T match {
    case U => true
    case _ => false
  }

  type ForAll[T <: Tuple, Predicate <: ([T] =>> Boolean)] <: Boolean = T match {
    case EmptyTuple => true
    case head *: rest =>
      Predicate[head] match {
        case true  => ForAll[rest, Predicate]
        case false => false
      }
  }

}
