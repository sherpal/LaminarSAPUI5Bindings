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
        Button(_ => "Basic Toast", _.events.onClick.mapTo(()) --> toastBus.writer),
        Toast(
          _ => inContext(el => toastBus.events.mapTo(()) --> Observer[Unit](_ => el.ref.show())),
          _ => "Basic Toast"
        ),
        MessageStrip(
          _.design := MessageStripDesign.Information,
          _ => "This toast pops up thanks to the click event going through an EventBus."
        )
      )
      //-- End
    },
    DemoPanel("Toast Duration") {
      //-- Begin: Toast Duration
      val shortToastId = "short-toast-id"
      val longToastId  = "long-toast-id"

      div(
        Button(
          _ => "Short Toast",
          _.events.onClick.mapTo(Toast.getToastById(shortToastId)) --> Observer[Option[Toast.Ref]] {
            case Some(toast) => toast.show()
            case None => throw new IllegalStateException(s"The dom does not contain any toast with id $shortToastId")
          }
        ),
        Toast(
          _.id := shortToastId,
          _.duration := 1500.millis,
          _.placement := ToastPlacement.BottomStart,
          _ => "Short Toast"
        ),
        Button(
          _ => "Long Toast",
          _.events.onClick.mapTo(Toast.getToastById(longToastId)) --> Observer[Option[Toast.Ref]] {
            case Some(toast) => toast.show()
            case None => throw new IllegalStateException(s"The dom does not contain any toast with id $longToastId")
          }
        ),
        Toast(
          _.id := longToastId,
          _.duration := 4500.millis,
          _.placement := ToastPlacement.BottomEnd,
          _ => "Long Toast"
        ),
        MessageStrip(
          _.design := MessageStripDesign.Information,
          _ => "These toasts pops up by grabbing their reference using the Toast.getToastById function."
        )
      )
      //-- End
    },
    DemoPanel("Toast Placements")(
      //-- Begin: Toast Placements
      div(
        ToastPlacement.allValues.flatMap { placement =>
          val toastBus: EventBus[Unit] = new EventBus

          List(
            Button(_ => placement.value, _.events.onClick.mapTo(()) --> toastBus.writer),
            Toast(
              _.placement := placement,
              _ => inContext(el => toastBus.events.mapTo(()) --> Observer[Unit](_ => el.ref.show())),
              _ => placement.value
            )
          )
        }
      )
      //-- End
    )
  )

}
