package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, IconName, LinkTarget}
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** The ui5-breadcrumbs-item component defines the content of an item in ui5-breadcumbs.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/BreadcrumbsItem/">the doc</a> for more
  *   information.
  */
object BreadcrumbsItem extends WebComponent with HasIcon {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/BreadcrumbsItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-breadcrumbs-item")

  lazy val accessibleName: HtmlAttr[String] =
    htmlAttr("accessible-name", StringAsIsCodec)

  lazy val href: HtmlAttr[String] = htmlAttr("href", StringAsIsCodec)

  lazy val target: HtmlAttr[LinkTarget] = htmlAttr("target", LinkTarget.AsStringCodec)

}
