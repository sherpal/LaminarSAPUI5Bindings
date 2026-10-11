package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.ColourScheme
import be.doeraene.webcomponents.ui5.configkeys.ValueState
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-avatar-badge component is used to display a badge (an icon) on top of a ui5-avatar component. It must be put
  * in the `badge` slot of the [[Avatar]].
  *
  * Example:
  * {{{
  *   Avatar(_.initials := ('J', 'D'), _.slots.badge := AvatarBadge(_.icon := IconName.edit))
  * }}}
  *
  * @since UI5 2.19.0
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/AvatarBadge/">the doc</a> for more information.
  */
object AvatarBadge extends WebComponent with HasIcon {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/AvatarBadge.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-avatar-badge")

  /** State of the badge, determining its styling. Takes precedence over `colourScheme` when not `None`. */
  lazy val state: HtmlAttr[ValueState] = htmlAttr("state", ValueState.AsStringCodec)

  /** Tooltip of the badge. If not provided, the accessible name of the icon is used.
    *
    * @since UI5 2.22.0
    */
  lazy val tooltip: HtmlAttr[String] = htmlAttr("tooltip", StringAsIsCodec)

  /** Colour scheme of the badge, using the indication colour palette ("1" to "10"). Ignored when `state` is not `None`.
    *
    * @since UI5 2.27.0
    */
  lazy val colourScheme: HtmlAttr[ColourScheme] = ColourScheme.asHtmlAttr("color-scheme")

  object slots {}

  object events {}

}
