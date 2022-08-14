package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{AvatarColorScheme, AvatarInitials, AvatarShape, AvatarSize}
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** An image-like component that has different display options for representing images and icons in different shapes and
  * sizes, depending on the use case. The shape can be circular or square. There are several predefined sizes, as well
  * as an option to set a custom size.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Avatar/">the doc</a> for more information.
  */
object Avatar extends HasIcon {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/Avatar.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = Avatar.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-avatar")

  val id: ReactiveProp[String, String] = idAttr

  val accessibleName: ReactiveHtmlAttr[String] = customHtmlAttr("accessible-name", StringAsIsCodec)

  val interactive: ReactiveHtmlAttr[Boolean] = customHtmlAttr("interactive", BooleanAsAttrPresenceCodec)

  val raised: ReactiveHtmlAttr[Boolean] =
    customHtmlAttr("raised", BooleanAsAttrPresenceCodec)
  val disabled: ReactiveHtmlAttr[Boolean] =
    customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)

  val colorScheme: ReactiveHtmlAttr[AvatarColorScheme] =
    customHtmlAttr("color-scheme", AvatarColorScheme.AsStringCodec)

  val shape: ReactiveHtmlAttr[AvatarShape] = customHtmlAttr("shape", AvatarShape.AsStringCodec)

  val initials: ReactiveHtmlAttr[AvatarInitials] =
    customHtmlAttr("initials", AvatarInitials.AsStringCodec)

  val size: ReactiveHtmlAttr[AvatarSize] = customHtmlAttr("size", AvatarSize.AsStringCodec)

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(Avatar)): _*)

}
