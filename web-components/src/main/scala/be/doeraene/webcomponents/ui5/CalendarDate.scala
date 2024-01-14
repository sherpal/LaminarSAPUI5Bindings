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

/** The ui5-date component defines a calendar date to be used inside ui5-calendar
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Calendar/">the doc</a> for more
  *   information.
  */
object CalendarDate extends WebComponent with HasValue {

  @js.native
  trait RawElement extends js.Object {
    def value: String = js.native
  }

  type Ref = dom.html.Element with RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-date")

  object slots {}

  object events {}

}
