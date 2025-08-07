package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.TableOverflowMode
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
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
  *   <a href="https://sap.github.io/ui5-webcomponents/components/Table/">the doc</a> for more information.
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

  object slots {
    val headerRow: Slot = Slot("headerRow")
    val noData: Slot    = Slot("noData")
    val features: Slot  = Slot("features")
  }

  object events {
    trait HasTableRow extends js.Object:
      def row: TableRow.Ref

    val onRowClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasTableRow]] = new EventProp("row-click")
    val onMoveOver: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasTableRow]] = new EventProp("move-over")
    val onMove: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasTableRow]]     = new EventProp("move")
    val onRowActionClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasTableRow]] = new EventProp(
      "row-action-click"
    )

  }

  def headerRow: TableHeaderRow.type = TableHeaderRow
  def row: TableRow.type             = TableRow
  def growing: TableGrowing.type     = TableGrowing

}
