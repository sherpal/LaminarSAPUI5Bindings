package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-side-navigation-item is used within ui5-side-navigation only. Via the ui5-side-navigation-item you control
  * the content of the SideNavigation.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/SideNavigationItem/">the doc</a> for more
  *   information.
  */
object SideNavigationItem extends HasText {

  @js.native
  trait RawElement extends SideNavigation.events.SideNavigationItemRawElement {}

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/SideNavigationItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = SideNavigationItem.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-side-navigation-item")

  val id: ReactiveProp[String, String] = idAttr

  val expanded: ReactiveHtmlAttr[Boolean] = customHtmlAttr("expanded", BooleanAsAttrPresenceCodec)

  val icon: ReactiveHtmlAttr[IconName] = customHtmlAttr("icon", IconName.AsStringCodec)

  val selected: ReactiveHtmlAttr[Boolean] = customHtmlAttr("selected", BooleanAsAttrPresenceCodec)

  val wholeItemToggleable: ReactiveHtmlAttr[Boolean] =
    customHtmlAttr("whole-item-toggleable", BooleanAsAttrPresenceCodec)

  object slots {}

  object events {}

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(SideNavigationItem)): _*)

  def subItem: SideNavigationSubItem.type = SideNavigationSubItem

}
