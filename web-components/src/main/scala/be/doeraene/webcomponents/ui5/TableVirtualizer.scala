package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.IntAsStringCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-table-virtualizer component is used inside the ui5-table (in its `features` slot) to virtualize the table
  * rows, when the `overflowMode` of the table is `Scroll`. It only renders the rows that are visible in the viewport
  * and updates them on scroll.
  *
  * You are responsible for rendering the rows of the current range (see the `range-change` event), and for setting the
  * `position` attribute of each [[TableRow]]. The table needs a fixed height.
  *
  * Note: this component is experimental in UI5 and its API may change.
  *
  * Since UI5 2.5.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/TableVirtualizer/">the doc</a> for more information.
  */
object TableVirtualizer extends WebComponent {

  @js.native
  trait RawElement extends js.Object {

    /** Resets the virtualizer to its initial state and triggers the `range-change` event. */
    def reset(): Unit = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/TableVirtualizer.js", JSImport.Default)
  object RawImport extends js.Object
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-table-virtualizer")

  /** Height of the rows in the table, in pixels. Mandatory for virtualization to work properly. */
  lazy val rowHeight: HtmlAttr[Int] = htmlAttr("row-height", IntAsStringCodec)

  /** Total count of rows in the table. Mandatory for virtualization to work properly. */
  lazy val rowCount: HtmlAttr[Int] = htmlAttr("row-count", IntAsStringCodec)

  /** Count of extra rows to be rendered at the top and bottom of the table (experimental). */
  lazy val extraRows: HtmlAttr[Int] = htmlAttr("extra-rows", IntAsStringCodec)

  object slots {}

  object events {

    /** Detail of the `range-change` event: 0-based indices of the first and last rows to render. */
    trait RangeChangeDetail extends js.Object {
      def first: Int
      def last: Int
    }

    /** Fired when the range of rows to render changes (e.g. on scrolling). */
    val onRangeChange: EventProp[EventWithPreciseTarget[Ref] & HasDetail[RangeChangeDetail]] =
      new EventProp("range-change")
  }

}
