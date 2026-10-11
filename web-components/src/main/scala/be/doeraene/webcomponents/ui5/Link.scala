package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.ButtonDesign
import be.doeraene.webcomponents.ui5.configkeys.EmbeddingAsIsCodec
import be.doeraene.webcomponents.ui5.configkeys.IconName
import be.doeraene.webcomponents.ui5.configkeys.InteractiveAreaSize
import be.doeraene.webcomponents.ui5.configkeys.LinkAccessibleRole
import be.doeraene.webcomponents.ui5.configkeys.LinkDesign
import be.doeraene.webcomponents.ui5.configkeys.LinkTarget
import be.doeraene.webcomponents.ui5.configkeys.WrappingType
import be.doeraene.webcomponents.ui5.datatypes.AccessibilityAttributes
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-link is a hyperlink component that is used to navigate to other apps and web pages, or to trigger actions.
  * It is a clickable text element, visualized in such a way that it stands out from the standard text. On hover, it
  * changes its style to an underlined text to provide additional feedback to the user.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/Link/">the doc</a> for more information.
  */
object Link extends WebComponent with HasAccessibleName {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/Link.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-link")

  lazy val disabled: HtmlAttr[Boolean] =
    htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val href: HtmlAttr[String]                  = htmlAttr("href", StringAsIsCodec)
  lazy val target: HtmlAttr[LinkTarget]            = htmlAttr("target", LinkTarget.AsStringCodec)
  lazy val tooltip: HtmlAttr[String]               = htmlAttr("tooltip", StringAsIsCodec)
  lazy val icon: HtmlAttr[IconName]                = htmlAttr("icon", IconName.AsStringCodec)
  lazy val endIcon: HtmlAttr[IconName]             = htmlAttr("end-icon", IconName.AsStringCodec)
  lazy val accessibleDescription: HtmlAttr[String] = htmlAttr("accessible-description", StringAsIsCodec)
  lazy val interactiveAreaSize: HtmlAttr[InteractiveAreaSize] =
    htmlAttr("interactive-area-size", InteractiveAreaSize.AsStringCodec)

  lazy val design: HtmlAttr[LinkDesign] =
    htmlAttr("design", LinkDesign.AsStringCodec)

  lazy val wrappingType: HtmlAttr[WrappingType] = htmlAttr("wrapping-type", WrappingType.AsStringCodec)

  /** ARIA role of the link (`Link` by default). Use `Button` when the link triggers an action rather than a navigation.
    */
  lazy val accessibleRole: HtmlAttr[LinkAccessibleRole] = LinkAccessibleRole.asHtmlAttr("accessible-role")

  /** Additional accessibility attributes. Supported fields: `expanded`, `hasPopup` and `current`. */
  lazy val accessibilityAttributes: HtmlProp[AccessibilityAttributes, AccessibilityAttributes] =
    AccessibilityAttributes.asHtmlProp

  object slots {}

  object events {
    trait ClickDetail extends js.Object {
      def altKey: Boolean
      def ctrlKey: Boolean
      def metaKey: Boolean
      def shiftKey: Boolean
    }

    /** Fired when the link is activated. Calling `preventDefault()` prevents the navigation to `href`. */
    val onClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ClickDetail]] = new EventProp("click")
  }

}
