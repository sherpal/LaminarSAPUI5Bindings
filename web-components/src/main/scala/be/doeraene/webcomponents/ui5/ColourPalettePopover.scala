package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.eventtypes.{HasColor, HasDetail}
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

/** Represents a predefined range of colors for easier selection. Overview The ColorPalettePopover provides the users
  * with a slot to predefine colors. You can customize them with the use of the colors property. You can specify a
  * defaultColor and display a "Default color" button for the user to choose directly. You can display a "More
  * colors..." button that opens an additional color picker for the user to choose specific colors that are not present
  * in the predefined range.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/ColorPalettePopover/">the doc</a> for more
  *   information.
  */
object ColourPalettePopover {

  //noinspection ScalaUnusedSymbol
  @js.native
  trait RawElement extends js.Object {
    def showAt(opener: dom.HTMLElement): Unit = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/ColorPalettePopover.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = ColourPalette.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-color-palette-popover")

  val id: ReactiveProp[String, String] = idAttr

  val defaultColour: ReactiveHtmlAttr[Colour] = customHtmlAttr("default-color", Colour.AsStringCodec)

  val showDefaultColour: ReactiveHtmlAttr[Boolean] = customHtmlAttr("show-default-color", BooleanAsAttrPresenceCodec)

  val showMoreColours: ReactiveHtmlAttr[Boolean] = customHtmlAttr("show-more-colors", BooleanAsAttrPresenceCodec)

  val showRecentColours: ReactiveHtmlAttr[Boolean] = customHtmlAttr("show-recent-colors", BooleanAsAttrPresenceCodec)

  object slots {}

  object events {
    val onItemClick: EventProp[dom.Event & HasDetail[HasColor]] = new EventProp("item-click")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(ColourPalette)): _*)

  def item: ColourPaletteItem.type = ColourPaletteItem

}
