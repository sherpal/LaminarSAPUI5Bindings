package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{FCLLayout, FCLMedia}
import be.doeraene.webcomponents.ui5.datatypes.{FCLAccessibilityAttributes, FCLLayoutConfiguration}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{AsIsCodec, BooleanAsAttrPresenceCodec, StringAsIsCodec}
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
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/FlexibleColumnLayout/">the doc</a> for more
  *   information.
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

    /** Current custom column proportions (the component updates it when the user drags the separators). */
    def layoutsConfiguration: js.Object = js.native
  }

  object RawElement {
    extension (element: RawElement) {
      def layoutADT: FCLLayout = FCLLayout.AsStringCodec.decode(element.layout)

      /** The current `layoutsConfiguration`, decoded as a [[FCLLayoutConfiguration]]. */
      def layoutsConfigurationADT: FCLLayoutConfiguration = FCLLayoutConfiguration.fromJS(element.layoutsConfiguration)

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

  /** Custom column proportions per screen size and layout.
    *
    * Note that when the user resizes the columns by dragging a separator, UI5 updates this property with the new
    * proportions and fires [[events.onLayoutConfigurationChange]].
    *
    * @since UI5 2.16.0
    */
  lazy val layoutsConfiguration: HtmlProp[FCLLayoutConfiguration, js.Object] = FCLLayoutConfiguration.asHtmlProp

  /** Additional accessibility attributes on the different areas of the component.
    *
    * Supported fields: `startColumn`, `midColumn`, `endColumn`, `startSeparator` and `endSeparator`, each supporting
    * `role` (one of "none", "complementary", "contentinfo", "main" or "region") and `name`.
    */
  lazy val accessibilityAttributes: HtmlProp[FCLAccessibilityAttributes, FCLAccessibilityAttributes] =
    htmlProp("accessibilityAttributes", AsIsCodec())

  @deprecated("hideArrows property has been renamed to disableResizing", since = "2.0.0")
  def hideArrows: HtmlAttr[Boolean] = disableResizing

  @deprecated("onLayoutChanged has been moved within the events object")
  def onLayoutChanged = events.onLayoutChanged

  object events {
    trait LayoutChangedDetail extends js.Object {
      def separatorsUsed: Boolean

      def resized: Boolean

      def layout: FCLLayout.StringFCLLayout

      /** Effective column widths (start, mid, end), e.g. `["67%", "33%", 0]`. */
      def columnLayout: js.Array[String | Int]

      def startColumnVisible: Boolean

      def midColumnVisible: Boolean

      def endColumnVisible: Boolean
    }

    object LayoutChangedDetail {
      extension (detail: LayoutChangedDetail) {
        def layoutADT: FCLLayout = FCLLayout.AsStringCodec.decode(detail.layout)

        def columnLayoutList: List[String] = detail.columnLayout.toList.map(FCLLayoutConfiguration.columnWidthFromJS)
      }
    }

    /** Fired when the layout changes via user interaction by dragging the separators or by changing the component
      * size due to resizing.
      */
    val onLayoutChanged = new EventProp[EventWithPreciseTarget[Ref] & HasDetail[LayoutChangedDetail]]("layout-change")

    trait LayoutConfigurationChangeDetail extends js.Object {
      def layout: FCLLayout.StringFCLLayout

      def media: FCLMedia.StringFCLMedia

      /** New column widths (start, mid, end) chosen by the user. */
      def columnLayout: js.Array[String | Int]
    }

    object LayoutConfigurationChangeDetail {
      extension (detail: LayoutConfigurationChangeDetail) {
        def layoutADT: FCLLayout = FCLLayout.AsStringCodec.decode(detail.layout)

        def mediaADT: FCLMedia = FCLMedia.AsStringCodec.decode(detail.media)

        def columnLayoutList: List[String] = detail.columnLayout.toList.map(FCLLayoutConfiguration.columnWidthFromJS)
      }
    }

    /** Fired when the `layoutsConfiguration` changes via user interaction by dragging the separators.
      *
      * Note: the component has already updated its `layoutsConfiguration` property in place when this event fires.
      *
      * @since UI5 2.16.0
      */
    val onLayoutConfigurationChange
        : EventProp[EventWithPreciseTarget[Ref] & HasDetail[LayoutConfigurationChangeDetail]] =
      new EventProp("layout-configuration-change")
  }

  object slots {
    val endColumn: Slot   = new Slot("endColumn")
    val midColumn: Slot   = new Slot("midColumn")
    val startColumn: Slot = new Slot("startColumn")
  }

}
