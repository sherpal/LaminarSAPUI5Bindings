package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{SideContentFallDown, SideContentPosition, SideContentVisibility}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.{HtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.WebComponent

/** The DynamicSideContent (ui5-dynamic-side-content) is a layout component that allows additional content to be
  * displayed in a way that flexibly adapts to different screen sizes. The side content appears in a container next to
  * or directly below the main content (it doesn't overlay). When the side content is triggered, the main content
  * becomes narrower (if appearing side-by-side). The side content contains a separate scrollbar when appearing next to
  * the main content.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/DynamicSideContent/">the doc</a> for more
  *   information.
  */
object DynamicSideContent extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def toggleContents(): Unit = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/DynamicSideContent.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-dynamic-side-content")

  lazy val equalSplit: HtmlAttr[Boolean] = customHtmlAttr("equal-split", BooleanAsAttrPresenceCodec)

  lazy val hideMainContent: HtmlAttr[Boolean] = customHtmlAttr("hide-main-content", BooleanAsAttrPresenceCodec)

  lazy val hideSideContent: HtmlAttr[Boolean] = customHtmlAttr("hide-side-content", BooleanAsAttrPresenceCodec)

  lazy val sideContentFallDown: HtmlAttr[SideContentFallDown] =
    customHtmlAttr("side-content-fall-down", SideContentFallDown.AsStringCodec)

  lazy val sideContentPosition: HtmlAttr[SideContentPosition] =
    customHtmlAttr("side-content-position", SideContentPosition.AsStringCodec)

  lazy val sideContentVisibility: HtmlAttr[SideContentVisibility] =
    customHtmlAttr("side-content-visibility", SideContentVisibility.AsStringCodec)

  object slots {
    val sideContent: Slot = new Slot("sideContent")
  }

  object events {
    trait LayoutChangeInfo extends js.Object {
      def currentBreakpoint: String
      def previousBreakpoint: String
      def mainContentVisible: Boolean
      def sideContentVisible: Boolean
    }

    val onLayoutChange: EventProp[EventWithPreciseTarget[Ref] & HasDetail[LayoutChangeInfo]] = new EventProp(
      "layout-change"
    )
  }

  

}
