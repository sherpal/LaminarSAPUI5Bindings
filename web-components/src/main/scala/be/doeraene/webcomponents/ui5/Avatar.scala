package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{AvatarColorScheme, AvatarInitials, AvatarShape, AvatarSize, IconName}
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
//import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.tags.CustomHtmlTag

/** An image-like component that has different display options for representing images and icons in different shapes and
  * sizes, depending on the use case. The shape can be circular or square. There are several predefined sizes, as well
  * as an option to set a custom size.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Avatar/">the doc</a> for more information.
  */
object Avatar extends WebComponent with HasIcon {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/Avatar.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-avatar")

  lazy val accessibleName: HtmlAttr[String]         = htmlAttr("accessible-name", StringAsIsCodec)
  lazy val colorScheme: HtmlAttr[AvatarColorScheme] = htmlAttr("color-scheme", AvatarColorScheme.AsStringCodec)
  lazy val disabled: HtmlAttr[Boolean]              = htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val fallbackIcon: HtmlAttr[IconName]         = htmlAttr("fallback-icon", IconName.AsStringCodec)
  lazy val initials: HtmlAttr[AvatarInitials]       = htmlAttr("initials", AvatarInitials.AsStringCodec)
  lazy val interactive: HtmlAttr[Boolean]           = htmlAttr("interactive", BooleanAsAttrPresenceCodec)
  lazy val raised: HtmlAttr[Boolean]                = htmlAttr("raised", BooleanAsAttrPresenceCodec)
  lazy val shape: HtmlAttr[AvatarShape]             = htmlAttr("shape", AvatarShape.AsStringCodec)
  lazy val size: HtmlAttr[AvatarSize]               = htmlAttr("size", AvatarSize.AsStringCodec)

  object slots {
    val badge: Slot = new Slot("badge")
  }

}
