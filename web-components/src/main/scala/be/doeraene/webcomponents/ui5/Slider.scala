package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, DoubleAsIsCodec, DoubleAsStringCodec, IntAsStringCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.WebComponent

/** The Slider component represents a numerical range and a handle (grip). The purpose of the component is to enable
  * visual selection of a value in a continuous numerical range by moving an adjustable handle.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Slider/">the doc</a> for more information.
  */
object Slider extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def value: Double = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/Slider.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-slider")

  lazy val accessibleName: HtmlAttr[String] = htmlAttr("accessible-name", StringAsIsCodec)

  lazy val disabled: HtmlAttr[Boolean] = htmlAttr("disabled", BooleanAsAttrPresenceCodec)

  lazy val labelInterval: HtmlAttr[Int] = htmlAttr("label-interval", IntAsStringCodec)

  lazy val max: HtmlAttr[Double] = htmlAttr("max", DoubleAsStringCodec)

  lazy val min: HtmlAttr[Double] = htmlAttr("min", DoubleAsStringCodec)

  lazy val showTickmarks: HtmlAttr[Boolean] = htmlAttr("show-tickmarks", BooleanAsAttrPresenceCodec)

  lazy val showTooltip: HtmlAttr[Boolean] = htmlAttr("show-tooltip", BooleanAsAttrPresenceCodec)

  lazy val step: HtmlAttr[Double] = htmlAttr("step", DoubleAsStringCodec)

  lazy val value: HtmlAttr[Double] = htmlAttr("value", DoubleAsStringCodec)

  object slots {}

  object events {
    val onChange: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("change")
    val onInput: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("input")
  }

}
