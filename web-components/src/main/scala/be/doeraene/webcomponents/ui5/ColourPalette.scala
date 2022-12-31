package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.eventtypes.{HasColor, HasDetail}
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

/** The ui5-color-palette provides the users with a range of predefined colors. The colors are fixed and do not change
  * with the theme.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/ColorPalette/">the doc</a> for more
  *   information.
  */
object ColourPalette extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/ColorPalette.js", JSImport.Default)
  object RawImport extends WebComponent.WithMetadata

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-color-palette")

  object slots {}

  object events {
    val onItemClick: EventProp[dom.Event & HasDetail[HasColor]] = new EventProp("item-click")
  }

  

  def item: ColourPaletteItem.type = ColourPaletteItem

}
