package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.TableOverflowMode
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.eventtypes.MoveEventDetail
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.IntAsStringCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.concurrent.duration.FiniteDuration
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-table component provides a set of sophisticated features for displaying and dealing with vast amounts of
  * data in a responsive manner. To render the ui5-table, you need to define the columns and rows. You can use the
  * provided ui5-table-header-row and ui5-table-row components for this purpose.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/Table/">the doc</a> for more information.
  */
object Table extends WebComponent with HasAccessibleName {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/Table.js", JSImport.Default)
  object RawImport extends js.Object
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-table")

  lazy val noDataText: HtmlAttr[String]              = htmlAttr("no-data-text", StringAsIsCodec)
  lazy val overflowMode: HtmlAttr[TableOverflowMode] = TableOverflowMode.asHtmlAttr("overflow-mode")
  lazy val loading: HtmlAttr[Boolean]                = htmlAttr("loading", BooleanAsAttrPresenceCodec)
  lazy val loadingDelay: HtmlAttr[FiniteDuration]    = htmlAttr("loading-delay", FiniteDurationCodec)
  lazy val rowActionCount: HtmlAttr[Int]             = htmlAttr("row-action-count", IntAsStringCodec)

  /** Whether the table rows are displayed with alternating background colors (since UI5 2.17). */
  lazy val alternateRowColors: HtmlAttr[Boolean] = htmlAttr("alternate-row-colors", BooleanAsAttrPresenceCodec)

  object slots {
    val headerRow: Slot = Slot("headerRow")
    val noData: Slot    = Slot("noData")
    val features: Slot  = Slot("features")
  }

  object events {
    trait HasTableRow extends js.Object:
      def row: TableRow.Ref

    /** Detail of the `row-action-click` event.
      *
      * The `action` is either a [[TableRowAction]] or a [[TableRowActionNavigation]].
      */
    trait RowActionClickDetail extends HasTableRow:
      def action: dom.html.Element

    val onRowClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasTableRow]] = new EventProp("row-click")

    /** Fired when a movable row is moved over a potential drop target during a drag-and-drop operation.
      *
      * Call `preventDefault()` on the event to allow the element to be dropped.
      */
    val onMoveOver: EventProp[EventWithPreciseTarget[Ref] & HasDetail[MoveEventDetail[TableRow.Ref]]] =
      new EventProp("move-over")

    /** Fired when a movable row is dropped on a valid drop target. */
    val onMove: EventProp[EventWithPreciseTarget[Ref] & HasDetail[MoveEventDetail[TableRow.Ref]]] =
      new EventProp("move")

    val onRowActionClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[RowActionClickDetail]] = new EventProp(
      "row-action-click"
    )

  }

  def headerRow: TableHeaderRow.type = TableHeaderRow
  def row: TableRow.type             = TableRow
  def groupRow: TableGroupRow.type   = TableGroupRow
  def growing: TableGrowing.type     = TableGrowing

  def virtualizer: TableVirtualizer.type = TableVirtualizer

}
