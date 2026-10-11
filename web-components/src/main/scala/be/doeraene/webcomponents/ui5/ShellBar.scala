package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.datatypes.ShellBarAccessibilityAttributes
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.eventtypes.HasItem
import be.doeraene.webcomponents.ui5.eventtypes.HasItems
import be.doeraene.webcomponents.ui5.eventtypes.HasTargetRef
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.AsIsCodec
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.annotation.JSName

/** The ui5-shellbar is meant to serve as an application header and includes numerous built-in features, such as: logo,
  * profile image/icon, title, search field, notifications and so on.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/ShellBar/">the doc</a> for more information.
  */
object ShellBar extends WebComponent with HasIcon {

  @js.native
  trait RawElement extends js.Object {

    /** Closes the overflow area. Useful to manually close the overflow after having suppressed automatic closing with
      * preventDefault() of ShellbarItem's click event
      */
    def closeOverflow(): Unit = js.native

    def accessibilityAttributes: ShellBarAccessibilityAttributes = js.native

    def logoDomRef: dom.HTMLElement          = js.native
    def notificationsDomRef: dom.HTMLElement = js.native
    def overflowDomRef: dom.HTMLElement      = js.native
    def productSwitchDomRef: dom.HTMLElement = js.native
    def profileDomRef: dom.HTMLElement       = js.native

    /** Returns the search button DOM reference (asynchronously). */
    @JSName("getSearchButtonDomRef")
    def getSearchButtonDomRefJS: js.Promise[dom.HTMLElement] = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/ShellBar.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-shellbar")

  lazy val primaryTitle: HtmlAttr[String] =
    htmlAttr("primary-title", StringAsIsCodec)
  lazy val secondaryTitle: HtmlAttr[String] =
    htmlAttr("secondary-title", StringAsIsCodec)

  lazy val notificationsCount: HtmlAttr[String] =
    htmlAttr("notifications-count", StringAsIsCodec)

  lazy val showProductSwitch: HtmlAttr[Boolean] =
    htmlAttr("show-product-switch", BooleanAsAttrPresenceCodec)

  @deprecated("show-copilot has been removed in UI5 2.0.0 and has no effect. Use the `assistant` slot instead.", since = "2.0.0")
  lazy val showCoPilot: HtmlAttr[Boolean] =
    htmlAttr("show-copilot", BooleanAsAttrPresenceCodec)

  lazy val showNotifications: HtmlAttr[Boolean] =
    htmlAttr("show-notifications", BooleanAsAttrPresenceCodec)

  /** Defines whether the search field (in the `searchField` slot) is expanded/visible. */
  lazy val showSearchField: HtmlAttr[Boolean] =
    htmlAttr("show-search-field", BooleanAsAttrPresenceCodec)

  /** Hides the search button (experimental in UI5). */
  lazy val hideSearchButton: HtmlAttr[Boolean] =
    htmlAttr("hide-search-button", BooleanAsAttrPresenceCodec)

  /** Disables the automatic search field expansion/collapse when the available space is not enough (experimental in
    * UI5).
    */
  lazy val disableSearchCollapse: HtmlAttr[Boolean] =
    htmlAttr("disable-search-collapse", BooleanAsAttrPresenceCodec)

  /** Additional accessibility attributes of the ShellBar areas.
    *
    *   - `logo`: supports `role` ("button" or "link") and `name`
    *   - `notifications`, `product`, `search`, `overflow`: support `hasPopup` and `expanded`
    *   - `profile`: supports `name`, `hasPopup` and `expanded`
    *   - `branding`: supports `name`
    */
  lazy val accessibilityAttributes: HtmlProp[ShellBarAccessibilityAttributes, ShellBarAccessibilityAttributes] =
    htmlProp("accessibilityAttributes", AsIsCodec())

  object events {

    trait SearchButtonClickDetail extends HasTargetRef[dom.HTMLElement] {
      def searchFieldVisible: Boolean
    }

    trait SearchFieldToggleDetail extends js.Object {
      def expanded: Boolean
    }

    @deprecated("co-pilot-click has been removed in UI5 2.0.0 and is never fired. Use the `assistant` slot instead.", since = "2.0.0")
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
    val onSearchButtonClick =
      new EventProp[EventWithPreciseTarget[Ref] & HasDetail[SearchButtonClickDetail]]("search-button-click")
    val onSearchFieldToggle =
      new EventProp[EventWithPreciseTarget[Ref] & HasDetail[SearchFieldToggleDetail]]("search-field-toggle")

    /** Fired when the search field is cleared (experimental in UI5). */
    val onSearchFieldClear =
      new EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasTargetRef[dom.HTMLElement]]]("search-field-clear")

    /** Fired when items of the `content` slot get hidden or shown, with the newly hidden items (experimental in UI5).
      */
    val onContentItemVisibilityChange =
      new EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasItems[dom.HTMLElement]]](
        "content-item-visibility-change"
      )
  }

  object slots {
    def logo: Slot        = Slot("logo")
    def profile: Slot     = Slot("profile")
    def menuItems: Slot   = Slot("menuItems")
    def searchField: Slot = Slot("searchField")
    def startButton: Slot = Slot("startButton")
    def content: Slot     = Slot("content")

    /** Slot for an AI assistant button (e.g. Joule). */
    def assistant: Slot = Slot("assistant")

    /** Slot for a [[ShellBarBranding]] (experimental in UI5). Has priority over `primaryTitle` and the `logo` slot. */
    def branding: Slot = Slot("branding")
  }

  def item: ShellBarItem.type         = ShellBarItem
  def branding: ShellBarBranding.type = ShellBarBranding
  def spacer: ShellBarSpacer.type     = ShellBarSpacer
  def search: ShellBarSearch.type     = ShellBarSearch

}
