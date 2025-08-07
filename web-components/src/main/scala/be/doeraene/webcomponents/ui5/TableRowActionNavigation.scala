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

object TableRowActionNavigation extends WebComponent {

  @js.native
  trait RawElement extends js.Object

  @js.native
  @JSImport("@ui5/webcomponents/dist/TableRowActionNavigation.js", JSImport.Default)
  object RawImport extends js.Object
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-table-row-action-navigation")

  lazy val interactive: HtmlAttr[Boolean] = htmlAttr("interactive", BooleanAsAttrPresenceCodec)
  lazy val invisible: HtmlAttr[Boolean]   = htmlAttr("invisible", BooleanAsAttrPresenceCodec)

  object slots {}

  object events {
    val onClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("click")
  }

}
