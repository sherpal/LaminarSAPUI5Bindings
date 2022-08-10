package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example}

object ButtonExample extends Example("Button") {

  def component: HtmlElement = {
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
          Button(_.design := ButtonDesign.Default, _ => "Default"),
          Button(_.disabled := true, _ => "Disabled"),
          Button(_.design := ButtonDesign.Transparent, _ => "Cancel"),
          Button(_.design := ButtonDesign.Positive, _ => "Approve"),
          Button(_.design := ButtonDesign.Negative, _ => "Decline"),
          Button(_.design := ButtonDesign.Attention, _ => "Warning"),
          Button(_.design := ButtonDesign.Emphasized, _ => "Subscribe")
        )
        //-- End
      ),
      DemoPanel("Button with Icon")(
        //-- Begin: Button with Icon
        div(
          className := exampleButtonContainerClass,
          Button(_.icon := IconName.employee, _ => "Add"),
          Button(_.icon := IconName.download, _ => "Download"),
          Button(_.design := ButtonDesign.Positive, _.icon := IconName.add, _ => "Add"),
          Button(_.design := ButtonDesign.Negative, _.icon := IconName.delete, _ => "Remove"),
          Button(_.design := ButtonDesign.Attention, _.icon := IconName.`message-warning`, _ => "Warning"),
          Button(_.design := ButtonDesign.Transparent, _.icon := IconName.accept, _ => "Transparent")
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
