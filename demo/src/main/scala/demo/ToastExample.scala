package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

import scala.concurrent.duration.DurationInt

object ToastExample extends Example("Toast") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic Toast") {
      //-- Begin: Basic Toast
      val toastBus: EventBus[Unit] = new EventBus

      div(
        Button("Basic Toast", _.events.onClick.mapTo(()) --> toastBus.writer),
        Toast(
          _.open <-- toastBus.events.mapTo(true),
          _.events.onClose.mapTo(()) --> Observer[Any] { _ =>
            println("The toast closed.")
          },
          "Basic Toast"
        ),
        MessageStrip(
          _.design := MessageStripDesign.Information,
          "This toast pops up thanks to the click event going through an EventBus."
        )
      )
      //-- End
    },
    DemoPanel("Toast Duration") {
      //-- Begin: Toast Duration
      val shortToastBus: EventBus[Unit] = new EventBus
      val longToastBus: EventBus[Unit]  = new EventBus

      div(
        Button("Short Toast", _.events.onClick.mapTo(()) --> shortToastBus.writer),
        Toast(
          _.duration  := 1500.millis,
          _.placement := ToastPlacement.BottomStart,
          "Short Toast",
          _.open <-- shortToastBus.events.mapTo(true)
        ),
        Button("Long Toast", _.events.onClick.mapTo(()) --> longToastBus.writer),
        Toast(
          _.duration  := 4500.millis,
          _.placement := ToastPlacement.BottomEnd,
          "Long Toast",
          _.open <-- longToastBus.events.mapTo(true)
        )
      )
      //-- End
    },
    DemoPanel("Toast Placements") {
      //-- Begin: Toast Placements
      div(
        ToastPlacement.allValues.flatMap { placement =>
          val toastBus: EventBus[Unit] = new EventBus

          List(
            Button(placement.value, _.events.onClick.mapTo(()) --> toastBus.writer),
            Toast(_.placement := placement, _.open <-- toastBus.events.mapTo(true), placement.value)
          )
        }
      )
      //-- End
    }
  )

}
