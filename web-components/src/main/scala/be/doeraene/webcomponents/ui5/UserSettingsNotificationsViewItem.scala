package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.ListItemAccessibleRole
import be.doeraene.webcomponents.ui5.configkeys.ListItemType
import be.doeraene.webcomponents.ui5.configkeys.ValueState
import be.doeraene.webcomponents.ui5.datatypes.AccessibilityAttributes
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.eventtypes.ListItemClickDetail
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-user-settings-notifications-view-item represents a single notification setting within the
  * [[UserSettingsNotificationsView]]. It displays a title (`text`) and an optional byline (`bylineText`).
  *
  * By default a trailing switch reflects the `checked` state. Content given in the `endContent` slot (e.g. a
  * [[Select]]) replaces the switch (and then no `switch-change` event is fired). Items can also be `navigable`.
  *
  * @since UI5 2.27.0
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/UserSettingsNotificationsViewItem/">the doc</a> for
  *   more information.
  */
object UserSettingsNotificationsViewItem extends WebComponent with HasText {

  @js.native
  trait RawElement extends js.Object {
    def itemKey: String   = js.native
    def text: String      = js.native
    def checked: Boolean  = js.native
    def selected: Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/UserSettingsNotificationsViewItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-user-settings-notifications-view-item")

  /** Unique identifier of the item. For navigable items, also used to route to the sibling secondary view with that id.
    */
  lazy val itemKey: HtmlAttr[String] = htmlAttr("item-key", StringAsIsCodec)

  /** Byline text of the item, rendered below the title. */
  lazy val bylineText: HtmlAttr[String] = htmlAttr("byline-text", StringAsIsCodec)

  /** Whether the trailing switch is on (ignored when the `endContent` slot is used). */
  lazy val checked: HtmlAttr[Boolean] = htmlAttr("checked", BooleanAsAttrPresenceCodec)

  /** Whether the item is navigable (a navigation arrow is rendered and the whole row fires the view's `item-click`).
    */
  lazy val navigable: HtmlAttr[Boolean] = htmlAttr("navigable", BooleanAsAttrPresenceCodec)

  lazy val selected: HtmlAttr[Boolean] = htmlAttr("selected", BooleanAsAttrPresenceCodec)

  lazy val accessibleName: HtmlAttr[String]                 = htmlAttr("accessible-name", StringAsIsCodec)
  lazy val accessibleRole: HtmlAttr[ListItemAccessibleRole] = ListItemAccessibleRole.asHtmlAttr("accessible-role")
  lazy val highlight: HtmlAttr[ValueState]                  = ValueState.asHtmlAttr("highlight")
  lazy val movable: HtmlAttr[Boolean]                       = htmlAttr("movable", BooleanAsAttrPresenceCodec)
  lazy val navigated: HtmlAttr[Boolean]                     = htmlAttr("navigated", BooleanAsAttrPresenceCodec)
  lazy val tooltip: HtmlAttr[String]                        = htmlAttr("tooltip", StringAsIsCodec)
  lazy val tpe: HtmlAttr[ListItemType]                      = ListItemType.asHtmlAttr("type")

  /** Additional accessibility attributes. Supported fields: `ariaSetsize`, `ariaPosinset` and `hasPopup`. */
  lazy val accessibilityAttributes: HtmlProp[AccessibilityAttributes, AccessibilityAttributes] =
    AccessibilityAttributes.asHtmlProp

  object slots {

    /** Custom content rendered at the end of the item, replacing the default switch. */
    val endContent: Slot   = Slot("endContent")
    val deleteButton: Slot = Slot("deleteButton")
  }

  object events {

    trait SwitchChangeDetail extends js.Object {
      def checked: Boolean
      def item: Ref
    }

    /** Fired when the switch state changes (not fired when the `endContent` slot is used). Bubbles. */
    val onSwitchChange: EventProp[EventWithPreciseTarget[Ref] & HasDetail[SwitchChangeDetail]] =
      new EventProp("switch-change")

    val onClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ListItemClickDetail]] = new EventProp("click")
    val onDetailClick: EventProp[EventWithPreciseTarget[Ref]]                             = new EventProp("detail-click")
  }

}
