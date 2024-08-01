package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName, SegmentedButtonMode}
import be.doeraene.webcomponents.ui5.eventtypes.{HasDetail, HasSelectedItem, HasSelectedItems}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, JSName}
import be.doeraene.webcomponents.WebComponent

/** The ui5-segmented-button shows a group of items. When the user clicks or taps one of the items, it stays in a
  * pressed state. It automatically resizes the items to fit proportionally within the component. When no width is set,
  * the component uses the available width.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/SegmentedButton/">the doc</a> for more
  *   information.
  */
object SegmentedButton extends WebComponent {

  @js.native
  trait RawElement extends js.Object {

    @JSName("selectedItems")
    def selectedItemsJS: js.Array[SegmentedButtonItem.Ref] = js.native
  }

  object RawElement {
    extension (elem: RawElement) {
      def selectedItems: Vector[SegmentedButtonItem.Ref] = elem.selectedItemsJS.toVector

      @deprecated("selectedItem has been deprecated and you should use selectedItems(0) instead", since = "2.0.0")
      def selectedItem = elem.selectedItemsJS(0)
    }
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/SegmentedButton.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-segmented-button")

  lazy val accessibleName: HtmlAttr[String]             = htmlAttr("accessible-name", StringAsIsCodec)
  lazy val selectionMode: HtmlAttr[SegmentedButtonMode] = SegmentedButtonMode.asHtmlAttr("selection-mode")

  @deprecated("mode property of SegmentedButton has been renamed to selectionMode", since = "2.0.0")
  def mode: HtmlAttr[SegmentedButtonMode] = selectionMode

  object slots {}

  object events {
    val onSelectionChange: EventProp[
      dom.Event & HasDetail[HasSelectedItem[SegmentedButtonItem.Ref] & HasSelectedItems[SegmentedButtonItem.Ref]]
    ] =
      new EventProp("selection-change")
  }

  def item: SegmentedButtonItem.type = SegmentedButtonItem

}
