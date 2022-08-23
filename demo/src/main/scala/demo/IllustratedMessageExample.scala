package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object IllustratedMessageExample extends Example("IllustratedMessage") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Illustrated Message")(
      //-- Begin: Illustrated Message
      IllustratedMessage(
        _.name := IllustrationMessageType.PageNotFound,
                  List(
            Button(_.design := ButtonDesign.Emphasized, "Action 1"),
            Button("Action 2")
          )
      )
      //-- End
    ),
    DemoPanel("Illustrated Message in dialog") {
      //-- Begin: Illustrated Message in dialog
      val dialogShowActionBus: EventBus[Boolean] = new EventBus
      div(
        Button("Open Dialog", _.events.onClick.mapTo(true) --> dialogShowActionBus.writer),
        Dialog(
          _.headerText := "Error",
                      inContext(el =>
              dialogShowActionBus.events --> Observer[Boolean](if _ then el.ref.show() else el.ref.close())
            ),
          IllustratedMessage(_.name := IllustrationMessageType.ErrorScreen),
          _.slots.footer := Bar(
            _.design := BarDesign.Footer,
            _.slots.endContent := Button(
              _.design := ButtonDesign.Emphasized,
              "Close",
              _.events.onClick.mapTo(false) --> dialogShowActionBus.writer
            )
          )
        )
      )
      //-- End
    }
    // todo: uncomment this example when we have a high enough sap ui5 version.
    // DemoPanel(
    //   "Illustrated Message with link in subtitle",
    //   IllustratedMessage(
    //     _.name := IllustrationMessageType.TntUnsuccessfulAuth,
    //     _.titleText := "Something wen wrong...",
    //     _.slots.subtitle := div("Please try again or contact us at ", Link("example@example.com"), "."),
    //     Button(_.icon := IconName.refresh, "Try again")
    //   )
    // )
  )

}
