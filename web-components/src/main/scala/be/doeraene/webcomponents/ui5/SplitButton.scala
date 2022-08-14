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
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget

/** ui5-split-button enables users to trigger actions. It is constructed of two separate actions - default action and
  * arrow action that can be activated by clicking or tapping, or by pressing certain keyboard keys - Space or Enter for
  * default action, and Arrow Down or Arrow Up for arrow action.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/SplitButton/">the doc</a> for more
  *   information.
  */
object SplitButton extends HasIcon {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/SplitButton.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = SplitButton.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-split-button")

  val id: ReactiveProp[String, String] = idAttr

  val accessibleName: ReactiveHtmlAttr[String] = customHtmlAttr("accessible-name", StringAsIsCodec)

  val activeIcon: ReactiveHtmlAttr[IconName] = customHtmlAttr("active-icon", IconName.AsStringCodec)

  val design: ReactiveHtmlAttr[ButtonDesign] = customHtmlAttr("design", ButtonDesign.AsStringCodec)

  val disabled: ReactiveHtmlAttr[Boolean] = customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)

  object slots {}

  object events {
    val onArrowClick: EventProp[EventWithPreciseTarget[dom.HTMLElement]] = new EventProp("arrow-click")
    val onClick: EventProp[EventWithPreciseTarget[Ref]]                  = new EventProp("click")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(SplitButton)): _*)

}
