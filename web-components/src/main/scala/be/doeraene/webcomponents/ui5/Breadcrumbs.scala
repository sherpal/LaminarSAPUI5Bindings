package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{BreadcrumbsDesign, BreadcrumbsSeparatorStyle}
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.{HtmlAttr}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.eventtypes.HasItem
import be.doeraene.webcomponents.WebComponent

/** Breadcrumbs menu for navigation.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Breadcrumbs/">the doc</a> for more
  *   information.
  */
object Breadcrumbs extends WebComponent with HasIcon {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/Breadcrumbs.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  opaque type Breadcrumbs = HtmlElement
  object Breadcrumbs {
    given Conversion[Breadcrumbs, HtmlElement] with {
      def apply(x: Breadcrumbs): HtmlElement = x
    }
  }

  protected val tag: HtmlTag[Ref] = htmlTag("ui5-breadcrumbs")

  lazy val separatorStyle: HtmlAttr[BreadcrumbsSeparatorStyle] =
    htmlAttr("separator-style", BreadcrumbsSeparatorStyle.AsStringCodec)

  lazy val design: HtmlAttr[BreadcrumbsDesign] =
    htmlAttr("design", BreadcrumbsDesign.AsStringCodec)

  object slots {}

  object events {
    val onItemClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasItem[Item.Ref]]] = new EventProp("item-click")
  }

  def Item: BreadcrumbsItem.type = BreadcrumbsItem

}
