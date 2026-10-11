package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, JSName}
import be.doeraene.webcomponents.WebComponent

/** The ui5-date component defines a calendar date to be used inside ui5-calendar. Its value must be formatted
  * according to the `valueFormat` property of the hosting calendar.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/CalendarDate/">the doc</a> for more information.
  */
object CalendarDate extends WebComponent with HasValue {

  @js.native
  trait RawElement extends js.Object {
    def value: String = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/CalendarDate.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-date")

  object slots {}

  object events {}

}
