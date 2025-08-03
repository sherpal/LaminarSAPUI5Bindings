package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.ButtonDesign
import be.doeraene.webcomponents.ui5.configkeys.ColourScheme
import be.doeraene.webcomponents.ui5.configkeys.IconName
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
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/SideNavigation/">the doc</a> for more
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

    @js.native
    trait SideNavigationItemRawElement extends js.Object {
      def text: String = js.native
    }

    val onSelectionChange: EventProp[dom.Event & HasDetail[HasItem[SideNavigationItemRawElement]]] = new EventProp(
      "selection-change"
    )

  }

  def item: SideNavigationItem.type   = SideNavigationItem
  def group: SideNavigationGroup.type = SideNavigationGroup

}
