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

/** The ui5-user-menu is an SAP Fiori specific web component that is used in the [[ShellBar]] and allows the user to
  * easily see information and settings for the current user and all other logged in accounts.
  *
  * Children are [[UserMenuItem]]s (and [[UserMenuItemGroup]]s), accounts are [[UserMenuAccount]]s given in the
  * `accounts` slot.
  *
  * @since UI5 2.5.0
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/UserMenu/">the doc</a> for more information.
  */
object UserMenu extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    var open: Boolean = js.native

    /** ID or DOM reference of the element at which the user menu is shown. */
    var opener: js.UndefOr[dom.HTMLElement | String] = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/UserMenu.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-user-menu")

  lazy val open: HtmlAttr[Boolean] = htmlAttr("open", BooleanAsAttrPresenceCodec)

  /** ID of the element at which the user menu is shown. (Set `opener` on the [[RawElement]] to use a DOM reference.) */
  lazy val openerId: HtmlAttr[String] = htmlAttr("opener", StringAsIsCodec)

  /** Whether the avatar of the selected account is interactive (focusable and pressable).
    *
    * @since UI5 2.24.0
    */
  lazy val avatarInteractive: HtmlAttr[Boolean] = htmlAttr("avatar-interactive", BooleanAsAttrPresenceCodec)

  lazy val showEditAccounts: HtmlAttr[Boolean]  = htmlAttr("show-edit-accounts", BooleanAsAttrPresenceCodec)
  lazy val showEditButton: HtmlAttr[Boolean]    = htmlAttr("show-edit-button", BooleanAsAttrPresenceCodec)
  lazy val showManageAccount: HtmlAttr[Boolean] = htmlAttr("show-manage-account", BooleanAsAttrPresenceCodec)
  lazy val showOtherAccounts: HtmlAttr[Boolean] = htmlAttr("show-other-accounts", BooleanAsAttrPresenceCodec)

  object slots {

    /** The user accounts ([[UserMenuAccount]]). The first one is shown as selected, unless another one is `selected`.
      */
    val accounts: Slot = Slot("accounts")

    /** Custom footer content, replacing the default "Sign Out" button (since UI5 2.20.0). */
    val footer: Slot = Slot("footer")

    /** Content of the info area inside the account block (since UI5 2.24.0). */
    val infoArea: Slot = Slot("infoArea")
  }

  object events {

    trait ItemClickDetail extends js.Object {
      def item: UserMenuItem.Ref
    }

    trait ChangeAccountDetail extends js.Object {
      def prevSelectedAccount: UserMenuAccount.Ref
      def selectedAccount: UserMenuAccount.Ref
    }

    val onAvatarClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("avatar-click")

    /** Fired when the account is switched to a different one. Can be prevented. */
    val onChangeAccount: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ChangeAccountDetail]] =
      new EventProp("change-account")

    val onEditAccountsClick: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("edit-accounts-click")
    val onManageAccountClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("manage-account-click")

    /** Fired when a menu item is selected. Can be prevented (to keep the menu open). */
    val onItemClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ItemClickDetail]] = new EventProp("item-click")

    val onOpen: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("open")
    val onClose: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("close")

    /** Fired when the "Sign Out" button is selected. Can be prevented (to keep the menu open). */
    val onSignOutClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("sign-out-click")
  }

  def item: UserMenuItem.type       = UserMenuItem
  def group: UserMenuItemGroup.type = UserMenuItemGroup
  def account: UserMenuAccount.type = UserMenuAccount

}
