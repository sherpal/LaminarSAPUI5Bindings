package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object BusyIndicatorExample extends Example("BusyIndicator") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Busy indicator with difference size")(
      //-- Begin: Busy indicator with difference size
      div(
        display := "flex",
        flexDirection := "column",
        alignItems := "center",
        BusyIndicatorSize.allValues.map(size =>
          BusyIndicator(
            _.size := size,
            _.active := true
          )
        )
      )
      //-- End
    ),
    DemoPanel("Busy indicator wrapping other elements") {
      //-- Begin: Busy indicator wrapping other elements
      val fetchListDataBus: EventBus[Unit] = new EventBus
      val allData                          = (1 to 10).map(j => s"Data $j").toList
      val numberOfDataToFetch              = fetchListDataBus.events.delay(3000).scanLeft(0)((acc, _) => acc + 3)
      val fetchedData                      = numberOfDataToFetch.map(allData.take)
      val busyStates = EventStream
        .merge(
          fetchListDataBus.events.mapTo(1),
          numberOfDataToFetch.changes.mapTo(-1)
        )
        .scanLeft(0)(_ + _)
        .map(_ > 0)
      div(
        div(Button("Fetch list data", _.events.onClick.mapTo(()) --> fetchListDataBus.writer)),
        div(
          BusyIndicator(
            _.active <-- busyStates,
            UList(
                width := "400px",
                children <-- fetchedData.map(data => data.map(pieceOfData => UList.item(pieceOfData))),
                _.noDataText := "No Data"
              )
          )
        )
      )
      //-- End
    }
  )

}
