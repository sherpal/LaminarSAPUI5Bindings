package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub, MTG}
import be.doeraene.webcomponents.ui5.scaladsl.colour.Colour

object ColourPickerExample extends Example("ColourPicker") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Pick colour") {
      //-- Begin: Pick Colour
      val maybeChosenColourVar: Var[Option[Colour]] = Var(Option.empty)
      div(
        ColourPicker(_.events.onChange.map(_.target.value).map(Some(_)) --> maybeChosenColourVar.writer),
        div(
          child.text <-- maybeChosenColourVar.signal.map {
            case Some(colour) => s"You have chosen colour ${colour.rgba}."
            case None         => "Chose a colour."
          }
        )
      )
      //-- End
    }
  )

}
