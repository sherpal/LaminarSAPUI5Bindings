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
import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.TagDesign
import be.doeraene.webcomponents.ui5.configkeys.WrappingType
import be.doeraene.webcomponents.ui5.configkeys.TagSize
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget

/** The ui5-tag is a component which serves the purpose to attract the user attention to some piece of information
  * (state, quantity, condition, etc.). It can contain icon and text information, and its design can be chosen from
  * specific design types.
  */
object Tag extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/Tag.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-tag")

  lazy val colourScheme: HtmlAttr[ColourScheme] = ColourScheme.asHtmlAttr("color-scheme")
  lazy val design: HtmlAttr[TagDesign]          = TagDesign.asHtmlAttr("design")
  lazy val hideStateIcon: HtmlAttr[Boolean]     = htmlAttr("hide-state-icon", BooleanAsAttrPresenceCodec)
  lazy val interactive: HtmlAttr[Boolean]       = htmlAttr("interactive", BooleanAsAttrPresenceCodec)
  lazy val wrappingType: HtmlAttr[WrappingType] = WrappingType.asHtmlAttr("wrapping-type")
  lazy val size: HtmlAttr[TagSize]              = TagSize.asHtmlAttr("size")

  object slots {
    // note that unlike most elements that have an attribute Icon, this element has a slot icon instead.
    // most of the time you will want to use a ui5-icon for this slot.
    val icon: Slot = new Slot("icon")
  }

  object events {
    val onClick = new EventProp[EventWithPreciseTarget[Ref]]("click")
  }

}
