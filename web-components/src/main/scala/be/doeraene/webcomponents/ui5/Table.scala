package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{TableGrowingMode, TableMode}
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
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

  type Ref         = dom.html.Element with RawElement
  type ModFunction = Table.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-table")

  val busy: ReactiveHtmlAttr[Boolean]                = customHtmlAttr("busy", BooleanAsAttrPresenceCodec)
  val busyDelay: ReactiveHtmlAttr[FiniteDuration]    = customHtmlAttr("busy-delay", FiniteDurationCodec)
  val growing: ReactiveHtmlAttr[TableGrowingMode]    = customHtmlAttr("growing", TableGrowingMode.AsStringCodec)
  val growingButtonSubtext: ReactiveHtmlAttr[String] = customHtmlAttr("growing-button-subtext", StringAsIsCodec)
  val growingButtonText: ReactiveHtmlAttr[String]    = customHtmlAttr("growing-button-text", StringAsIsCodec)
  val hideNoData: ReactiveHtmlAttr[Boolean]          = customHtmlAttr("hide-no-data", BooleanAsAttrPresenceCodec)
  val mode: ReactiveHtmlAttr[TableMode]              = customHtmlAttr("mode", TableMode.AsStringCodec)
  val noDataText: ReactiveHtmlAttr[String]           = customHtmlAttr("no-data-text", StringAsIsCodec)
  val stickyColumnHeader: ReactiveHtmlAttr[Boolean] = customHtmlAttr("sticky-column-header", BooleanAsAttrPresenceCodec)

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

    val onSelectionChange = new EventProp[dom.Event & HasDetail[TableSelectionChangeDetail]]("selection-change")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(Table)): _*)

  def column: TableColumn.type = TableColumn
  def row: TableRow.type       = TableRow

  def groupRow(mods: Mod[ReactiveHtmlElement[dom.HTMLElement]]*): HtmlElement =
    customHtmlTag[dom.HTMLElement]("ui5-table-group-row")(mods: _*)

}
