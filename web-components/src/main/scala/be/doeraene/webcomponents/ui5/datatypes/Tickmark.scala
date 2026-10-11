package be.doeraene.webcomponents.ui5.datatypes

import com.raquo.laminar.api.L
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.Codec

import scala.scalajs.js
import scala.scalajs.js.JSConverters.*

/** A custom tickmark for the `ui5-slider` and `ui5-range-slider` components (see their `tickmarks` property).
  *
  * @param value
  *   position of the tickmark. It must be between the `min` and `max` of the slider.
  * @param label
  *   optional label displayed below the tickmark.
  */
trait Tickmark extends js.Object {
  val value: Double
  val label: js.UndefOr[String]
}

object Tickmark {

  def apply(value: Double, label: js.UndefOr[String] = js.undefined): Tickmark = {
    val _value = value
    val _label = label
    new Tickmark {
      val value: Double             = _value
      val label: js.UndefOr[String] = _label
    }
  }

  /** `tickmarks` is a property (not an attribute), taking a JS array of [[Tickmark]]s. */
  def asHtmlProp: HtmlProp[List[Tickmark], js.Array[Tickmark]] =
    L.htmlProp(
      "tickmarks",
      new Codec[List[Tickmark], js.Array[Tickmark]] {
        def decode(domValue: js.Array[Tickmark]): List[Tickmark] = domValue.toList
        def encode(scalaValue: List[Tickmark]): js.Array[Tickmark] = scalaValue.toJSArray
      }
    )

}
