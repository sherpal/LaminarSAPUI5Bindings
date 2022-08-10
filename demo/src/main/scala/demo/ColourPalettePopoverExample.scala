package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, MTG}
import be.doeraene.webcomponents.ui5.scaladsl.colour.Colour
import org.scalajs.dom.HTMLElement
import com.raquo.airstream.eventbus.EventBus

object ColourPalettePopoverExample extends Example("ColourPalettePopover") {

  def component: HtmlElement = div(
    DemoPanel("Color Palette Popover with recent colors, default color and more colors features") {
      //-- Begin: Color Palette Popover with recent colors, default color and more colors features
      val openPopoverBus: EventBus[HTMLElement] = new EventBus
      div(
        Button(
          _ => "Open ColourPalettePopover",
          _.events.onClick.mapToEvent.map(_.target) --> openPopoverBus.writer
        ),
        ColourPalettePopover(
          _ =>
            inContext(el =>
              openPopoverBus.events.map(el.ref -> _) --> Observer[(ColourPalettePopover.Ref, HTMLElement)](_ showAt _)
            ),
          _ => ColourPaletteExample.someColourPaletteItems,
          _.showRecentColours := true,
          _.showMoreColours := true,
          _.showDefaultColour := true,
          _.defaultColour := Colour.green
        )
      )
      //-- End
    },
    DemoPanel("Color Palette Popover without any additional features") {
      //-- Begin: Color Palette Popover without any additional features
      val openPopoverBus: EventBus[HTMLElement] = new EventBus
      div(
        Button(
          _ => "Open ColourPalettePopover",
          _.events.onClick.mapToEvent.map(_.target) --> openPopoverBus.writer
        ),
        ColourPalettePopover(
          _ =>
            inContext(el =>
              openPopoverBus.events.map(el.ref -> _) --> Observer[(ColourPalettePopover.Ref, HTMLElement)](_ showAt _)
            ),
          _ => ColourPaletteExample.someColourPaletteItems,
          _.defaultColour := Colour.green
        )
      )
      //-- End
    }
  )

}
