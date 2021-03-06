package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.internal.Slot
import be.doeraene.webcomponents.ui5.scaladsl.colour.Colour
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-color-palette-item component represents a color in the the ui5-color-palette.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/ColorPalette/">the doc</a> for more
  *   information.
  */
object ColourPaletteItem {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/ColorPalette.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = ColourPaletteItem.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-color-palette-item")

  val id: ReactiveProp[String, String] = idAttr

  val value: ReactiveHtmlAttr[Colour] = customHtmlAttr("value", Colour.AsStringCodec)

  object slots {}

  object events {}

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(ColourPaletteItem)): _*)

}
