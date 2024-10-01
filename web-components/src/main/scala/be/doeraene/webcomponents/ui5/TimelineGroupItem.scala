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

/** An entry posted on the timeline. It is intented to represent a group of <ui5-timeline-item>s.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/components/fiori/TimelineGroupItem/">the doc</a> for more
  *   information.
  */
object TimelineGroupItem extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def groupName: String = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/TimelineGroupItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-timeline-group-item")

  lazy val groupName: HtmlAttr[String] = htmlAttr("group-name", StringAsIsCodec)

  lazy val collapsed: HtmlAttr[Boolean] = htmlAttr("collapsed", BooleanAsAttrPresenceCodec)

  object slots {}

  object events {
    val onToggle: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("toggle")
  }

  def item = TimelineItem

}
