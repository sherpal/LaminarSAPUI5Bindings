package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import com.raquo.domtypes.generic.codecs.DoubleAsStringCodec
import com.raquo.domtypes.generic.codecs.IntAsStringCodec
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.WebComponent

/** Represents a numerical interval and two handles (grips) to select a sub-range within it. The purpose of the
  * component to enable visual selection of sub-ranges within a given interval.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/RangeSlider/">the doc</a> for more
  *   information.
  */
object RangeSlider extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def endValue: Double   = js.native
    def startValue: Double = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/RangeSlider.js", JSImport.Default)
  object RawImport extends WebComponent.WithMetadata

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-range-slider")

  lazy val endValue: ReactiveHtmlAttr[Double] = customHtmlAttr("end-value", DoubleAsStringCodec)

  lazy val startValue: ReactiveHtmlAttr[Double] = customHtmlAttr("start-value", DoubleAsStringCodec)

  lazy val accessibleName: ReactiveHtmlAttr[String] = customHtmlAttr("accessible-name", StringAsIsCodec)

  lazy val disabled: ReactiveHtmlAttr[Boolean] = customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)

  lazy val labelInterval: ReactiveHtmlAttr[Int] = customHtmlAttr("label-interval", IntAsStringCodec)

  lazy val max: ReactiveHtmlAttr[Double] = customHtmlAttr("max", DoubleAsStringCodec)

  lazy val min: ReactiveHtmlAttr[Double] = customHtmlAttr("min", DoubleAsStringCodec)

  lazy val showTickmarks: ReactiveHtmlAttr[Boolean] = customHtmlAttr("show-tickmarks", BooleanAsAttrPresenceCodec)

  lazy val showTooltip: ReactiveHtmlAttr[Boolean] = customHtmlAttr("show-tooltip", BooleanAsAttrPresenceCodec)

  lazy val step: ReactiveHtmlAttr[Int] = customHtmlAttr("step", IntAsStringCodec)

  object slots {}

  object events {
    val onChange: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("change")
    val onInput: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("input")
  }

  

}
