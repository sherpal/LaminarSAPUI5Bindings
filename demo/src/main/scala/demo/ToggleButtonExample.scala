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
        ToggleButton(_ => "ToggleButton"),
        ToggleButton(_.pressed := true, _ => "Pressed ToggleButton"),
        ToggleButton(_.disabled := true, _ => "Disabled ToggleButton"),
        ToggleButton(_.disabled := true, _.pressed := true, _ => "Disabled pressed ToggleButton"),
        ToggleButton(_.design := ButtonDesign.Positive, _ => "Accept ToggleButton"),
        ToggleButton(_.design := ButtonDesign.Positive, _.pressed := true, _ => "Accept pressed ToggleButton"),
        ToggleButton(_.design := ButtonDesign.Negative, _ => "Reject ToggleButton"),
        ToggleButton(_.design := ButtonDesign.Negative, _.pressed := true, _ => "Reject pressed ToggleButton"),
        ToggleButton(_.design := ButtonDesign.Transparent, _ => "Transparent ToggleButton"),
        ToggleButton(_.design := ButtonDesign.Transparent, _.pressed := true, _ => "Transparent pressed ToggleButton")
      )
      //-- End
    },
    DemoPanel("ToggleButton with Icon") {
      //-- Begin: ToggleButton with Icon
      div(
        ToggleButton(_.icon := IconName.menu, _ => "Menu"),
        ToggleButton(_.design := ButtonDesign.Emphasized, _.icon := IconName.add, _ => "Add"),
        ToggleButton(_.design := ButtonDesign.Default, _.icon := IconName.`nav-back`, _ => "Back"),
        ToggleButton(_.design := ButtonDesign.Positive, _.icon := IconName.accept, _ => "Accept"),
        ToggleButton(_.design := ButtonDesign.Negative, _.icon := IconName.`sys-cancel`, _ => "Deny")
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
    }
  )

}
