package be.doeraene.webcomponents.ui5

import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, IntAsStringCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.internal.Slot
import be.doeraene.webcomponents.ui5.eventtypes.{HasDetail, HasItem, HasTargetRef}
import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.MoveEventDetail

/** The ui5-li-groupheader is a special list item, used only to separate other list items into logical groups.
  */
object UListGroup extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def selected: Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/ListItemGroup.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-li-group")

  lazy val headerText: HtmlAttr[String]            = htmlAttr("header-text", StringAsIsCodec)
  lazy val headerAccessibleName: HtmlAttr[Boolean] = htmlAttr("header-accessible-name", BooleanAsAttrPresenceCodec)

  object slots {
    val header: Slot = Slot("header")
  }

  object events {
    lazy val onMove: EventProp[EventWithPreciseTarget[Ref] & HasDetail[MoveEventDetail[item.Ref]]] = new EventProp(
      "move"
    )
    lazy val onMoveOver: EventProp[EventWithPreciseTarget[Ref] & HasDetail[MoveEventDetail[item.Ref]]] = new EventProp(
      "move-over"
    )
  }

  def item = ListItem

}
