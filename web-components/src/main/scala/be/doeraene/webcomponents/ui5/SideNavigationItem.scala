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

/** The ui5-side-navigation-item is used within ui5-side-navigation only. Via the ui5-side-navigation-item you control
  * the content of the SideNavigation.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/SideNavigationItem/">the doc</a> for more
  *   information.
  */
object SideNavigationItem extends WebComponent with HasText {

  @js.native
  trait RawElement extends SideNavigation.events.SideNavigationItemRawElement {}

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

  lazy val wholeItemToggleable: HtmlAttr[Boolean] =
    htmlAttr("whole-item-toggleable", BooleanAsAttrPresenceCodec)

  lazy val href: HtmlAttr[String] = htmlAttr("href", StringAsIsCodec)

  lazy val target: HtmlAttr[LinkTarget] = htmlAttr("target", LinkTarget.AsStringCodec)

  object slots {}

  object events {}

  def subItem: SideNavigationSubItem.type = SideNavigationSubItem

}
