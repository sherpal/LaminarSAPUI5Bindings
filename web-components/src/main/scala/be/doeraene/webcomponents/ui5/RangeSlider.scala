package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import com.raquo.laminar.codecs.DoubleAsStringCodec
import com.raquo.laminar.codecs.IntAsStringCodec
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.datatypes.Tickmark
import be.doeraene.webcomponents.WebComponent

/** Represents a numerical interval and two handles (grips) to select a sub-range within it. The purpose of the
  * component to enable visual selection of sub-ranges within a given interval.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/RangeSlider/">the doc</a> for more information.
  */
object RangeSlider extends WebComponent with HasName {

  @js.native
  trait RawElement extends js.Object {
    def endValue: Double   = js.native
    def startValue: Double = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/RangeSlider.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-range-slider")

  lazy val endValue: HtmlAttr[Double] = htmlAttr("end-value", DoubleAsStringCodec)

  lazy val startValue: HtmlAttr[Double] = htmlAttr("start-value", DoubleAsStringCodec)

  lazy val accessibleName: HtmlAttr[String] = htmlAttr("accessible-name", StringAsIsCodec)

  lazy val disabled: HtmlAttr[Boolean] = htmlAttr("disabled", BooleanAsAttrPresenceCodec)

  lazy val labelInterval: HtmlAttr[Int] = htmlAttr("label-interval", IntAsStringCodec)

  lazy val max: HtmlAttr[Double] = htmlAttr("max", DoubleAsStringCodec)

  lazy val min: HtmlAttr[Double] = htmlAttr("min", DoubleAsStringCodec)

  lazy val showTickmarks: HtmlAttr[Boolean] = htmlAttr("show-tickmarks", BooleanAsAttrPresenceCodec)

  lazy val showTooltip: HtmlAttr[Boolean] = htmlAttr("show-tooltip", BooleanAsAttrPresenceCodec)

  /** Whether input fields should be used as tooltips for the handles (requires `showTooltip`). */
  lazy val editableTooltip: HtmlAttr[Boolean] = htmlAttr("editable-tooltip", BooleanAsAttrPresenceCodec)

  lazy val step: HtmlAttr[Int] = htmlAttr("step", IntAsStringCodec)

  /** Custom tickmarks (with optional labels) displayed on the slider scale. They are purely visual and do not affect
    * the step or the possible values.
    */
  lazy val tickmarks: HtmlProp[List[Tickmark], js.Array[Tickmark]] = Tickmark.asHtmlProp

  object slots {}

  object events {
    val onChange: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("change")
    val onInput: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("input")
  }

}
