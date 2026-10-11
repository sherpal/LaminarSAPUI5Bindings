package be.doeraene.webcomponents.ui5

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
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget

/** Title of the DynamicPage.
  *
  * The DynamicPageTitle component is part of the DynamicPage family and is used to serve as title of the DynamicPage.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/DynamicPageTitle/">the doc</a> for more
  *   information.
  */
object DynamicPageTitle extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/DynamicPageTitle.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-dynamic-page-title")

  object slots {
    val heading: Slot        = Slot("heading")
    val snappedHeading: Slot = Slot("snappedHeading")
    val actionsBar: Slot     = Slot("actionsBar")

    /** Should be a [[Toolbar]] */
    val navigationBar: Slot     = Slot("navigationBar")
    val subheading: Slot        = Slot("subheading")
    val snappedSubheading: Slot = Slot("snappedSubheading")
    val breadcrumbs: Slot       = Slot("breadcrumbs")

    /** Title displayed instead of the heading when the header is snapped on mobile devices. Should be a [[Title]].
      *
      * @since UI5 2.3.0
      */
    val snappedTitleOnMobile: Slot = Slot("snappedTitleOnMobile")
  }

  object events {}

}
