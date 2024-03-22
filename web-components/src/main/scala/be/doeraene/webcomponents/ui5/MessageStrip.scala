package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName, MessageStripDesign}
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

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-message-strip")

  lazy val design: HtmlAttr[MessageStripDesign] = htmlAttr("design", MessageStripDesign.AsStringCodec)

  lazy val hideCloseButton: HtmlAttr[Boolean] = htmlAttr("hide-close-button", BooleanAsAttrPresenceCodec)

  lazy val hideIcon: HtmlAttr[Boolean] = htmlAttr("hide-icon", BooleanAsAttrPresenceCodec)

  object slots {
    val icon: Slot = new Slot("icon")
  }

  object events {
    val onClose: EventProp[dom.Event] = new EventProp("close")
  }

}
