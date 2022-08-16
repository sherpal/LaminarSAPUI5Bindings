package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.FCLLayout
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** The FlexibleColumnLayout implements the master-detail-detail paradigm by displaying up to three pages in separate
  * columns.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/FlexibleColumnLayout /">the doc</a> for
  *   more information.
  */
object FlexibleColumnLayout extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def columnLayout: js.Array[String | Int] = js.native

    def endColumnVisible: Boolean = js.native

    def hideArrows: Boolean = js.native

    def layout: FCLLayout.StringFCLLayout = js.native

    def midColumnVisible: Boolean = js.native

    def startColumnVisible: Boolean = js.native

    def visibleColumns: Int = js.native
  }

  object RawElement {
    extension (element: RawElement) def layoutADT: FCLLayout = FCLLayout.AsStringCodec.decode(element.layout)
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/FlexibleColumnLayout.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = FlexibleColumnLayout.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-flexible-column-layout")

  val hideArrows: ReactiveHtmlAttr[Boolean] = customHtmlAttr("hide-arrows", BooleanAsAttrPresenceCodec)

  val layout: ReactiveHtmlAttr[FCLLayout] = customHtmlAttr("layout", FCLLayout.AsStringCodec)

  val onLayoutChanged = new EventProp[dom.Event]("layout-change")

  object slots {
    val endColumn: Slot   = new Slot("endColumn")
    val midColumn: Slot   = new Slot("midColumn")
    val startColumn: Slot = new Slot("startColumn")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(FlexibleColumnLayout)): _*)

}
