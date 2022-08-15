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

/** The Slider component represents a numerical range and a handle (grip). The purpose of the component is to enable
  * visual selection of a value in a continuous numerical range by moving an adjustable handle.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Slider/">the doc</a> for more information.
  */
object Slider {

  @js.native
  trait RawElement extends js.Object {
    def value: Double = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/Slider.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = Slider.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-slider")

  val id: ReactiveProp[String, String] = idAttr

  val accessibleName: ReactiveHtmlAttr[String] = customHtmlAttr("accessible-name", StringAsIsCodec)

  val disabled: ReactiveHtmlAttr[Boolean] = customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)

  val labelInterval: ReactiveHtmlAttr[Int] = customHtmlAttr("label-interval", IntAsStringCodec)

  val max: ReactiveHtmlAttr[Double] = customHtmlAttr("max", DoubleAsStringCodec)

  val min: ReactiveHtmlAttr[Double] = customHtmlAttr("min", DoubleAsStringCodec)

  val showTickmarks: ReactiveHtmlAttr[Boolean] = customHtmlAttr("show-tickmarks", BooleanAsAttrPresenceCodec)

  val showTooltip: ReactiveHtmlAttr[Boolean] = customHtmlAttr("show-tooltip", BooleanAsAttrPresenceCodec)

  val step: ReactiveHtmlAttr[Int] = customHtmlAttr("step", IntAsStringCodec)

  val value: ReactiveHtmlAttr[Double] = customHtmlAttr("value", DoubleAsStringCodec)

  object slots {}

  object events {
    val onChange: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("change")
    val onInput: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("input")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(Slider)): _*)

}