package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.LinkTarget
import be.doeraene.webcomponents.ui5.configkeys.SideNavigationItemDesign
import be.doeraene.webcomponents.ui5.datatypes.AccessibilityAttributes
import be.doeraene.webcomponents.ui5.eventtypes.{EventWithPreciseTarget, HasDetail}

/** The ui5-side-navigation-item is used within ui5-side-navigation only. Via the ui5-side-navigation-item you control
  * the content of the SideNavigation.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/SideNavigationItem/">the doc</a> for more
  *   information.
  */
object SideNavigationItem extends WebComponent with HasText {

  @js.native
  trait RawElement extends SideNavigation.events.SideNavigationItemRawElement {
    def expanded: Boolean = js.native
    def selected: Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/SideNavigationItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-side-navigation-item")

  lazy val expanded: HtmlAttr[Boolean] = htmlAttr("expanded", BooleanAsAttrPresenceCodec)

  lazy val icon: HtmlAttr[IconName] = htmlAttr("icon", IconName.AsStringCodec)

  lazy val selected: HtmlAttr[Boolean] = htmlAttr("selected", BooleanAsAttrPresenceCodec)

  lazy val href: HtmlAttr[String] = htmlAttr("href", StringAsIsCodec)

  lazy val target: HtmlAttr[LinkTarget] = htmlAttr("target", LinkTarget.AsStringCodec)

  /** Whether the item is interactive. Disabled items cannot be selected or clicked. */
  lazy val disabled: HtmlAttr[Boolean] = htmlAttr("disabled", BooleanAsAttrPresenceCodec)

  lazy val tooltip: HtmlAttr[String] = htmlAttr("tooltip", StringAsIsCodec)

  /** @since UI5 2.22.0 */
  lazy val accessibleName: HtmlAttr[String] = htmlAttr("accessible-name", StringAsIsCodec)

  /** Design of the item. Use `Action` for items that trigger an action (e.g. opening a dialog) rather than navigating.
    * Items with design `Action` must not have sub-items.
    *
    * @since UI5 2.7.0
    */
  lazy val design: HtmlAttr[SideNavigationItemDesign] = SideNavigationItemDesign.asHtmlAttr("design")

  /** Whether the item is not selectable. An unselectable parent item only expands/collapses its sub-items when
    * clicked. Items with `href` and target `_blank`, and items with design `Action`, should be unselectable.
    *
    * @since UI5 2.7.0
    */
  lazy val unselectable: HtmlAttr[Boolean] = htmlAttr("unselectable", BooleanAsAttrPresenceCodec)

  /** Additional accessibility attributes. Supported field: `hasPopup` (e.g. `AriaHasPopup.Dialog` for an item opening
    * a dialog).
    *
    * @since UI5 2.7.0
    */
  lazy val accessibilityAttributes: HtmlProp[AccessibilityAttributes, AccessibilityAttributes] =
    AccessibilityAttributes.asHtmlProp

  @scala.annotation.compileTimeOnly(
    "The wholeItemToggleable property is now removed. The functionality of clicking the whole item to show/hide the sub-items is no longer available. The collapsing/expanding of the item can still be done by pressing the icon."
  )
  def wholeItemToggleable: HtmlAttr[Boolean] = ???

  object slots {

    /** A single [[Tag]] displayed next to the text (visible in expanded mode). The tag should use the `Set2` design,
      * `hideStateIcon`, colour schemes 5 to 10, and must not be interactive.
      *
      * @since UI5 2.23.0
      */
    val tag: Slot = Slot("tag")
  }

  object events {

    trait ClickDetail extends js.Object {
      def altKey: Boolean
      def ctrlKey: Boolean
      def metaKey: Boolean
      def shiftKey: Boolean
    }

    /** Fired when the item is activated, either with a click/tap or with the Enter or Space keys. */
    val onClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ClickDetail]] = new EventProp("click")
  }

  def subItem: SideNavigationSubItem.type = SideNavigationSubItem

}
