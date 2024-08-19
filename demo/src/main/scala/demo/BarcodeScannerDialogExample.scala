package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object BarcodeScannerDialogExample extends Example("BarcodeScannerDialog") {

  def component(using demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo): HtmlElement = div(
    DemoPanel("Usage") {
      //-- Begin: Usage
      // Feed this bus in order to open the barcode scanner
      val openScannerBus: EventBus[Unit] = new EventBus

      // Feed this bus to pass the information of a failed scan
      val barcodeScanFailedBus: EventBus[BarcodeScannerDialog.events.ErrorInfo] = new EventBus
      // Feed this bus to pass the information of a successful scan
      val barcodeScanSuccessBus: EventBus[BarcodeScannerDialog.events.SuccessInfo] = new EventBus

      val barcodeScanEvents = EventStream
        .merge(
          barcodeScanSuccessBus.events,
          barcodeScanFailedBus.events
        )
        .mapTo(())

      div(
        BarcodeScannerDialog(
          _.open <-- EventStream.merge(openScannerBus.events.mapTo(true), barcodeScanEvents.mapTo(false)),
          _.events.onScanSuccess.map(_.detail) --> barcodeScanSuccessBus.writer,
          _.events.onScanError.map(_.detail) --> barcodeScanFailedBus.writer
        ),
        Title.h3("Click on the button below and show a QR code to the camera:"),
        Button(
          _.icon    := IconName.camera,
          _.tooltip := "Start Camera",
          "Scan",
          _.events.onClick.mapTo(()) --> openScannerBus.writer
        ),
        div(Label(child.text <-- barcodeScanSuccessBus.events.map(_.text))),
        div(Label(child.text <-- barcodeScanFailedBus.events.map(_.message)))
      )
      //-- End
    }
  )

}
