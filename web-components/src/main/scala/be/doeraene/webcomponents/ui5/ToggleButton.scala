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
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.WebComponent

/** The ui5-toggle-button component is an enhanced ui5-button that can be toggled between pressed and normal states.
  * Users can use the ui5-toggle-button as a switch to turn a setting on or off. It can also be used to represent an
  * independent choice similar to a check box.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/ToggleButton/">the doc</a> for more
  *   information.
  */
object ToggleButton extends WebComponent with HasAccessibleName with HasIcon {

  @js.native
  trait RawElement extends js.Object {
    var accessibilityAttributes: js.Object = js.native

    def pressed: Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/ToggleButton.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-toggle-button")

  lazy val pressed: HtmlAttr[Boolean] = htmlAttr("pressed", BooleanAsAttrPresenceCodec)

  // This component has accessibilityAttributes but I currently don't know how to implement it

  lazy val design: HtmlAttr[ButtonDesign] = htmlAttr("design", ButtonDesign.AsStringCodec)

  lazy val disabled: HtmlAttr[Boolean] = htmlAttr("disabled", BooleanAsAttrPresenceCodec)

  lazy val iconEnd: HtmlAttr[Boolean] = htmlAttr("icon-end", BooleanAsAttrPresenceCodec)

  lazy val submits: HtmlAttr[Boolean] = {
    SubmitsSupport
    htmlAttr("submits", BooleanAsAttrPresenceCodec)
  }

  lazy val tooltip: HtmlAttr[String] = htmlAttr("tooltip", StringAsIsCodec)

  object slots {}

  object events {
    val onClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("click")
  }

}
