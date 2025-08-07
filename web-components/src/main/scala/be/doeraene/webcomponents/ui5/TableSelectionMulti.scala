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

object TableSelectionMulti extends WebComponent {

  @js.native
  trait RawElement extends js.Object:
    @JSName("selected")
    def selectedJS: String = js.native

    @JSName("getSelectedRows")
    def getSelectedRowsJS: js.Array[TableRow.Ref] = js.native

    def setSelectedAsSet(set: js.Set[String]): Unit = js.native

    def getRowByKey(key: String): js.UndefOr[TableRow.Ref] = js.native

  object RawElement:
    extension (elem: RawElement)
      def selected: List[String]                        = selectedCodec.decode(elem.selectedJS)
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

  object slots {}

  object events {
    val onChange: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("change")
  }

  private lazy val selectedCodec = ListCodec(StringAsIsCodec, ' ')

}
