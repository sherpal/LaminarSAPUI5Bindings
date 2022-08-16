package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub, MTG}
import be.doeraene.webcomponents.ui5.scaladsl.colour.Colour

object ColourPaletteExample extends Example("ColourPalette") {

  //-- Begin Common
  def someColourPaletteItems = List(
    Colour.fromString("darkblue"),
    Colour.fromString("pink"),
    Colour.fromIntColour(0x444444),
    Colour(0, 200, 0),
    Colour.green,
    Colour.fromString("darkred"),
    Colour.yellow,
    Colour.blue,
    Colour.fromString("cyan"),
    Colour.orange,
    Colour.fromIntColour(0x5480e7),
    Colour.fromIntColour(0xff6699)
  ).map(colour => ColourPalette.item(_.value := colour))
  //-- End Common

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Colour Palette")(
      //-- Begin: Colour Palette
      ColourPalette(_ => someColourPaletteItems)
      //-- End
    )
  )

}
