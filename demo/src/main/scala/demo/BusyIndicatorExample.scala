package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example}

object BusyIndicatorExample extends Example("BusyIndicator") {

  def component: HtmlElement = div(
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
      val numberOfDataToFetch              = fetchListDataBus.events.delay(3000).foldLeft(0)((acc, _) => acc + 3)
      val fetchedData                      = numberOfDataToFetch.map(allData.take)
      val busyStates = EventStream
        .merge(
          fetchListDataBus.events.map(_ => 1),
          numberOfDataToFetch.changes.map(_ => -1)
        )
        .foldLeft(0)(_ + _)
        .map(_ > 0)
      div(
        div(Button(_ => "Fetch list data", _.onClick.mapTo(()) --> fetchListDataBus.writer)),
        div(
          BusyIndicator(
            _.active <-- busyStates,
            _ =>
              UList(
                _ => width := "400px",
                _ => children <-- fetchedData.map(data => data.map(pieceOfData => UList.Li(_ => pieceOfData))),
                _.noDataText := "No Data"
              )
          )
        )
      )
      //-- End
    }
  )

}
