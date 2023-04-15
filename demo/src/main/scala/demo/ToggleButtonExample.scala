package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object ToggleButtonExample extends Example("ToggleButton") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    styleTag("""
    |ui5-toggle-button {
    |  margin-right: 0.5rem;
    |}
    |""".stripMargin),
    DemoPanel("ToggleButton States") {
      //-- Begin: ToggleButton States
      div(
        ToggleButton("ToggleButton"),
        ToggleButton(_.pressed := true, "Pressed ToggleButton"),
        ToggleButton(_.disabled := true, "Disabled ToggleButton"),
        ToggleButton(_.disabled := true, _.pressed := true, "Disabled pressed ToggleButton"),
        ToggleButton(_.design := ButtonDesign.Positive, "Accept ToggleButton"),
        ToggleButton(_.design := ButtonDesign.Positive, _.pressed := true, "Accept pressed ToggleButton"),
        ToggleButton(_.design := ButtonDesign.Negative, "Reject ToggleButton"),
        ToggleButton(_.design := ButtonDesign.Negative, _.pressed := true, "Reject pressed ToggleButton"),
        ToggleButton(_.design := ButtonDesign.Transparent, "Transparent ToggleButton"),
        ToggleButton(_.design := ButtonDesign.Transparent, _.pressed := true, "Transparent pressed ToggleButton")
      )
      //-- End
    },
    DemoPanel("ToggleButton with Icon") {
      //-- Begin: ToggleButton with Icon
      div(
        ToggleButton(_.icon := IconName.menu, "Menu"),
        ToggleButton(_.design := ButtonDesign.Emphasized, _.icon := IconName.add, "Add"),
        ToggleButton(_.design := ButtonDesign.Default, _.icon := IconName.`nav-back`, "Back"),
        ToggleButton(_.design := ButtonDesign.Positive, _.icon := IconName.accept, "Accept"),
        ToggleButton(_.design := ButtonDesign.Negative, _.icon := IconName.`sys-cancel`, "Deny")
      )
      //-- End
    },
    DemoPanel("ToggleButton with Icon Only") {
      //-- Begin: ToggleButton with Icon Only
      div(
        ToggleButton(_.icon := IconName.accept),
        ToggleButton(_.icon := IconName.`action-settings`, _.pressed := true),
        ToggleButton(_.icon := IconName.add),
        ToggleButton(_.icon := IconName.alert, _.pressed := true),
        ToggleButton(_.icon := IconName.away, _.design := ButtonDesign.Positive),
        ToggleButton(_.icon := IconName.bookmark, _.design := ButtonDesign.Positive),
        ToggleButton(_.icon := IconName.cancel, _.design := ButtonDesign.Negative),
        ToggleButton(_.icon := IconName.call, _.design := ButtonDesign.Negative, _.pressed := true),
        ToggleButton(_.icon := IconName.camera, _.design := ButtonDesign.Transparent),
        ToggleButton(_.icon := IconName.cart, _.design := ButtonDesign.Transparent, _.pressed := true)
      )
      //-- End
    },
    DemoPanel("ToggleButton with event handler") {
      //-- Begin: ToggleButton with event handler
      val toggleStateVar = Var(false)
      div(
        p(
          "Toggle button is ",
          child.text <-- toggleStateVar.signal.map(pressed => if pressed then "" else "not "),
          "pressed."
        ),
        div(
          ToggleButton(
            _.icon := IconName.accept,
            _.pressed <-- toggleStateVar.signal,
            _.events.onClick.map(_.target.pressed) --> toggleStateVar.writer
          )
        )
      )
      //-- End
    }
  )

}
