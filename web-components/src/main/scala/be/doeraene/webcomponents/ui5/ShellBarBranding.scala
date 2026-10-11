package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.LinkTarget
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-shellbar-branding component is intended to be placed inside the `branding` slot of the [[ShellBar]]. Its
  * content has higher priority than the `primaryTitle` property and the `logo` slot of the [[ShellBar]].
  *
  * The default slot (children) is the title of the branding, and the `logo` slot takes the logo.
  *
  * Note: this component is experimental in UI5 and its API may change.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/ShellBarBranding/">the doc</a> for more
  *   information.
  */
object ShellBarBranding extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/ShellBarBranding.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-shellbar-branding")

  lazy val accessibleName: HtmlAttr[String] = htmlAttr("accessible-name", StringAsIsCodec)

  /** Defines the component href (standard hyperlink behaviour is supported). */
  lazy val href: HtmlAttr[String] = htmlAttr("href", StringAsIsCodec)

  /** Defines the component target. Only used when `href` is set. */
  lazy val target: HtmlAttr[LinkTarget] = htmlAttr("target", LinkTarget.AsStringCodec)

  object slots {
    val logo: Slot = Slot("logo")
  }

  object events {
    val onClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("click")
  }

}
