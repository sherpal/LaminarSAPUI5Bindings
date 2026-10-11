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
import be.doeraene.webcomponents.ui5.configkeys.IconName

/** The ui5-table-row-action component defines an action for table rows. It is placed in the `actions` slot of a
  * ui5-table-row. Set the `rowActionCount` of the table for the actions to be displayed.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/TableRowAction/">the doc</a> for more information.
  */
object TableRowAction extends WebComponent {

  @js.native
  trait RawElement extends js.Object:
    def rowKey: String = js.native

  @js.native
  @JSImport("@ui5/webcomponents/dist/TableRowAction.js", JSImport.Default)
  object RawImport extends js.Object
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-table-row-action")

  lazy val icon: HtmlAttr[IconName]     = IconName.asHtmlAttr("icon")
  lazy val text: HtmlAttr[String]       = htmlAttr("text", StringAsIsCodec)
  lazy val invisible: HtmlAttr[Boolean] = htmlAttr("invisible", BooleanAsAttrPresenceCodec)

  object slots {}

  object events {
    val onClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("click")
  }

}
