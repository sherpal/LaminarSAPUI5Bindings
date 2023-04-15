package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{TableGrowingMode, TableMode}
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.compiletime.ops.int.<=

import scala.concurrent.duration.FiniteDuration
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

object Table extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/Table.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = htmlTag("ui5-table")

  lazy val busy: HtmlAttr[Boolean]                = htmlAttr("busy", BooleanAsAttrPresenceCodec)
  lazy val busyDelay: HtmlAttr[FiniteDuration]    = htmlAttr("busy-delay", FiniteDurationCodec)
  lazy val growing: HtmlAttr[TableGrowingMode]    = htmlAttr("growing", TableGrowingMode.AsStringCodec)
  lazy val growingButtonSubtext: HtmlAttr[String] = htmlAttr("growing-button-subtext", StringAsIsCodec)
  lazy val growingButtonText: HtmlAttr[String]    = htmlAttr("growing-button-text", StringAsIsCodec)
  lazy val hideNoData: HtmlAttr[Boolean]          = htmlAttr("hide-no-data", BooleanAsAttrPresenceCodec)
  lazy val mode: HtmlAttr[TableMode]              = htmlAttr("mode", TableMode.AsStringCodec)
  lazy val noDataText: HtmlAttr[String]           = htmlAttr("no-data-text", StringAsIsCodec)
  lazy val stickyColumnHeader: HtmlAttr[Boolean] =
    htmlAttr("sticky-column-header", BooleanAsAttrPresenceCodec)

  object slots {
    val columns: Slot = new Slot("columns")
  }

  object events {
    val onLoadMore = new EventProp[dom.Event]("load-more")

    @js.native
    trait TableSelectionChangeDetail extends js.Object {
      def previouslySelectedRows: js.Array[TableRow.Ref] = js.native

      def selectedRows: js.Array[TableRow.Ref] = js.native
    }

    val onSelectionChange = new EventProp[dom.Event with HasDetail[TableSelectionChangeDetail]]("selection-change")
  }

  def column: TableColumn.type = TableColumn
  def row: TableRow.type       = TableRow

  def groupRow(mods: Mod[ReactiveHtmlElement[dom.HTMLElement]]*): HtmlElement =
    htmlTag[dom.HTMLElement]("ui5-table-group-row")(mods: _*)

}
