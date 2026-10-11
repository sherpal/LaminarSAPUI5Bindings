package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.SortIndicator
import be.doeraene.webcomponents.ui5.configkeys.TableCellHorizontalAlign
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.IntAsStringCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-table-header-cell component represents a column in the ui5-table.
  *
  * As it is tightly coupled to the ui5-table, it should only be used within a ui5-table-header-row.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/TableHeaderCell/">the doc</a> for more information.
  */
object TableHeaderCell extends WebComponent {

  @js.native
  trait RawElement extends js.Object

  @js.native
  @JSImport("@ui5/webcomponents/dist/TableHeaderCell.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-table-header-cell")

  lazy val importance: HtmlAttr[Int]              = htmlAttr("importance", IntAsStringCodec)
  lazy val popinText: HtmlAttr[String]            = htmlAttr("popin-text", StringAsIsCodec)
  lazy val sortIndicator: HtmlAttr[SortIndicator] = SortIndicator.asHtmlAttr("sort-indicator")
  lazy val popinHidden: HtmlAttr[Boolean]         = htmlAttr("popin-hidden", BooleanAsAttrPresenceCodec)
  lazy val horizontalAlign: HtmlAttr[TableCellHorizontalAlign] =
    TableCellHorizontalAlign.asHtmlAttr("horizontal-align")

  /** Width of the column, as a CSS size (e.g. "200px", "20%", "auto"). By default, columns grow and shrink according
    * to the available space.
    */
  lazy val width: HtmlAttr[String] = htmlAttr("width", StringAsIsCodec)

  /** Minimum width of the column, as a CSS size. In `Popin` overflow mode, a column whose minimum width does not fit
    * anymore moves into the popin area.
    */
  lazy val minWidth: HtmlAttr[String] = htmlAttr("min-width", StringAsIsCodec)

  object slots {

    /** Header cell action, e.g. a [[TableHeaderCellActionAI]] (since UI5 2.8). */
    val action: Slot = Slot("action")
  }

  object events {}

  def actionAI: TableHeaderCellActionAI.type = TableHeaderCellActionAI

}
