package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-table-group-row component represents a group header row in the ui5-table. It is used to visually group rows
  * and spans across all visible table columns.
  *
  * It is placed as a direct child of the ui5-table, alongside ui5-table-row elements. Rows following a group row are
  * considered part of that group until the next group row. Its (text) content is given as children.
  *
  * Note: group rows do not support cells, actions, navigation, interaction, selection, nor the [[TableVirtualizer]].
  *
  * Since UI5 2.23.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/TableGroupRow/">the doc</a> for more information.
  */
object TableGroupRow extends WebComponent {

  @js.native
  trait RawElement extends js.Object

  @js.native
  @JSImport("@ui5/webcomponents/dist/TableGroupRow.js", JSImport.Default)
  object RawImport extends js.Object
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-table-group-row")

  /** Whether the row is movable (drag and drop). */
  lazy val movable: HtmlAttr[Boolean] = htmlAttr("movable", BooleanAsAttrPresenceCodec)

  object slots {}

  object events {}

}
