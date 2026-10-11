package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.ListItemAccessibleRole
import be.doeraene.webcomponents.ui5.configkeys.ListItemType
import be.doeraene.webcomponents.ui5.configkeys.ValueState
import be.doeraene.webcomponents.ui5.datatypes.AccessibilityAttributes
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.eventtypes.HasEscPressed
import be.doeraene.webcomponents.ui5.eventtypes.HasItem
import be.doeraene.webcomponents.ui5.eventtypes.ListItemClickDetail
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.concurrent.duration.FiniteDuration
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-user-menu-item is the item to use inside a [[UserMenu]]. It is a [[MenuItem]], hence an arbitrary hierarchy
  * structure can be represented by recursively nesting user menu items.
  *
  * @since UI5 2.5.0
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/UserMenuItem/">the doc</a> for more information.
  */
object UserMenuItem extends WebComponent with HasIcon with HasText with HasAdditionalText {

  @js.native
  trait RawElement extends js.Object {
    def text: js.UndefOr[String]                         = js.native
    def checked: Boolean                                 = js.native
    def accessibilityAttributes: AccessibilityAttributes = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/UserMenuItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-user-menu-item")

  lazy val accessibleName: HtmlAttr[String] = htmlAttr("accessible-name", StringAsIsCodec)

  lazy val disabled: HtmlAttr[Boolean]     = htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val highlight: HtmlAttr[ValueState] = ValueState.asHtmlAttr("highlight")
  lazy val navigated: HtmlAttr[Boolean]    = htmlAttr("navigated", BooleanAsAttrPresenceCodec)
  lazy val selected: HtmlAttr[Boolean]     = htmlAttr("selected", BooleanAsAttrPresenceCodec)
  lazy val tooltip: HtmlAttr[String]       = htmlAttr("tooltip", StringAsIsCodec)
  lazy val tpe: HtmlAttr[ListItemType]     = ListItemType.asHtmlAttr("type")

  /** Whether the item is checked. Only taken into account when the item is inside a [[UserMenuItemGroup]] whose
    * `checkMode` is `Single` or `Multiple`.
    */
  lazy val checked: HtmlAttr[Boolean] = htmlAttr("checked", BooleanAsAttrPresenceCodec)

  /** Whether a selection (check) indicator is shown (since UI5 2.22.0). */
  lazy val showSelection: HtmlAttr[Boolean] = htmlAttr("show-selection", BooleanAsAttrPresenceCodec)

  /** Role of the item. If not set, it is inherited from the parent list. */
  lazy val accessibleRole: HtmlAttr[ListItemAccessibleRole] = ListItemAccessibleRole.asHtmlAttr("accessible-role")

  /** Additional accessibility attributes. Supported fields: `ariaKeyShortcuts`, `role`, `hasPopup`, `ariaSetsize` and
    * `ariaPosinset`.
    */
  lazy val accessibilityAttributes: HtmlProp[AccessibilityAttributes, AccessibilityAttributes] =
    AccessibilityAttributes.asHtmlProp

  lazy val loading: HtmlAttr[Boolean]             = htmlAttr("loading", BooleanAsAttrPresenceCodec)
  lazy val loadingDelay: HtmlAttr[FiniteDuration] = htmlAttr("loading-delay", FiniteDurationCodec)

  object slots {
    val endContent: Slot   = Slot("endContent")
    val deleteButton: Slot = Slot("deleteButton")
  }

  object events {
    val onDetailClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("detail-click")

    /** Fired when the item is activated. */
    val onClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ListItemClickDetail]] = new EventProp("click")

    /** Fired when the `checked` state of the item changes by user interaction. */
    val onCheck: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("check")

    /** Fired before the sub-menu is opened. Can be prevented. */
    val onBeforeOpen: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasItem[Ref]]] = new EventProp("before-open")

    /** Fired after the sub-menu is opened. */
    val onOpen: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("open")

    /** Fired before the sub-menu is closed. Can be prevented. */
    val onBeforeClose: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasEscPressed]] = new EventProp(
      "before-close"
    )

    /** Fired after the sub-menu is closed. */
    val onClose: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("close")
  }

  // reference to itself, useful for sub-menus
  def item: UserMenuItem.type = this

  def group: UserMenuItemGroup.type = UserMenuItemGroup

}
