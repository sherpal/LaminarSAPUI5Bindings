package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.AvatarSize.L
import be.doeraene.webcomponents.ui5.configkeys.ButtonDesign
import be.doeraene.webcomponents.ui5.configkeys.Growing
import be.doeraene.webcomponents.ui5.configkeys.IconName
import be.doeraene.webcomponents.ui5.configkeys.Priority
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.concurrent.duration.FiniteDuration
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

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

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-li-notification-group")

  lazy val collapsed: HtmlAttr[Boolean]           = htmlAttr("collapsed", BooleanAsAttrPresenceCodec)
  lazy val loading: HtmlAttr[Boolean]             = htmlAttr("loading", BooleanAsAttrPresenceCodec)
  lazy val loadingDelay: HtmlAttr[FiniteDuration] = htmlAttr("loading-delay", FiniteDurationCodec)
  lazy val read: HtmlAttr[Boolean]                = htmlAttr("read", BooleanAsAttrPresenceCodec)
  lazy val titleText: HtmlAttr[String]            = htmlAttr("title-text", StringAsIsCodec)
  lazy val growing: HtmlAttr[Growing]             = htmlAttr("growing", Growing.AsStringCodec)

  @scala.annotation.compileTimeOnly("showClose property has been removed")
  def showClose: HtmlAttr[Boolean] = ???
  @scala.annotation.compileTimeOnly("showCounter property has been removed")
  def showCounter: HtmlAttr[Boolean] = ???
  @scala.annotation.compileTimeOnly("priority property has been removed")
  def priority: HtmlAttr[Priority] = ???
  @deprecated("busy property has been renamed to loading", since = "2.0.0")
  def busy: HtmlAttr[Boolean] = loading
  @deprecated("busyDelay property has been renamed to loadingDelay", since = "2.0.0")
  def busyDelay: HtmlAttr[FiniteDuration] = loadingDelay

  object slots {
    @scala.annotation.compileTimeOnly("actions slot has been removed")
    def actions: Slot = Slot("actions")
  }

  object events {
    val onToggle: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("toggle")

    @scala.annotation.compileTimeOnly("close event of NotificationListGroupItem has been removed")
    def onClose: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("close")
  }

  def item: NotificationListItem.type = NotificationListItem

}
