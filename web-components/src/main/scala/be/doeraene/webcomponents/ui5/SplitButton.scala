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

/** ui5-split-button enables users to trigger actions. It is constructed of two separate actions - default action and
  * arrow action that can be activated by clicking or tapping, or by pressing certain keyboard keys - Space or Enter for
  * default action, and Arrow Down or Arrow Up for arrow action.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/SplitButton/">the doc</a> for more
  *   information.
  */
object SplitButton extends WebComponent with HasIcon {

  @js.native
  trait RawElement extends js.Object {
    def activeArrowButton: Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/SplitButton.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-split-button")

  lazy val accessibleName: HtmlAttr[String]     = htmlAttr("accessible-name", StringAsIsCodec)
  lazy val activeIcon: HtmlAttr[IconName]       = htmlAttr("active-icon", IconName.AsStringCodec)
  lazy val design: HtmlAttr[ButtonDesign]       = htmlAttr("design", ButtonDesign.AsStringCodec)
  lazy val disabled: HtmlAttr[Boolean]          = htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val activeArrowButton: HtmlAttr[Boolean] = htmlAttr("active-arrow-button", BooleanAsAttrPresenceCodec)

  object slots {}

  object events {
    val onArrowClick: EventProp[EventWithPreciseTarget[dom.HTMLElement]] = new EventProp("arrow-click")
    val onClick: EventProp[EventWithPreciseTarget[Ref]]                  = new EventProp("click")
  }

}
