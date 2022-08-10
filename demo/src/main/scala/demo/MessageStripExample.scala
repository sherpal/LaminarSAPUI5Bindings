package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example}
import org.scalajs.dom.HTMLElement

object MessageStripExample extends Example("MessageStrip") {

  def component: HtmlElement = div(
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
                  _ => s"${design.value} MessageStrip",
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
            _.design := design,
            _ => s"${design.value} MessageStrip with No Close Button"
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
                  _ => s"${design.value} MessageStrip",
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
      val numberOfClicks             = clickedBus.events.mapTo(1).foldLeft(0)(_ + _).changes
      div(
        Button(_ => "Generate MessageStrip", _.events.onClick.mapTo(()) --> clickedBus.writer),
        child <-- numberOfClicks.map(count =>
          MessageStrip(
            _.design := MessageStripDesign.allValues(count % MessageStripDesign.allValues.size),
            _ => s"You clicked $count times.",
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
          _.design := MessageStripDesign.Information,
          _ => width := "300px",
          _.hideIcon := true,
          _.hideCloseButton := true,
          _ => "You have new message"
        ),
        MessageStrip(
          _.design := MessageStripDesign.Positive,
          _ => width := "300px",
          _.hideCloseButton := true,
          _ => "Successful login!"
        ),
        MessageStrip(
          _.design := MessageStripDesign.Negative,
          _ => width := "300px",
          _.hideIcon := true,
          _ => "Access denied!"
        ),
        MessageStrip(_.design := MessageStripDesign.Warning, _ => width := "300px", _ => "Update is required"),
        MessageStrip(
          _.design := MessageStripDesign.Warning,
          _ => width := "300px",
          _.slots.icon := Icon(_.name := IconName.palette),
          _ => "Custom Icon"
        ),
        MessageStrip(
          _.design := MessageStripDesign.Positive,
          _ => width := "300px",
          _ => "Custom animated icon",
          _.slots.icon := img(
            src := "https://sap.github.io/ui5-webcomponents/assets/images/loading.gif",
            width := "16px",
            height := "16px"
          )
        )
      )
      //-- End
    )
  )

}
