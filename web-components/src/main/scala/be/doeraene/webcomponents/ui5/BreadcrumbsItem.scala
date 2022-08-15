package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, IconName, LinkTarget}
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** Simple UI button
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/BreadcrumbsItem/">the doc</a> for more
  *   information.
  */
object BreadcrumbsItem extends HasIcon {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/BreadcrumbsItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = BreadcrumbsItem.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-breadcrumbs-item")

  val id: ReactiveProp[String, String] = idAttr

  val accessibleName: ReactiveHtmlAttr[String] =
    customHtmlAttr("accessible-name", StringAsIsCodec)

  val href: ReactiveHtmlAttr[String] = customHtmlAttr("href", StringAsIsCodec)

  val target: ReactiveHtmlAttr[LinkTarget] = customHtmlAttr("target", LinkTarget.AsStringCodec)

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(BreadcrumbsItem)): _*)

}
