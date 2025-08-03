package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.ButtonDesign
import be.doeraene.webcomponents.ui5.configkeys.ColourScheme
import be.doeraene.webcomponents.ui5.configkeys.IconName
import be.doeraene.webcomponents.ui5.configkeys.OnOff
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.DoubleAsIsCodec
import com.raquo.laminar.codecs.DoubleAsStringCodec
import com.raquo.laminar.codecs.IntAsStringCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.configkeys.TextOverflowMode

/** The ui5-expandable-text component allows displaying a large body of text in a small space. It provides an
  * "expand/collapse" functionality, which shows/hides potentially truncated text.
  */
object ExpandableText extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/ExpandableText.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-expandable-text")

  lazy val text: HtmlAttr[String]                   = htmlAttr("text", StringAsIsCodec)
  lazy val maxCharacters: HtmlAttr[Int]             = htmlAttr("max-characters", IntAsStringCodec)
  lazy val emptyIndicatorMode: HtmlAttr[OnOff]      = htmlAttr("empty-indicator-mode", OnOff.AsStringCodec)
  lazy val overflowMode: HtmlAttr[TextOverflowMode] = htmlAttr("overflow-mode", TextOverflowMode.AsStringCodec)

  object slots {}

  object events {}

}
