package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}
import org.scalajs.dom.HTMLElement

object MessageStripExample extends Example("MessageStrip") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("MessageStrip")(
      //-- Begin: MessageStrip
      div(
        MessageStripDesign.allValues.map { design =>
          val closeBus: EventBus[Unit] = new EventBus
          div(
            child <-- closeBus.events
              .mapTo(emptyNode)
              .startWith(
                MessageStrip(
                  _.design := design,
                  s"${design.value} MessageStrip",
                  _.events.onClose.mapTo(()) --> closeBus.writer
                )
              )
          )
        }
      )
      //-- End
    ),
    DemoPanel("MessageStrip With No Close Button")(
      //-- Begin: MessageStrip With No Close Button
      div(
        MessageStripDesign.allValues.map(design =>
          MessageStrip(
            _.hideCloseButton := true,
            _.design          := design,
            s"${design.value} MessageStrip with No Close Button"
          )
        )
      )
      //-- End
    ),
    DemoPanel("MessageStrip With No Icon")(
      //-- Begin: MessageStrip With No Icon
      div(
        MessageStripDesign.allValues.map { design =>
          val closeBus: EventBus[Unit] = new EventBus
          div(
            child <-- closeBus.events
              .mapTo(emptyNode)
              .startWith(
                MessageStrip(
                  _.design := design,
                  s"${design.value} MessageStrip",
                  _.events.onClose.mapTo(()) --> closeBus.writer,
                  _.hideIcon := true
                )
              )
          )
        }
      )
      //-- End
    ),
    DemoPanel("Dynamic Message Strip Generator") {
      //-- Begin: Dynamic Message Strip Generator
      val clickedBus: EventBus[Unit] = new EventBus
      val numberOfClicks             = clickedBus.events.mapTo(1).scanLeft(0)(_ + _).changes
      div(
        Button("Generate MessageStrip", _.events.onClick.mapTo(()) --> clickedBus.writer),
        child <-- numberOfClicks.map(count =>
          MessageStrip(
            _.design := MessageStripDesign.allValues(count % MessageStripDesign.allValues.size),
            s"You clicked $count times.",
            _.hideCloseButton := true
          )
        )
      )
      //-- End
    },
    DemoPanel("Custom MessageStrip")(
      //-- Begin: Custom MessageStrip
      div(
        MessageStrip(
          _.design          := MessageStripDesign.Information,
          width             := "300px",
          _.hideIcon        := true,
          _.hideCloseButton := true,
          "You have new message"
        ),
        MessageStrip(
          _.design          := MessageStripDesign.Positive,
          width             := "300px",
          _.hideCloseButton := true,
          "Successful login!"
        ),
        MessageStrip(
          _.design   := MessageStripDesign.Negative,
          width      := "300px",
          _.hideIcon := true,
          "Access denied!"
        ),
        MessageStrip(_.design := MessageStripDesign.Critical, width := "300px", "Update is required"),
        MessageStrip(
          _.design     := MessageStripDesign.Critical,
          width        := "300px",
          _.slots.icon := Icon(_.name := IconName.palette),
          "Custom Icon"
        ),
        MessageStrip(
          _.design := MessageStripDesign.Positive,
          width    := "300px",
          "Custom animated icon",
          _.slots.icon := img(
            src    := "https://sap.github.io/ui5-webcomponents/assets/images/loading.gif",
            width  := "16px",
            height := "16px"
          )
        )
      )
      //-- End
    )
  )

}
