package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName, TimelineLayout}
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
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

/** An entry posted on the timeline.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Timeline/">the doc</a> for more
  *   information.
  */
object TimelineItem extends WebComponent with HasIcon with HasName {

  @js.native
  trait RawElement extends js.Object {
    def name: String = js.native

    def nameClickable: Boolean = js.native

    def subtitleText: String = js.native

    def titleText: String = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/TimelineItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-timeline-item")

  lazy val nameClickable: HtmlAttr[Boolean] = htmlAttr("name-clickable", BooleanAsAttrPresenceCodec)

  lazy val subtitleText: HtmlAttr[String] = htmlAttr("subtitle-text", StringAsIsCodec)

  lazy val titleText: HtmlAttr[String] = htmlAttr("title-text", StringAsIsCodec)

  object slots {}

  object events {
    val onNameClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("name-click")
  }

}
