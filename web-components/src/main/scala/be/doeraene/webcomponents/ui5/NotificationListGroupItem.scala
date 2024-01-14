package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, IconName, Priority}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.concurrent.duration.FiniteDuration
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.AvatarSize.L

/** The ui5-li-notification-group is a special type of list item, that unlike others can group items within self,
  * usually ui5-li-notification items.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/NotificationListGroupItem/">the doc</a> for
  *   more information.
  */
object NotificationListGroupItem extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/NotificationListGroupItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-li-notification-group")

  lazy val collapsed: HtmlAttr[Boolean] = htmlAttr("collapsed", BooleanAsAttrPresenceCodec)

  lazy val showCounter: HtmlAttr[Boolean] = htmlAttr("show-counter", BooleanAsAttrPresenceCodec)

  lazy val busy: HtmlAttr[Boolean] = htmlAttr("busy", BooleanAsAttrPresenceCodec)

  lazy val busyDelay: HtmlAttr[FiniteDuration] = htmlAttr("busy-delay", FiniteDurationCodec)

  lazy val priority: HtmlAttr[Priority] = htmlAttr("priority", Priority.AsStringCodec)

  lazy val read: HtmlAttr[Boolean] = htmlAttr("read", BooleanAsAttrPresenceCodec)

  lazy val showClose: HtmlAttr[Boolean] = htmlAttr("show-close", BooleanAsAttrPresenceCodec)

  lazy val titleText: HtmlAttr[String] = htmlAttr("title-text", StringAsIsCodec)

  object slots {
    val actions: Slot = Slot("actions")
  }

  object events {
    val onToggle: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("toggle")
    val onClose: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("close")
  }

  def item: NotificationListItem.type = NotificationListItem

}
