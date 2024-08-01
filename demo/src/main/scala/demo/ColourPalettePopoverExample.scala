package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub, MTG}
import be.doeraene.webcomponents.ui5.scaladsl.colour.Colour
import org.scalajs.dom.HTMLElement
import com.raquo.airstream.eventbus.EventBus

object ColourPalettePopoverExample extends Example("ColourPalettePopover") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Color Palette Popover with recent colors, default color and more colors features") {
      //-- Begin: Color Palette Popover with recent colors, default color and more colors features
      val openPopoverBus: EventBus[HTMLElement] = new EventBus
      val chosenColourBus                       = new EventBus[Colour]
      div(
        div(
          child.text <-- chosenColourBus.events.map(colour => s"Selected colour: $colour").startWith("No selection yet")
        ),
        Button(
          idAttr := "Color-Palette-Popover-with-recent-colors,-default-color-and-more-colors-features-Button",
          "Open ColourPalettePopover",
          _.events.onClick.mapToEvent.map(_.target) --> openPopoverBus.writer
        ),
        ColourPalettePopover(
          _.showAtOpenerIdFromEvents(openPopoverBus.events.map(_.id)),
          ColourPaletteExample.someColourPaletteItems,
          _.showRecentColours := true,
          _.showMoreColours   := true,
          _.showDefaultColour := true,
          _.defaultColour     := Colour.green,
          _.events.onItemClick.map(_.detail.scalaColour) --> chosenColourBus.writer,
          _.events.onClose --> Observer[Any](x => org.scalajs.dom.console.log(x))
        )
      )
      //-- End
    },
    DemoPanel("Color Palette Popover without any additional features") {
      //-- Begin: Color Palette Popover without any additional features
      val openPopoverBus: EventBus[Unit] = new EventBus
      val openerId                       = "Color-Palette-Popover-without-any-additional-features-Button"
      div(
        Button(
          "Open ColourPalettePopover",
          idAttr := openerId,
          _.events.onClick.mapTo(()) --> openPopoverBus.writer
        ),
        ColourPalettePopover(
          _.openerId := openerId,
          _.open    <-- openPopoverBus.events.mapTo(true),
          ColourPaletteExample.someColourPaletteItems,
          _.defaultColour := Colour.green
        )
      )
      //-- End
    }
  )

}
