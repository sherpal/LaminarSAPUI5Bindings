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

/** Simple UI button
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/ShellBar/">the doc</a> for more
  *   information.
  */
object ShellBar extends HasIcon with HasOnClick {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/ShellBar", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = ShellBar.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-shellbar")

  val id: ReactiveProp[String, String] = idAttr

  val primaryTitle: ReactiveHtmlAttr[String] =
    customHtmlAttr("primary-title", StringAsIsCodec)
  val secondaryTitle: ReactiveHtmlAttr[String] =
    customHtmlAttr("secondary-title", StringAsIsCodec)

  val notificationsCount: ReactiveHtmlAttr[String] =
    customHtmlAttr("notifications-count", StringAsIsCodec)

  val showProductSwitch: ReactiveHtmlAttr[Boolean] =
    customHtmlAttr("show-product-switch", BooleanAsAttrPresenceCodec)

  val showCoPilot: ReactiveHtmlAttr[Boolean] =
    customHtmlAttr("show-copilot", BooleanAsAttrPresenceCodec)

  val showNotifications: ReactiveHtmlAttr[Boolean] =
    customHtmlAttr("show-notifications", BooleanAsAttrPresenceCodec)

  val onProfileClick = new EventProp[dom.Event & HasDetail[HasTargetRef[dom.HTMLElement]]]("profile-click")
  val onLogoClick    = new EventProp[dom.Event & HasDetail[HasTargetRef[dom.HTMLElement]]]("logo-click")

  object slots {
    def logo: Slot        = new Slot("logo")
    def profile: Slot     = new Slot("profile")
    def menuItems: Slot   = new Slot("menuItems")
    def searchField: Slot = new Slot("searchField")
    def startButton: Slot = new Slot("startButton")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(ShellBar)): _*)

  def Item: ShellBarItem.type = ShellBarItem

}
