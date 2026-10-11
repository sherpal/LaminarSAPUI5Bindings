package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{BreadcrumbsDesign, BreadcrumbsSeparatorStyle}
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
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
  *   <a href="https://ui5.github.io/webcomponents/components/Breadcrumbs/">the doc</a> for more information.
  */
object Breadcrumbs extends WebComponent with HasIcon {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/Breadcrumbs.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  opaque type Breadcrumbs = HtmlElement
  object Breadcrumbs {
    given Conversion[Breadcrumbs, HtmlElement] with {
      def apply(x: Breadcrumbs): HtmlElement = x
    }
  }

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-breadcrumbs")

  @deprecated("separatorStyle has been moved to separators", since = "2.0.0")
  def separatorStyle: HtmlAttr[BreadcrumbsSeparatorStyle] = separators

  lazy val separators: HtmlAttr[BreadcrumbsSeparatorStyle] = BreadcrumbsSeparatorStyle.asHtmlAttr("separators")

  lazy val design: HtmlAttr[BreadcrumbsDesign] =
    htmlAttr("design", BreadcrumbsDesign.AsStringCodec)

  /** Accessible name of the component.
    *
    * @since UI5 2.24.0
    */
  lazy val accessibleName: HtmlAttr[String] = htmlAttr("accessible-name", StringAsIsCodec)

  object slots {}

  object events {
    trait ItemClickDetail extends HasItem[Item.Ref] {
      def altKey: Boolean
      def ctrlKey: Boolean
      def metaKey: Boolean
      def shiftKey: Boolean
    }

    /** Fired when a [[BreadcrumbsItem]] is clicked. Calling `preventDefault()` prevents the navigation to the `href` of
      * the item.
      */
    val onItemClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ItemClickDetail]] = new EventProp(
      "item-click"
    )
  }

  def Item: BreadcrumbsItem.type = BreadcrumbsItem

  inline def item = Item

}
