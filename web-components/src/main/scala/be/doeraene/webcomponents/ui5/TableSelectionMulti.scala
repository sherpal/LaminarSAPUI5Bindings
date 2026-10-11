package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.IntAsStringCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.annotation.JSName
import scala.scalajs.js.JSConverters.*
import be.doeraene.webcomponents.ui5.configkeys.TableSelectionMultiHeaderSelector
import be.doeraene.webcomponents.ui5.configkeys.TableSingleSelectBehaviour

/** The ui5-table-selection-multi component is used inside the ui5-table (in its `features` slot) to add multi-selection
  * capabilities to the table.
  *
  * The `rowKey` attribute of the rows must be set for the selection to work properly.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/TableSelectionMulti/">the doc</a> for more information.
  */
object TableSelectionMulti extends WebComponent {

  @js.native
  trait RawElement extends js.Object:
    @JSName("selected")
    def selectedJS: String = js.native

    @JSName("getSelectedRows")
    def getSelectedRowsJS: js.Array[TableRow.Ref] = js.native

    def setSelectedAsSet(set: js.Set[String]): Unit = js.native

    def getSelectedAsSet(): js.Set[String] = js.native

    def getRowByKey(key: String): js.UndefOr[TableRow.Ref] = js.native

  object RawElement:
    extension (elem: RawElement)
      def selected: List[String] = (elem.selectedJS: Any) match {
        case value: String => selectedCodec.decode(value).filter(_.nonEmpty)
        case _             => Nil // `selected` is undefined when nothing was ever selected
      }
      def selectedRows: List[TableRow.Ref]              = elem.getSelectedRowsJS.toList
      def setSelection(rowKeys: Iterable[String]): Unit = elem.setSelectedAsSet(rowKeys.toSet.toJSSet)
      def rowByKey(key: String): Option[TableRow.Ref]   = elem.getRowByKey(key).toOption

  @js.native
  @JSImport("@ui5/webcomponents/dist/TableSelectionMulti.js", JSImport.Default)
  object RawImport extends js.Object
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-table-selection-multi")

  lazy val selected: HtmlAttr[List[String]] = htmlAttr("selected", selectedCodec)

  /** Selection behavior: either with a dedicated selection column (`RowSelector`, default), or by clicking on rows
    * (`RowOnly`).
    */
  lazy val behavior: HtmlAttr[TableSingleSelectBehaviour] = TableSingleSelectBehaviour.asHtmlAttr("behavior")

  /** Selector rendered in the header row (since UI5 2.12). */
  lazy val headerSelector: HtmlAttr[TableSelectionMultiHeaderSelector] =
    TableSelectionMultiHeaderSelector.asHtmlAttr("header-selector")

  object slots {}

  object events {
    val onChange: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("change")
  }

  private lazy val selectedCodec = ListCodec(StringAsIsCodec, ' ')

}
