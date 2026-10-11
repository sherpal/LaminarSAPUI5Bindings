package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.{
  HeroBannerActionsPlacement,
  HeroBannerColumnsRatio,
  HeroBannerHeaderBlockPlacement
}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-hero-banner is a flexible, full-width banner designed for placement at the top of a page. It provides a
  * personalized greeting (overline + header) and quick access to key information or actions, in up to two free
  * content blocks (the children, and the `endContent` slot) and an `actions` slot.
  *
  * The hero banner itself is non-interactive, but interactive elements (buttons, cards, search fields...) can be put
  * in its slots.
  *
  * Note: this component is experimental in UI5 and its API may change.
  *
  * @since UI5 2.23.0
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/HeroBanner/">the doc</a> for more information.
  */
object HeroBanner extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/HeroBanner.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-hero-banner")

  /** Main greeting header, e.g. "Hello, John". */
  lazy val headerText: HtmlAttr[String] = htmlAttr("header-text", StringAsIsCodec)

  /** Text displayed above the header, e.g. the current date or a status message. */
  lazy val overlineText: HtmlAttr[String] = htmlAttr("overline-text", StringAsIsCodec)

  /** Ratio between the two content columns. Only takes effect when the `endContent` slot is used. */
  lazy val columnsRatio: HtmlAttr[HeroBannerColumnsRatio] = HeroBannerColumnsRatio.asHtmlAttr("columns-ratio")

  /** Placement of the `actions` slot within the header area. */
  lazy val actionsPlacement: HtmlAttr[HeroBannerActionsPlacement] =
    HeroBannerActionsPlacement.asHtmlAttr("actions-placement")

  /** Vertical placement of the header block. `Bottom` only takes effect when `columnsRatio` is set and only the
    * `endContent` slot is used (no children).
    */
  lazy val headerBlockPlacement: HtmlAttr[HeroBannerHeaderBlockPlacement] =
    HeroBannerHeaderBlockPlacement.asHtmlAttr("header-block-placement")

  object slots {

    /** Second free content block, used alongside the children (the first block). */
    val endContent: Slot = Slot("endContent")

    /** Action buttons (or links, ...) displayed in the header area. */
    val actions: Slot = Slot("actions")
  }

  object events {}

}
