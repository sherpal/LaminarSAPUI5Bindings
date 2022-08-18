package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.eventtypes.{HasDetail, HasItem}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

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

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-side-navigation")

  lazy val collapsed: ReactiveHtmlAttr[Boolean] = customHtmlAttr[Boolean]("collapsed", BooleanAsAttrPresenceCodec)

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

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(SideNavigation)): _*)

  def item: SideNavigationItem.type = SideNavigationItem

}
