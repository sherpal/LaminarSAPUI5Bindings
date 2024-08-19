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

/** A layout component, representing a web page, consisting of a title, header with dynamic behavior, a content area,
  * and an optional floating footer.
  */
object DynamicPage extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/DynamicPage.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-dynamic-page")

  lazy val hidePinButton: HtmlAttr[Boolean] = htmlAttr("hide-pin-button", BooleanAsAttrPresenceCodec)
  lazy val headerPinned: HtmlAttr[Boolean]  = htmlAttr("header-pinned", BooleanAsAttrPresenceCodec)
  lazy val showFooter: HtmlAttr[Boolean]    = htmlAttr("show-footer", BooleanAsAttrPresenceCodec)
  lazy val headerSnapped: HtmlAttr[Boolean] = htmlAttr("header-snapped", BooleanAsAttrPresenceCodec)

  object slots {

    /** Should be a [[DynamicPageTitle]] */
    val titleArea: Slot = Slot("titleArea")

    /** Should be a [[DynamicPageHeader]] */
    val headerArea: Slot = Slot("headerArea")

    /** Will typically be a [[Bar]] */
    val footerArea: Slot = Slot("footerArea")
  }

  object events {
    val onPinButtonToggle: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("pin-button-toggle")
    val onTitleToggle: EventProp[EventWithPreciseTarget[Ref]]     = new EventProp("title-toggle")
  }

}
