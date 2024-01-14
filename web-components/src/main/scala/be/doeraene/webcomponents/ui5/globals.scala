package be.doeraene.webcomponents.ui5

import java.time.LocalDate

import com.raquo.laminar.codecs.Codec
import com.raquo.laminar.api.L.htmlAttr
import com.raquo.laminar.keys.HtmlAttr

import scala.concurrent.duration.FiniteDuration
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.concurrent.duration.DurationLong

/** Simple function that marks the argument as used, so that IJ does not complain. */
private[ui5] def used(any: Any): Unit = ()

@js.native
@JSImport("@ui5/webcomponents-icons/dist/AllIcons.js", JSImport.Namespace)
object AllIconsImport extends js.Object

@js.native
@JSImport("@ui5/webcomponents/dist/features/InputElementsFormSupport.js", JSImport.Namespace)
object InputElementsFormSupport extends js.Object

object FiniteDurationCodec extends Codec[FiniteDuration, String] {
  override def decode(domValue: String): FiniteDuration = domValue.toLong.millis

  override def encode(scalaValue: FiniteDuration): String = scalaValue.toMillis.toString
}

case class ListCodec[A](codec: Codec[A, String]) extends Codec[List[A], String] {
  def decode(domValue: String): List[A] = domValue.split(',').toList.map(codec.decode)

  def encode(scalaValue: List[A]): String = scalaValue.map(codec.encode).mkString(",")
}

@js.native
@JSImport("@ui5/webcomponents/dist/features/InputElementsFormSupport.js", JSImport.Default)
object SubmitsSupport extends js.Object

@js.native
@JSImport("@ui5/webcomponents-localization/dist/Assets.js", JSImport.Namespace)
object Localization extends js.Object

def htmlAttrWithSupport[V](name: String, codec: Codec[V, String])(support: => Any): HtmlAttr[V] = {
  support
  htmlAttr(name, codec)
}
