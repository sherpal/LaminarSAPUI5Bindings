package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.ButtonDesign
import be.doeraene.webcomponents.ui5.configkeys.ColourScheme
import be.doeraene.webcomponents.ui5.configkeys.IconName
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.eventtypes.HasItem
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

/** The SideNavigation is used as a standard menu in applications. It consists of three containers: header
  * (top-aligned), main navigation section (top-aligned) and the secondary section (bottom-aligned).
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/SideNavigation/">the doc</a> for more
  *   information.
  */
object SideNavigation extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/SideNavigation.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-side-navigation")

  lazy val collapsed: HtmlAttr[Boolean]     = htmlAttr[Boolean]("collapsed", BooleanAsAttrPresenceCodec)
  lazy val accessibleName: HtmlAttr[String] = htmlAttr[String]("accessible-name", StringAsIsCodec)

  object slots {
    val fixedItems: Slot = new Slot("fixedItems")
    val header: Slot     = new Slot("header")
  }

  object events {

    /** Common raw element of the side navigation items, sub-items and groups. */
    @js.native
    trait SideNavigationItemRawElement extends js.Object {
      def text: String = js.native
    }

    /** Fired when the selection has changed via user interaction. The detail item is the selected item. */
    val onSelectionChange: EventProp[dom.Event & HasDetail[HasItem[SideNavigationItemRawElement]]] = new EventProp(
      "selection-change"
    )

    /** Fired when an item (or sub-item) is clicked. The detail item is the clicked item.
      *
      * @since UI5 2.20.0
      */
    val onItemClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasItem[SideNavigationItemRawElement]]] =
      new EventProp("item-click")

    /** Fired when a [[SideNavigationItem]] or a [[SideNavigationGroup]] is expanded or collapsed. The detail item is
      * the toggled item.
      *
      * This event is cancelable: calling `preventDefault()` keeps the `expanded` state unchanged (handy e.g. to
      * dynamically load child items before allowing a parent item to expand).
      *
      * @since UI5 2.26.0
      */
    val onItemToggle: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasItem[SideNavigationItemRawElement]]] =
      new EventProp("item-toggle")

  }

  def item: SideNavigationItem.type   = SideNavigationItem
  def group: SideNavigationGroup.type = SideNavigationGroup

}
