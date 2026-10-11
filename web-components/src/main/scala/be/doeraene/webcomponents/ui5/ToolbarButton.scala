package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.{ButtonAccessibleRole, ButtonDesign, IconName}
import be.doeraene.webcomponents.ui5.datatypes.AccessibilityAttributes
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.scaladsl.csssize.CSSSize
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-toolbar-button represents an abstract action, used in the [[Toolbar]].
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/ToolbarButton/">the doc</a> for more information.
  */
object ToolbarButton extends WebComponent with HasIcon with HasText with HasAccessibleName with HasToolbarOverflow {

  @js.native
  trait RawElement extends js.Object

  @js.native
  @JSImport("@ui5/webcomponents/dist/ToolbarButton.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-toolbar-button")

  lazy val design: HtmlAttr[ButtonDesign] = htmlAttr("design", ButtonDesign.AsStringCodec)
  lazy val disabled: HtmlAttr[Boolean]    = htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val endIcon: HtmlAttr[IconName]    = htmlAttr("end-icon", IconName.AsStringCodec)
  lazy val tooltip: HtmlAttr[String]      = htmlAttr("tooltip", StringAsIsCodec)
  lazy val widthJS: HtmlAttr[String]      = htmlAttr("width", StringAsIsCodec)
  lazy val width: HtmlAttr[CSSSize]       = htmlAttr("width", CSSSize.AsStringCodec)

  /** When true, the button is icon-only in the toolbar, and its text is only shown in the overflow popover (only
    * meaningful when `text` is set).
    *
    * @since UI5 2.17.0
    */
  lazy val showOverflowText: HtmlAttr[Boolean] = htmlAttr("show-overflow-text", BooleanAsAttrPresenceCodec)

  /** @since UI5 2.27.0 */
  lazy val accessibleDescription: HtmlAttr[String] = htmlAttr("accessible-description", StringAsIsCodec)

  /** Accessible role of the button (Button or Link).
    *
    * @since UI5 2.27.0
    */
  lazy val accessibleRole: HtmlAttr[ButtonAccessibleRole] = ButtonAccessibleRole.asHtmlAttr("accessible-role")

  /** Additional accessibility attributes. Supported fields: `expanded`, `hasPopup`, `controls`, `ariaKeyShortcuts` and
    * `ariaLabel`.
    */
  lazy val accessibilityAttributes: HtmlProp[AccessibilityAttributes, AccessibilityAttributes] =
    AccessibilityAttributes.asHtmlProp

  @scala.annotation.compileTimeOnly("iconEnd property has been removed in favour of the end-icon property")
  def iconEnd: HtmlAttr[Boolean] = ???

  object slots {}

  object events {
    lazy val onClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("click")
  }

}
