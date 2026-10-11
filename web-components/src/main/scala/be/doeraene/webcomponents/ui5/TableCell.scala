package be.doeraene.webcomponents.ui5

import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.TableCellHorizontalAlign

/** The ui5-table-cell represents a cell inside of a ui5-table-row.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/TableCell/">the doc</a> for more information.
  */
object TableCell extends WebComponent {

  @js.native
  trait RawElement extends js.Object

  @js.native
  @JSImport("@ui5/webcomponents/dist/TableCell.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-table-cell")

  lazy val horizontalAlign: HtmlAttr[TableCellHorizontalAlign] = TableCellHorizontalAlign.asHtmlAttr("horizontal-align")

  /** Whether the cell is visually merged with the cell directly above it (since UI5 2.21).
    *
    * Useful when consecutive cells in a column have the same value. Merging is only visual: the cell must still
    * contain its value (for accessibility).
    */
  lazy val merged: HtmlAttr[Boolean] = htmlAttr("merged", BooleanAsAttrPresenceCodec)

  object slots {}

  object events {}

}
