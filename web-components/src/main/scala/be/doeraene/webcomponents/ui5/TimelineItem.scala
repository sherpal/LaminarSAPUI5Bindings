package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName, TimelineLayout}
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** An entry posted on the timeline.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Timeline/">the doc</a> for more
  *   information.
  */
object TimelineItem extends HasIcon with HasName {

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

  type Ref         = dom.html.Element with RawElement
  type ModFunction = TimelineItem.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-timeline-item")

  val id: ReactiveProp[String, String] = idAttr

  val nameClickable: ReactiveHtmlAttr[Boolean] = customHtmlAttr("name-clickable", BooleanAsAttrPresenceCodec)

  val subtitleText: ReactiveHtmlAttr[String] = customHtmlAttr("subtitle-text", StringAsIsCodec)

  val titleText: ReactiveHtmlAttr[String] = customHtmlAttr("title-text", StringAsIsCodec)

  object slots {}

  object events {
    val onNameClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("name-click")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(TimelineItem)): _*)

}
