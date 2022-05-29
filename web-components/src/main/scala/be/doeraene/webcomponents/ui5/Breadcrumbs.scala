package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{BreadcrumbsDesign, BreadcrumbsSeparatorStyle}
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** Breadcrumbs menu for navigation.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Breadcrumbs/">the doc</a> for more
  *   information.
  */
object Breadcrumbs extends HasIcon with HasOnClick {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/Breadcrumbs.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = this.type => Mod[ReactiveHtmlElement[Ref]]

  opaque type Breadcrumbs = HtmlElement
  object Breadcrumbs {
    given Conversion[Breadcrumbs, HtmlElement] with {
      def apply(x: Breadcrumbs): HtmlElement = x
    }
  }

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-breadcrumbs")

  val id: ReactiveProp[String, String] = idAttr

  val separatorStyle: ReactiveHtmlAttr[BreadcrumbsSeparatorStyle] =
    customHtmlAttr("separator-style", BreadcrumbsSeparatorStyle.AsStringCodec)

  val design: ReactiveHtmlAttr[BreadcrumbsDesign] =
    customHtmlAttr("design", BreadcrumbsDesign.AsStringCodec)

  def apply(mods: ModFunction*): Breadcrumbs = tag(mods.map(_(this)): _*)

  def Item: BreadcrumbsItem.type = BreadcrumbsItem

}
