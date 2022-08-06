package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, MTG}
import be.doeraene.webcomponents.ui5.scaladsl.colour.Colour

object ColourPickerExample extends Example("ColourPicker") {

  def component: HtmlElement = div(
    DemoPanel(
      "Pick colour", {
        val maybeChosenColourVar: Var[Option[Colour]] = Var(Option.empty)
        div(
          ColourPicker(_.events.onChange.map(_.target.colour).map(Some(_)) --> maybeChosenColourVar.writer),
          div(
            child.text <-- maybeChosenColourVar.signal.map {
              case Some(colour) => s"You have chosen colour ${colour.rgba}."
              case None         => "Chose a colour."

            }
          )
        )
      }
    )
  )

}
