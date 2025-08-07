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
