package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.AvatarColorScheme
import be.doeraene.webcomponents.ui5.configkeys.AvatarInitials
import be.doeraene.webcomponents.ui5.configkeys.AvatarMode
import be.doeraene.webcomponents.ui5.configkeys.AvatarShape
import be.doeraene.webcomponents.ui5.configkeys.AvatarSize
import be.doeraene.webcomponents.ui5.configkeys.IconName
import be.doeraene.webcomponents.ui5.datatypes.AccessibilityAttributes
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** An image-like component that has different display options for representing images and icons in different shapes and
  * sizes, depending on the use case. The shape can be circular or square. There are several predefined sizes, as well
  * as an option to set a custom size.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/Avatar/">the doc</a> for more information.
  */
object Avatar extends WebComponent with HasIcon {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/Avatar.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-avatar")

  lazy val accessibleName: HtmlAttr[String]         = htmlAttr("accessible-name", StringAsIsCodec)
  lazy val colorScheme: HtmlAttr[AvatarColorScheme] = htmlAttr("color-scheme", AvatarColorScheme.AsStringCodec)
  lazy val disabled: HtmlAttr[Boolean]              = htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val fallbackIcon: HtmlAttr[IconName]         = htmlAttr("fallback-icon", IconName.AsStringCodec)
  lazy val initials: HtmlAttr[AvatarInitials]       = htmlAttr("initials", AvatarInitials.AsStringCodec)
  lazy val shape: HtmlAttr[AvatarShape]             = htmlAttr("shape", AvatarShape.AsStringCodec)
  lazy val size: HtmlAttr[AvatarSize]               = htmlAttr("size", AvatarSize.AsStringCodec)

  /** Mode of the component: `Image` (default, role="img"), `Decorative` (purely decorative, hidden from assistive
    * technologies) or `Interactive` (role="button", focusable and fires `click`).
    *
    * @since UI5 2.20.0
    */
  lazy val mode: HtmlAttr[AvatarMode] = AvatarMode.asHtmlAttr("mode")

  @deprecated(
    "Set `mode=\"Interactive\"` instead for the same functionality with proper accessibility. (Use `_.mode := AvatarMode.Interactive`)",
    since = "2.20.0"
  )
  lazy val interactive: HtmlAttr[Boolean] = htmlAttr("interactive", BooleanAsAttrPresenceCodec)

  /** Additional accessibility attributes. Supported fields: `hasPopup`. */
  lazy val accessibilityAttributes: HtmlProp[AccessibilityAttributes, AccessibilityAttributes] =
    AccessibilityAttributes.asHtmlProp

  @scala.annotation.compileTimeOnly("raised property of Avatar has been removed.")
  def raised: HtmlAttr[Boolean] = ???

  object slots {

    /** Badge displayed on top of the avatar (use an [[AvatarBadge]]). */
    val badge: Slot = new Slot("badge")
  }

  object events {

    /** Fired when the avatar is clicked (only when `mode` is `Interactive`). */
    val onClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("click")
  }

}
