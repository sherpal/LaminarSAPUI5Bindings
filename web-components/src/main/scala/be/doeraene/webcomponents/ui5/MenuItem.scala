package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.{ListItemAccessibleRole, ListItemType, ValueState}
import be.doeraene.webcomponents.ui5.datatypes.AccessibilityAttributes
import be.doeraene.webcomponents.ui5.eventtypes.*
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.concurrent.duration.FiniteDuration
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** ui5-menu-item is the item to use inside a ui5-menu. An arbitrary hierarchy structure can be represented by
  * recursively nesting menu items.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/MenuItem/">the doc</a> for more information.
  */
object MenuItem extends WebComponent with HasIcon with HasText with HasAdditionalText {

  @js.native
  trait RawElement extends js.Object {
    def accessibilityAttributes: js.Object = js.native

    def checked: Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/MenuItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-menu-item")

  lazy val accessibleName: HtmlAttr[String] = htmlAttr("accessible-name", StringAsIsCodec)

  lazy val disabled: HtmlAttr[Boolean]     = htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val highlight: HtmlAttr[ValueState] = ValueState.asHtmlAttr("highlight")
  lazy val navigated: HtmlAttr[Boolean]    = htmlAttr("navigated", BooleanAsAttrPresenceCodec)
  lazy val selected: HtmlAttr[Boolean]     = htmlAttr("selected", BooleanAsAttrPresenceCodec)
  lazy val tooltip: HtmlAttr[String]       = htmlAttr("tooltip", StringAsIsCodec)
  lazy val tpe: HtmlAttr[ListItemType]     = ListItemType.asHtmlAttr("type")

  /** Whether the item is checked. Only taken into account when the item is inside a [[MenuItemGroup]] whose
    * `checkMode` is `Single` or `Multiple`.
    *
    * @since UI5 2.12.0
    */
  lazy val checked: HtmlAttr[Boolean] = htmlAttr("checked", BooleanAsAttrPresenceCodec)

  /** Role of the item. If not set, it is inherited from the parent list.
    *
    * @since UI5 2.23.0
    */
  lazy val accessibleRole: HtmlAttr[ListItemAccessibleRole] = ListItemAccessibleRole.asHtmlAttr("accessible-role")

  /** Additional accessibility attributes. Supported fields: `ariaKeyShortcuts`, `role`, `hasPopup`, `ariaSetsize` and
    * `ariaPosinset`.
    */
  lazy val accessibilityAttributes: HtmlProp[AccessibilityAttributes, AccessibilityAttributes] =
    AccessibilityAttributes.asHtmlProp

  @scala.annotation.compileTimeOnly("startsSection has been replace by the MenuSeparator component")
  def startsSection: HtmlAttr[Boolean] = ???

  lazy val loading: HtmlAttr[Boolean]             = htmlAttr("loading", BooleanAsAttrPresenceCodec)
  lazy val loadingDelay: HtmlAttr[FiniteDuration] = htmlAttr("loading-delay", FiniteDurationCodec)

  object slots {
    val endContent: Slot   = Slot("endContent")
    val deleteButton: Slot = Slot("deleteButton")
  }

  object events {
    val onDetailClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("detail-click")

    /** Fired when the item is activated (since UI5 2.23.0). */
    val onClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ListItemClickDetail]] = new EventProp("click")

    /** Fired when the `checked` state of the item changes by user interaction (since UI5 2.12.0). */
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
  def item: MenuItem.type = this

  def separator: MenuSeparator.type = MenuSeparator
  def group: MenuItemGroup.type     = MenuItemGroup

}
