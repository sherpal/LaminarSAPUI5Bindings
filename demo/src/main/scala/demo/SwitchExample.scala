package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example}

object SwitchExample extends Example("Switch") {

  def component: HtmlElement = div(
    styleTag("""
    |div > ui5-switch {
    |  margin-right: 1em;
    |}
    |""".stripMargin),
    DemoPanel(
      "BasicSwitch",
      div(
        Switch(_.textOn := "On", _.textOff := "Off"),
        Switch(_.textOn := "On", _.textOff := "Off", _.checked := true),
        Switch(),
        Switch(_.textOn := "Yes", _.textOff := "No", _.disabled := true),
        Switch(_.textOn := "Yes", _.textOff := "No", _.checked := true, _.disabled := true)
      )
    ),
    DemoPanel(
      "Graphical Switch",
      div(
        for {
          disabled <- List(false, true)
          checked  <- List(false, true)
        } yield Switch(_.design := SwitchDesign.Graphical, _.checked := checked, _.disabled := disabled)
      )
    )
  )

}