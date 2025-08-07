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
import be.doeraene.webcomponents.ui5.configkeys.TableSingleSelectBehaviour

object TableSelectionSingle extends WebComponent {

  @js.native
  trait RawElement extends js.Object:
    def selected: String = js.native

    def getSelectedRow: js.UndefOr[TableRow.Ref] = js.native

    def getRowByKey(key: String): js.UndefOr[TableRow.Ref] = js.native

  object RawElement:
    extension (elem: RawElement)
      def maybeSelectedRow: Option[TableRow.Ref]      = elem.getSelectedRow.toOption
      def rowByKey(key: String): Option[TableRow.Ref] = elem.getRowByKey(key).toOption
  @js.native
  @JSImport("@ui5/webcomponents/dist/TableSelectionSingle.js", JSImport.Default)
  object RawImport extends js.Object
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-table-selection-single")

  lazy val selected: HtmlAttr[String] = htmlAttr("selected", StringAsIsCodec)
  lazy val behaviour: HtmlAttr[TableSingleSelectBehaviour] =
    TableSingleSelectBehaviour.asHtmlAttr("behavior")

  object slots {}

  object events {
    val onChange: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("change")
  }

}
