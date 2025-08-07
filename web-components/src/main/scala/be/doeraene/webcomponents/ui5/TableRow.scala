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

import scala.concurrent.duration.FiniteDuration
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-table-row component represents a row in the ui5-table.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/components/TableRow/">the doc</a> for more information.
  */
object TableRow extends WebComponent {

  @js.native
  trait RawElement extends js.Object:
    def rowKey: String = js.native

  @js.native
  @JSImport("@ui5/webcomponents/dist/TableRow.js", JSImport.Default)
  object RawImport extends js.Object
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-table-row")

  lazy val rowKey: HtmlAttr[String]       = htmlAttr("row-key", StringAsIsCodec)
  lazy val position: HtmlAttr[Int]        = htmlAttr("position", IntAsStringCodec)
  lazy val interactive: HtmlAttr[Boolean] = htmlAttr("interactive", BooleanAsAttrPresenceCodec)
  lazy val navigated: HtmlAttr[Boolean]   = htmlAttr("navigated", BooleanAsAttrPresenceCodec)
  lazy val movable: HtmlAttr[Boolean]     = htmlAttr("movable", BooleanAsAttrPresenceCodec)

  object slots {
    val actions: Slot = Slot("actions")
  }

  object events {}

  def cell: TableCell.type = TableCell

  def action: TableRowAction.type                     = TableRowAction
  def actionNavigation: TableRowActionNavigation.type = TableRowActionNavigation

}
