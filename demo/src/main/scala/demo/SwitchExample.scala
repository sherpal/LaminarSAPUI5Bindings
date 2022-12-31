package demo

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object SwitchExample extends Example("Switch") {

  def webComponent: WebComponent = Switch

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    styleTag("""
    |div > ui5-switch {
    |  margin-right: 1em;
    |}
    |""".stripMargin),
    DemoPanel("Basic Switch")(
      //-- Begin: Basic Switch
      div(
        Switch(_.textOn := "On", _.textOff := "Off", _.tooltip := "On/Off Switch"),
        Switch(_.textOn := "On", _.textOff := "Off", _.checked := true, _.tooltip := "On/Off Switch"),
        Switch(_.tooltip := "Default Switch"),
        Switch(_.textOn := "Yes", _.textOff := "No", _.disabled := true, _.tooltip := "Yes/No Switch"),
        Switch(
          _.textOn := "Yes",
          _.textOff := "No",
          _.checked := true,
          _.disabled := true,
          _.tooltip := "Yes/No Switch"
        )
      )
      //-- End
    ),
    DemoPanel("Graphical Switch")(
      //-- Begin: Graphical Switch
      div(
        for {
          disabled <- List(false, true)
          checked  <- List(false, true)
        } yield Switch(_.design := SwitchDesign.Graphical, _.checked := checked, _.disabled := disabled)
      )
      //-- End
    )
  )

}
