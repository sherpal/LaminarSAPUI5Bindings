package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName, MessageStripDesign}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** The ui5-message-strip component enables the embedding of app-related messages. It displays 4 designs of messages,
  * each with corresponding semantic color and icon: Information, Positive, Warning and Negative. Each message can have
  * a Close button, so that it can be removed from the UI, if needed.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/MessageStrip/">the doc</a> for more
  *   information.
  */
object MessageStrip extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/MessageStrip.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = MessageStrip.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-message-strip")

  val design: ReactiveHtmlAttr[MessageStripDesign] = customHtmlAttr("design", MessageStripDesign.AsStringCodec)

  val hideCloseButton: ReactiveHtmlAttr[Boolean] = customHtmlAttr("hide-close-button", BooleanAsAttrPresenceCodec)

  val hideIcon: ReactiveHtmlAttr[Boolean] = customHtmlAttr("hide-icon", BooleanAsAttrPresenceCodec)

  object slots {
    val icon: Slot = new Slot("icon")
  }

  object events {
    val onClose: EventProp[dom.Event] = new EventProp("close")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(MessageStrip)): _*)

}
