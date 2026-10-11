package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-user-settings-account-view is a view displayed in the [[UserSettingsItem]], showing the user account (a
  * [[UserMenuAccount]] in the `account` slot). Children are additional content of the view.
  *
  * @since UI5 2.17.0
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/UserSettingsAccountView/">the doc</a> for more
  *   information.
  */
object UserSettingsAccountView extends WebComponent with UserSettingsViewAttributes {

  @js.native
  trait RawElement extends js.Object {
    def selected: Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/UserSettingsAccountView.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-user-settings-account-view")

  /** Whether the edit button is shown on the avatar (since UI5 2.26.0). */
  lazy val showEditButton: HtmlAttr[Boolean] = htmlAttr("show-edit-button", BooleanAsAttrPresenceCodec)

  /** Whether the "Manage Account" option is shown. */
  lazy val showManageAccount: HtmlAttr[Boolean] = htmlAttr("show-manage-account", BooleanAsAttrPresenceCodec)

  object slots {

    /** The user account ([[UserMenuAccount]]). */
    val account: Slot = Slot("account")
  }

  object events {

    /** Fired when the "Edit Accounts" button is selected. */
    val onEditAccountsClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("edit-accounts-click")

    /** Fired when the "Manage Account" button is selected. */
    val onManageAccountClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("manage-account-click")
  }

  def account: UserMenuAccount.type = UserMenuAccount

}
