package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

import scala.concurrent.duration.DurationInt
import scala.scalajs.js

object ButtonExample extends Example("Button") {

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
          Button(_.design   := ButtonDesign.Default, "Default"),
          Button(_.disabled := true, "Disabled"),
          Button(_.design   := ButtonDesign.Transparent, "Cancel"),
          Button(_.design   := ButtonDesign.Positive, "Approve"),
          Button(_.design   := ButtonDesign.Negative, "Decline"),
          Button(_.design   := ButtonDesign.Attention, "Warning"),
          Button(_.design   := ButtonDesign.Emphasized, "Subscribe")
        )
        //-- End
      ),
      DemoPanel("Button with Icon")(
        //-- Begin: Button with Icon
        div(
          className := exampleButtonContainerClass,
          Button(_.icon   := IconName.employee, "Add"),
          Button(_.icon   := IconName.download, "Download"),
          Button(_.design := ButtonDesign.Positive, _.icon    := IconName.add, "Add"),
          Button(_.design := ButtonDesign.Negative, _.icon    := IconName.delete, "Remove"),
          Button(_.design := ButtonDesign.Attention, _.icon   := IconName.`message-warning`, "Warning"),
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
          Button(_.icon := IconName.accept, _.design   := ButtonDesign.Positive),
          Button(_.icon := IconName.bookmark, _.design := ButtonDesign.Positive),
          Button(_.icon := IconName.cart, _.design     := ButtonDesign.Transparent)
        )
        //-- End
      ),
      DemoPanel("Button with Loading State") {
        //-- Begin: Button with Loading State
        val loadingVar = Var(false)
        div(
          className := exampleButtonContainerClass,
          Button(
            _.design       := ButtonDesign.Emphasized,
            _.icon         := IconName.upload,
            _.loading     <-- loadingVar.signal,
            _.loadingDelay := 200.millis,
            "Upload (2 seconds)",
            _.events.onClick --> { _ =>
              loadingVar.set(true)
              js.timers.setTimeout(2000)(loadingVar.set(false))
            }
          ),
          Button(_.loading := true, "Always loading")
        )
        //-- End
      },
      DemoPanel("Button with Modifier Keys") {
        //-- Begin: Button with Modifier Keys
        val lastClickVar = Var("Click the button, possibly holding Ctrl, Alt, Shift or Meta.")
        div(
          className := exampleButtonContainerClass,
          Button(
            "Click me",
            _.events.onClick.map { event =>
              val detail = event.detail
              val keys = List(
                Option.when(detail.ctrlKey)("Ctrl"),
                Option.when(detail.altKey)("Alt"),
                Option.when(detail.shiftKey)("Shift"),
                Option.when(detail.metaKey)("Meta")
              ).flatten
              if keys.isEmpty then "Clicked without modifier keys." else s"Clicked with ${keys.mkString(" + ")}."
            } --> lastClickVar.writer
          ),
          span(child.text <-- lastClickVar.signal)
        )
        //-- End
      },
      DemoPanel("Button Types")(
        //-- Begin: Button Types
        div(
          ButtonType.allValues.map(buttonType => Button(buttonType.value, _.tpe := buttonType))
        )
        //-- End
      )
    )
  }

}
