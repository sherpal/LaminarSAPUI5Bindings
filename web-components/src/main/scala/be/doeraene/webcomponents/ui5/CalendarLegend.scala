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

/** The ui5-calendar-legend component is designed for use within the ui5-calendar to display a legend. Each
  * ui5-calendar-legend-item represents a unique date type, specifying its visual style and a corresponding textual
  * label.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/v1/components/CalendarLegend/">the doc</a> for more information.
  */
object CalendarLegend extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/CalendarLegend.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-calendar-legend")

  lazy val hideToday: HtmlAttr[Boolean]         = htmlAttr("hide-today", BooleanAsAttrPresenceCodec)
  lazy val hideSelectedDay: HtmlAttr[Boolean]   = htmlAttr("hide-selected-day", BooleanAsAttrPresenceCodec)
  lazy val hideNonWorkingDay: HtmlAttr[Boolean] = htmlAttr("hide-non-working-day", BooleanAsAttrPresenceCodec)
  lazy val hideWorkingDay: HtmlAttr[Boolean]    = htmlAttr("hide-working-day", BooleanAsAttrPresenceCodec)

  object slots {}

  object events {}

  def item = CalendarLegendItem

}
