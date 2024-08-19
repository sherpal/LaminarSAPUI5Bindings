package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.FCLLayout
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
import be.doeraene.webcomponents.ui5.eventtypes.{EventWithPreciseTarget, HasDetail}

/** The FlexibleColumnLayout implements the list-detail-detail paradigm by displaying up to three pages in separate
  * columns. There are several possible layouts that can be changed either with the component API, or by dragging the
  * column separators.
  */
object FlexibleColumnLayout extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def columnLayout: js.Array[String | Int] = js.native

    def endColumnVisible: Boolean = js.native

    def disableResizing: Boolean = js.native

    def layout: FCLLayout.StringFCLLayout = js.native

    def midColumnVisible: Boolean = js.native

    def startColumnVisible: Boolean = js.native

    def visibleColumns: Int = js.native
  }

  object RawElement {
    extension (element: RawElement) {
      def layoutADT: FCLLayout = FCLLayout.AsStringCodec.decode(element.layout)

      @deprecated("hideArrows property has been renamed to disableResizing", since = "2.0.0")
      def hideArrows: Boolean = element.disableResizing
    }
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/FlexibleColumnLayout.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-flexible-column-layout")

  lazy val disableResizing: HtmlAttr[Boolean] = htmlAttr("disable-resizing", BooleanAsAttrPresenceCodec)

  lazy val layout: HtmlAttr[FCLLayout] = htmlAttr("layout", FCLLayout.AsStringCodec)

  @deprecated("hideArrows property has been renamed to disableResizing", since = "2.0.0")
  def hideArrows: HtmlAttr[Boolean] = disableResizing

  @deprecated("onLayoutChanged has been moved within the events object")
  def onLayoutChanged = events.onLayoutChanged

  object events {
    trait LayoutChangedDetail extends js.Object {
      def separatorsUsed: Boolean

      def resized: Boolean
    }

    val onLayoutChanged = new EventProp[EventWithPreciseTarget[Ref] & HasDetail[LayoutChangedDetail]]("layout-change")
  }

  object slots {
    val endColumn: Slot   = new Slot("endColumn")
    val midColumn: Slot   = new Slot("midColumn")
    val startColumn: Slot = new Slot("startColumn")
  }

}
