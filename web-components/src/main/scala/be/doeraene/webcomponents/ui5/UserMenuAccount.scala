package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.AvatarColorScheme
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-user-menu-account represents an account in the [[UserMenu]] (in its `accounts` slot) or in a
  * [[UserSettingsAccountView]].
  *
  * @since UI5 2.5.0
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/UserMenuAccount/">the doc</a> for more
  *   information.
  */
object UserMenuAccount extends WebComponent with HasDescription {

  @js.native
  trait RawElement extends js.Object {
    def titleText: String = js.native
    def selected: Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/UserMenuAccount.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-user-menu-account")

  /** Avatar image url of the user. */
  lazy val avatarSrc: HtmlAttr[String] = htmlAttr("avatar-src", StringAsIsCodec)

  /** Avatar initials of the user (displayed when there is no `avatarSrc`). */
  lazy val avatarInitials: HtmlAttr[String] = htmlAttr("avatar-initials", StringAsIsCodec)

  /** Background color of the avatar. (UI5's default is "Auto", which displays the avatar with the `Accent6` color.) */
  lazy val avatarColorScheme: HtmlAttr[AvatarColorScheme] =
    htmlAttr("avatar-color-scheme", AvatarColorScheme.AsStringCodec)

  lazy val titleText: HtmlAttr[String]      = htmlAttr("title-text", StringAsIsCodec)
  lazy val subtitleText: HtmlAttr[String]   = htmlAttr("subtitle-text", StringAsIsCodec)
  lazy val additionalInfo: HtmlAttr[String] = htmlAttr("additional-info", StringAsIsCodec)
  lazy val selected: HtmlAttr[Boolean]      = htmlAttr("selected", BooleanAsAttrPresenceCodec)

  /** Indicates whether a loading indicator should be shown. */
  lazy val loading: HtmlAttr[Boolean] = htmlAttr("loading", BooleanAsAttrPresenceCodec)

  object slots {}

  object events {}

}
