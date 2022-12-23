package be.doeraene.webcomponents

import java.time.LocalDate

import com.raquo.domtypes.generic.codecs.Codec

import scala.concurrent.duration.FiniteDuration
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.concurrent.duration.DurationLong

package object ui5 {

  /** Simple function that marks the argument as used, so that IJ does not complain. */
  private[ui5] def used(any: Any): Unit = ()

  @js.native
  @JSImport("@ui5/webcomponents-icons/dist/AllIcons.js", JSImport.Default)
  object RawImport extends js.Object

  used(RawImport)

  @js.native
  @JSImport("@ui5/webcomponents/dist/features/InputElementsFormSupport.js", JSImport.Default)
  object InputElementsFormSupport extends js.Object
  
  object FiniteDurationCodec extends Codec[FiniteDuration, String] {
    override def decode(domValue: String): FiniteDuration = domValue.toLong.millis

    override def encode(scalaValue: FiniteDuration): String = scalaValue.toMillis.toString
  }

  object LocalDateCodec extends Codec[LocalDate, String] {
    private val formatter = java.time.format.DateTimeFormatter.ISO_LOCAL_DATE

    override def decode(domValue: String): LocalDate = LocalDate.parse(domValue, formatter)

    override def encode(scalaValue: LocalDate): String = scalaValue.format(formatter)
  }

  case class ListCodec[A](codec: Codec[A, String]) extends Codec[List[A], String] {
    def decode(domValue: String): List[A] = domValue.split(',').toList.map(codec.decode)

    def encode(scalaValue: List[A]): String = scalaValue.map(codec.encode).mkString(",")
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/features/InputElementsFormSupport.js", JSImport.Default)
  object SubmitsSupport extends js.Object

}
