package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.eventtypes.{HasDetail, HasSelectedItem}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-segmented-button shows a group of items. When the user clicks or taps one of the items, it stays in a
  * pressed state. It automatically resizes the items to fit proportionally within the component. When no width is set,
  * the component uses the available width.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/SegmentedButton/">the doc</a> for more
  *   information.
  */
object SegmentedButton {

  @js.native
  trait RawElement extends js.Object {
    def selectedItem: SegmentedButtonItem.Ref = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/SegmentedButton.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = SegmentedButton.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-segmented-button")

  val id: ReactiveProp[String, String] = idAttr

  val accessibleName: ReactiveHtmlAttr[String] = customHtmlAttr("accessible-name", StringAsIsCodec)

  object slots {}

  object events {
    val onSelectionChange: EventProp[dom.Event & HasDetail[HasSelectedItem[SegmentedButtonItem.Ref]]] =
      new EventProp("selection-change")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(SegmentedButton)): _*)

  def item: SegmentedButtonItem.type = SegmentedButtonItem

}
