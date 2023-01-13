package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.Priority
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.{HtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.configkeys.WrappingType
import scala.concurrent.duration.FiniteDuration
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.WebComponent

/** The ui5-li-notification is a type of list item, meant to display notifications.
  *
  * The component has a rich set of various properties that allows the user to set avatar, titleText, descriptive
  * content and footnotes to fully describe a notification.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/NotificationListItem/">the doc</a> for more
  *   information.
  */
object NotificationListItem extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/NotificationListItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-li-notification")

  lazy val wrappingType: HtmlAttr[WrappingType] = customHtmlAttr("wrapping-type", WrappingType.AsStringCodec)

  lazy val busy: HtmlAttr[Boolean] = customHtmlAttr("busy", BooleanAsAttrPresenceCodec)

  lazy val busyDelay: HtmlAttr[FiniteDuration] = customHtmlAttr("busy-delay", FiniteDurationCodec)

  lazy val priority: HtmlAttr[Priority] = customHtmlAttr("priority", Priority.AsStringCodec)

  lazy val read: HtmlAttr[Boolean] = customHtmlAttr("read", BooleanAsAttrPresenceCodec)

  lazy val showClose: HtmlAttr[Boolean] = customHtmlAttr("show-close", BooleanAsAttrPresenceCodec)

  lazy val titleText: HtmlAttr[String] = customHtmlAttr("title-text", StringAsIsCodec)

  object slots {
    val avatar: Slot    = Slot("avatar")
    val footnotes: Slot = Slot("footnotes")
    val actions: Slot   = Slot("actions")
  }

  object events {
    val onClose: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("close")
  }

  

  def action: NotificationAction.type = NotificationAction

}
