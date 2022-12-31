package demo

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object ButtonExample extends Example("Button") {

  def webComponent: WebComponent = Button

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = {
    val exampleButtonContainerClass = "exampleButtonContainerClass"
    div(
      styleTag(s"""
        |.$exampleButtonContainerClass > ui5-button {
        |  margin-left: 1em;
        |}
        |""".stripMargin),
      h1("Button"),
      DemoPanel("Basic Button")(
        //-- Begin: Basic Button
        div(
          className := exampleButtonContainerClass,
          Button(_.design := ButtonDesign.Default, "Default"),
          Button(_.disabled := true, "Disabled"),
          Button(_.design := ButtonDesign.Transparent, "Cancel"),
          Button(_.design := ButtonDesign.Positive, "Approve"),
          Button(_.design := ButtonDesign.Negative, "Decline"),
          Button(_.design := ButtonDesign.Attention, "Warning"),
          Button(_.design := ButtonDesign.Emphasized, "Subscribe")
        )
        //-- End
      ),
      DemoPanel("Button with Icon")(
        //-- Begin: Button with Icon
        div(
          className := exampleButtonContainerClass,
          Button(_.icon := IconName.employee, "Add"),
          Button(_.icon := IconName.download, "Download"),
          Button(_.design := ButtonDesign.Positive, _.icon := IconName.add, "Add"),
          Button(_.design := ButtonDesign.Negative, _.icon := IconName.delete, "Remove"),
          Button(_.design := ButtonDesign.Attention, _.icon := IconName.`message-warning`, "Warning"),
          Button(_.design := ButtonDesign.Transparent, _.icon := IconName.accept, "Transparent")
        )
        //-- End
      ),
      DemoPanel("Icon Only Button")(
        //-- Begin: Icon Only Button
        div(
          className := exampleButtonContainerClass,
          Button(_.icon := IconName.away),
          Button(_.icon := IconName.`action-settings`),
          Button(_.icon := IconName.add),
          Button(_.icon := IconName.alert),
          Button(_.icon := IconName.accept, _.design := ButtonDesign.Positive),
          Button(_.icon := IconName.bookmark, _.design := ButtonDesign.Positive),
          Button(_.icon := IconName.cart, _.design := ButtonDesign.Transparent)
        )
        //-- End
      )
    )
  }

}
