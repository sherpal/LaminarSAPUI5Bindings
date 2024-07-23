package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{CalendarSelectionMode, CalendarType}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, JSName}
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.CalendarLegendItemType

object CalendarLegendItem extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/CalendarLegendItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-calendar-legend-item")

  lazy val text: HtmlAttr[String] = htmlAttr("text", StringAsIsCodec)

  lazy val tpe: HtmlAttr[CalendarLegendItemType] = htmlAttr("type", CalendarLegendItemType.AsStringCodec)

  object slots {}

  object events {}

  def types = CalendarLegendItemType

}
