package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-user-settings-item represents an item in the [[UserSettingsDialog]].
  *
  * Its children are "page" views ([[UserSettingsView]] or one of its specialisations), while views given in the `tabs`
  * slot are displayed as tabs. If there is no tab view, the first page view is shown (unless one is `selected`).
  *
  * @since UI5 2.8.0
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/UserSettingsItem/">the doc</a> for more
  *   information.
  */
object UserSettingsItem extends WebComponent with HasIcon with HasText {

  @js.native
  trait RawElement extends js.Object {
    def selected: Boolean = js.native
    def text: String      = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/UserSettingsItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-user-settings-item")

  lazy val accessibleName: HtmlAttr[String] = htmlAttr("accessible-name", StringAsIsCodec)
  lazy val disabled: HtmlAttr[Boolean]      = htmlAttr("disabled", BooleanAsAttrPresenceCodec)

  /** Header text of the item (displayed above its views). */
  lazy val headerText: HtmlAttr[String] = htmlAttr("header-text", StringAsIsCodec)

  lazy val loading: HtmlAttr[Boolean] = htmlAttr("loading", BooleanAsAttrPresenceCodec)

  /** Indicates why the item is in loading state. */
  lazy val loadingReason: HtmlAttr[String] = htmlAttr("loading-reason", StringAsIsCodec)

  /** Whether this item is the one currently displayed in the dialog. */
  lazy val selected: HtmlAttr[Boolean] = htmlAttr("selected", BooleanAsAttrPresenceCodec)

  /** Tooltip of the item (should be given, as it is shown when only the icon is visible). */
  lazy val tooltip: HtmlAttr[String] = htmlAttr("tooltip", StringAsIsCodec)

  object slots {

    /** The tab views of the item. (Note: this slot replaced the former `pages` slot.) */
    val tabs: Slot = Slot("tabs")
  }

  object events {

    trait SelectionChangeDetail extends js.Object {
      def view: UserSettingsView.Ref
    }

    /** Fired when the selected view changed. Can be prevented. */
    val onSelectionChange: EventProp[EventWithPreciseTarget[Ref] & HasDetail[SelectionChangeDetail]] =
      new EventProp("selection-change")
  }

  def view: UserSettingsView.type                           = UserSettingsView
  def accountView: UserSettingsAccountView.type             = UserSettingsAccountView
  def appearanceView: UserSettingsAppearanceView.type       = UserSettingsAppearanceView
  def notificationsView: UserSettingsNotificationsView.type = UserSettingsNotificationsView

}
