package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import be.doeraene.webcomponents.ui5.scaladsl.colour.Colour
import com.raquo.laminar.api.L.*
import demo.helpers.DemoPanel
import demo.helpers.Example
import demo.helpers.FetchDemoPanelFromGithub

object ColourPickerExample extends Example("ColourPicker") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Pick colour") {
      //-- Begin: Pick Colour
      val maybeChosenColourVar: Var[Option[Colour]] = Var(Option.empty)
      val simplifiedVar                             = Var(false)
      div(
        div(
          display.flex,
          alignItems.center,
          marginBottom := "1em",
          Label("Toggle simplified"),
          Switch(_.checked <-- simplifiedVar.signal, _.events.onCheckedChange.mapToUnit --> simplifiedVar.invertWriter)
        ),
        ColourPicker(
          _.simplified <-- simplifiedVar.signal,
          _.events.onChange.map(_.target.value).map(Some(_)) --> maybeChosenColourVar.writer
        ),
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
