package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, MTG}
import be.doeraene.webcomponents.ui5.scaladsl.colour.Colour
import org.scalajs.dom.HTMLElement
import com.raquo.airstream.eventbus.EventBus

object ColourPalettePopoverExample extends Example("ColourPalettePopover") {

  private def colourPalettePopoverExample(withExtraFeatures: Boolean) = {
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
        _.showRecentColours := withExtraFeatures,
        _.showMoreColours := withExtraFeatures,
        _.showDefaultColour := withExtraFeatures,
        _.defaultColour := Colour.green
      )
    )
  }

  def component: HtmlElement = div(
    DemoPanel(
      "Color Palette Popover with recent colors, default color and more colors features",
      colourPalettePopoverExample(true)
    ),
    DemoPanel(
      "Color Palette Popover without any additional features",
      colourPalettePopoverExample(false)
    )
  )

}
