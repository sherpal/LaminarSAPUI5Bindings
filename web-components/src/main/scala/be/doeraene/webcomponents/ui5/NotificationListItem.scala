package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.Priority
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
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

  type Ref         = dom.html.Element with RawElement
  type ModFunction = NotificationListItem.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-li-notification")

  lazy val wrappingType: ReactiveHtmlAttr[WrappingType] = customHtmlAttr("wrapping-type", WrappingType.AsStringCodec)

  lazy val busy: ReactiveHtmlAttr[Boolean] = customHtmlAttr("busy", BooleanAsAttrPresenceCodec)

  lazy val busyDelay: ReactiveHtmlAttr[FiniteDuration] = customHtmlAttr("busy-delay", FiniteDurationCodec)

  lazy val priority: ReactiveHtmlAttr[Priority] = customHtmlAttr("priority", Priority.AsStringCodec)

  lazy val read: ReactiveHtmlAttr[Boolean] = customHtmlAttr("read", BooleanAsAttrPresenceCodec)

  lazy val showClose: ReactiveHtmlAttr[Boolean] = customHtmlAttr("show-close", BooleanAsAttrPresenceCodec)

  lazy val titleText: ReactiveHtmlAttr[String] = customHtmlAttr("title-text", StringAsIsCodec)

  object slots {
    val avatar: Slot    = Slot("avatar")
    val footnotes: Slot = Slot("footnotes")
    val actions: Slot   = Slot("actions")
  }

  object events {
    val onClose: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("close")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(NotificationListItem)): _*)

  def action: NotificationAction.type = NotificationAction

}
