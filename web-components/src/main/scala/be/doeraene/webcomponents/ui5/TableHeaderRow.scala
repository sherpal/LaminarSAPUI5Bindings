package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.SortIndicator
import be.doeraene.webcomponents.ui5.configkeys.TableCellHorizontalAlign
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-table-header-row component represents the table headers of a ui5-table. It is tightly coupled to the
  * ui5-table and should therefore be used in the ui5-table only. The header row is placed in the `headerRow` slot of
  * the table.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/TableHeaderRow/">the doc</a> for more information.
  */
object TableHeaderRow extends WebComponent {

  @js.native
  trait RawElement extends js.Object

  @js.native
  @JSImport("@ui5/webcomponents/dist/TableHeaderRow.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-table-header-row")

  lazy val sticky: HtmlAttr[Boolean] = htmlAttr("sticky", BooleanAsAttrPresenceCodec)

  object slots {}

  object events {}

  def cell: TableHeaderCell.type = TableHeaderCell

}
