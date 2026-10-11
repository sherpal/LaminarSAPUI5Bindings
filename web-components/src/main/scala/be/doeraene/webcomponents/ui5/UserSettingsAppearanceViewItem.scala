package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.AvatarColorScheme
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

/** The ui5-user-settings-appearance-view-item represents a theme/appearance option item within the
  * [[UserSettingsAppearanceView]]. It displays a theme with an avatar icon and a text label, and can be selected.
  *
  * @since UI5 2.17.0
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/UserSettingsAppearanceViewItem/">the doc</a> for
  *   more information.
  */
object UserSettingsAppearanceViewItem extends WebComponent with HasIcon with HasText {

  @js.native
  trait RawElement extends js.Object {
    def itemKey: String   = js.native
    def text: String      = js.native
    def selected: Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/UserSettingsAppearanceViewItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-user-settings-appearance-view-item")

  /** Unique identifier of the item (e.g. the theme name). */
  lazy val itemKey: HtmlAttr[String] = htmlAttr("item-key", StringAsIsCodec)

  /** Color scheme of the avatar (default: Accent7). */
  lazy val colorScheme: HtmlAttr[AvatarColorScheme] = htmlAttr("color-scheme", AvatarColorScheme.AsStringCodec)

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
    val deleteButton: Slot = Slot("deleteButton")
  }

  object events {
    val onClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ListItemClickDetail]] = new EventProp("click")
    val onDetailClick: EventProp[EventWithPreciseTarget[Ref]]                             = new EventProp("detail-click")
  }

}
