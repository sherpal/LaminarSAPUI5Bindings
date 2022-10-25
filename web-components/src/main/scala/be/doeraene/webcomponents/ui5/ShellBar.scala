package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, IconName}
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom
import be.doeraene.webcomponents.ui5.eventtypes.{HasDetail, HasTargetRef}
import be.doeraene.webcomponents.ui5.internal.Slot

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.eventtypes.HasItem
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.WebComponent

/** Simple UI button
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/ShellBar/">the doc</a> for more
  *   information.
  */
object ShellBar extends WebComponent with HasIcon {

  @js.native
  trait RawElement extends js.Object {
    def closeOverflow(): Unit = js.native

    def accessibilityRoles: js.Object = js.native
    def accessibilityTexts: js.Object = js.native

    def copilotDomRef: dom.HTMLElement = js.native
    def logoDomRef: dom.HTMLElement = js.native
    def overflowDomRef: dom.HTMLElement = js.native
    def productSwitchDomRef: dom.HTMLElement = js.native
    def profileDomRef: dom.HTMLElement = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/ShellBar", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-shellbar")

  lazy val primaryTitle: ReactiveHtmlAttr[String] =
    customHtmlAttr("primary-title", StringAsIsCodec)
  lazy val secondaryTitle: ReactiveHtmlAttr[String] =
    customHtmlAttr("secondary-title", StringAsIsCodec)

  lazy val notificationsCount: ReactiveHtmlAttr[String] =
    customHtmlAttr("notifications-count", StringAsIsCodec)

  lazy val showProductSwitch: ReactiveHtmlAttr[Boolean] =
    customHtmlAttr("show-product-switch", BooleanAsAttrPresenceCodec)

  lazy val showCoPilot: ReactiveHtmlAttr[Boolean] =
    customHtmlAttr("show-copilot", BooleanAsAttrPresenceCodec)

  lazy val showNotifications: ReactiveHtmlAttr[Boolean] =
    customHtmlAttr("show-notifications", BooleanAsAttrPresenceCodec)

  object events {
    val onCopilotClick =
      new EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasTargetRef[dom.HTMLElement]]]("co-pilot-click")
    val onProfileClick =
      new EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasTargetRef[dom.HTMLElement]]]("profile-click")
    val onLogoClick =
      new EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasTargetRef[dom.HTMLElement]]]("logo-click")
    val onMenuItemClick =
      new EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasItem[dom.HTMLElement]]]("menu-item-click")
    val onNotificationsClick =
      new EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasTargetRef[dom.HTMLElement]]]("notifications-click")
    val onProductSwitchClick =
      new EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasTargetRef[dom.HTMLElement]]]("product-switch-click")
  }

  object slots {
    def logo: Slot        = new Slot("logo")
    def profile: Slot     = new Slot("profile")
    def menuItems: Slot   = new Slot("menuItems")
    def searchField: Slot = new Slot("searchField")
    def startButton: Slot = new Slot("startButton")
  }

  

  def item: ShellBarItem.type = ShellBarItem

}
