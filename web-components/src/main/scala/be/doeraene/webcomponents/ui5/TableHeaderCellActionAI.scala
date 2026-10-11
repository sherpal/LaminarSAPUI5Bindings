package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.eventtypes.HasTargetRef
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-table-header-cell-action-ai component defines a dedicated AI action for a table column. It is placed in the
  * `action` slot of a ui5-table-header-cell.
  *
  * Since UI5 2.8.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/TableHeaderCellActionAI/">the doc</a> for more
  *   information.
  */
object TableHeaderCellActionAI extends WebComponent {

  @js.native
  trait RawElement extends js.Object

  @js.native
  @JSImport("@ui5/webcomponents/dist/TableHeaderCellActionAI.js", JSImport.Default)
  object RawImport extends js.Object
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-table-header-cell-action-ai")

  object slots {}

  object events {

    /** Fired when the action is clicked. The `targetRef` is the element that triggered the event (e.g. to open a
      * popover next to it).
      */
    val onClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasTargetRef[dom.HTMLElement]]] =
      new EventProp("click")
  }

}
