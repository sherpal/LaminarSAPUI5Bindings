package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-date-range component defines a range of dates to be used inside ui5-calendar (e.g. in its `disabledDates`
  * slot). The values must be formatted according to the `valueFormat` property of the hosting calendar.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/CalendarDateRange/">the doc</a> for more information.
  */
object CalendarDateRange extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def startValue: String = js.native
    def endValue: String   = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/CalendarDateRange.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-date-range")

  /** Start of the date range, formatted according to the `valueFormat` property of the hosting calendar. */
  lazy val startValue: HtmlAttr[String] = htmlAttr("start-value", StringAsIsCodec)

  /** End of the date range, formatted according to the `valueFormat` property of the hosting calendar. */
  lazy val endValue: HtmlAttr[String] = htmlAttr("end-value", StringAsIsCodec)

  object slots {}

  object events {}

}
