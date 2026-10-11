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
import be.doeraene.webcomponents.ui5.configkeys.NotificationListItemImportance
import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.ValueState

/** The ui5-li-notification is a type of list item, meant to display notifications.
  *
  * The component has a rich set of various properties that allows the user to set avatar, titleText, descriptive
  * content and footnotes to fully describe a notification.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/NotificationListItem/">the doc</a> for more
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

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-li-notification")

  lazy val wrappingType: HtmlAttr[WrappingType]   = htmlAttr("wrapping-type", WrappingType.AsStringCodec)
  lazy val loading: HtmlAttr[Boolean]             = htmlAttr("loading", BooleanAsAttrPresenceCodec)
  lazy val loadingDelay: HtmlAttr[FiniteDuration] = htmlAttr("loading-delay", FiniteDurationCodec)
  lazy val state: HtmlAttr[ValueState]            = ValueState.asHtmlAttr("state")
  lazy val read: HtmlAttr[Boolean]                = htmlAttr("read", BooleanAsAttrPresenceCodec)
  lazy val showClose: HtmlAttr[Boolean]           = htmlAttr("show-close", BooleanAsAttrPresenceCodec)
  lazy val titleText: HtmlAttr[String]            = htmlAttr("title-text", StringAsIsCodec)

  /** Importance of the notification. `Important` notifications display an "Important" label. */
  lazy val importance: HtmlAttr[NotificationListItemImportance] =
    NotificationListItemImportance.asHtmlAttr("importance")

  @deprecated("busy property has been renamed to loading", since = "2.0.0")
  def busy: HtmlAttr[Boolean] = loading
  @deprecated("busyDelay property has been renamed to loadingDelay", since = "2.0.0")
  def busyDelay: HtmlAttr[FiniteDuration] = loadingDelay
  @deprecated("priority property has been renamed to state", since = "2.0.0")
  def priority: HtmlAttr[ValueState] = state

  object slots {
    val avatar: Slot    = Slot("avatar")
    val footnotes: Slot = Slot("footnotes")
    val menu: Slot      = Slot("menu")

    @deprecated("The actions slot has been renamed to menu, which expect a Menu component", since = "2.0.0")
    def actions: Slot = menu
  }

  object events {

    /** Fired when the close button is pressed. */
    val onClose: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasItem[RawElement]]] = new EventProp("close")

    trait ClickDetail extends js.Object {
      def originalEvent: dom.Event
    }

    /** Fired when the item is clicked (or activated with Enter/Space).
      *
      * @since UI5 2.23.0
      */
    val onClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ClickDetail]] = new EventProp("click")
  }

  @deprecated(
    "ui5-notification-action does not exist anymore in UI5 2. Use the menu slot with a Menu component instead",
    since = "2.0.0"
  )
  def action: NotificationAction.type = NotificationAction

}
