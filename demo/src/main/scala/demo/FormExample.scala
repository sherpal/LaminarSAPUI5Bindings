package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object FormExample extends Example("Form") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Simple Form") {
      //-- Begin: Simple Form
      val sliderValueVar = Var(85.0)
      val widthSignal    = sliderValueVar.signal.map(_ / 100 * 1500).map(_.toInt)
      val cssWidthSignal = widthSignal.map(w => s"${w}px")
      val layoutSignal = widthSignal.map {
        case w if w > 599 && w < 1024   => "M"
        case w if w >= 1024 && w < 1440 => "L"
        case w if w >= 1440             => "XL"
        case _                          => "S"
      }
      div(
        Label(_.showColon := true, "Form Layout"),
        Text("S1 M3 L4 XL4"),
        Label(_.showColon := true, "Page Size"),
        Text(child.text  <-- layoutSignal),
        Slider(_.value    := 85, _.events.onChange.map(_.target.value) --> sliderValueVar.writer),
        div(
          maxWidth := "1500px",
          width   <-- cssWidthSignal,
          overflowX.auto,
          Form(
            _.headerText := "Address",
            _.layout     := "S1 M2 L2 XL2",
            _.item(
              _.slots.labelContent := Label("Name:"),
              Text("Red Point Stores")
            ),
            _.item(
              _.slots.labelContent := Label("ZIP Code/City:"),
              Text("411 Maintown")
            ),
            _.item(
              _.slots.labelContent := Label("Street:"),
              Text("Main St 1618")
            ),
            _.item(
              _.slots.labelContent := Label("Country:"),
              Text("Germany")
            )
          )
        )
      )
      //-- End
    }
  )

}
