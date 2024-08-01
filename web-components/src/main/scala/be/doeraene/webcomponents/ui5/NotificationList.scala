package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.Priority
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.configkeys.WrappingType
import scala.concurrent.duration.FiniteDuration
import be.doeraene.webcomponents.ui5.eventtypes.{EventWithPreciseTarget, HasDetail, HasItem}
import be.doeraene.webcomponents.WebComponent

/** The ui5-notification-list web component represents a container for ui5-li-notification-group and
  * ui5-li-notification.
  */
object NotificationList extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/NotificationList.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-notification-list")

  lazy val noDataText: HtmlAttr[String] = htmlAttr("no-data-text", StringAsIsCodec)

  object slots {}

  object events {
    val onItemClick  = new EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasItem[item.Ref]]]("item-click")
    val onItemClose  = new EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasItem[item.Ref]]]("item-close")
    val onItemToggle = new EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasItem[item.Ref]]]("item-toggle")
  }

  def item = NotificationListItem

}
