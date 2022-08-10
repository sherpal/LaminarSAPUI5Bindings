package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example}

object PanelExample extends Example("Panel") {

  private val countries = List("Argentina", "Belgium", "Bulgaria", "Canada", "Columbia", "Croatia", "Denmark")

  def component: HtmlElement = div(
    DemoPanel("Basic Panel")(
      //-- Begin: Basic Panel
      Panel(
        _ => width := "100%",
        _.headerText := "Both expandable and expanded",
        _ => h1("I am a native heading!"),
        _ => Label(_.wrappingType := WrappingType.Normal, _ => "Short text."),
        _ => br(),
        _ => Label(_.wrappingType := WrappingType.Normal, _ => "Another text."),
        _ =>
          p(
            "Aute ullamco officia fugiat culpa do tempor tempor aute excepteur magna. Quis velit adipisicing excepteur " +
              "do eu duis elit. Sunt ea pariatur nulla est laborum proident sunt labore commodo Lorem laboris nisi Lorem."
          )
      )
      //-- End
    ),
    DemoPanel("Panel with List")(
      //-- Begin: Panel with List
      Panel(
        _.headerText := "Select your country",
        _ => width := "100%",
        _ =>
          UList(
            _.mode := ListMode.MultiSelect,
            _ => countries.map(country => UList.Li(_ => country))
          )
      )
      //-- End
    ),
    DemoPanel("Fixed Panel (Can't be Collapsed/Expanded)")(
      //-- Begin: Fixed Panel (Can't be Collapsed/Expanded)
      Panel(
        _.fixed := true,
        _.headerText := "Country Of Birth",
        _ =>
          UList(
            _.mode := ListMode.SingleSelectBegin,
            _ => countries.map(country => UList.Li(_ => country))
          )
      )
      //-- End
    ),
    DemoPanel("Panel with Custom Header")(
      //-- Begin: Panel with Custom Header
      div(
        styleTag("""
      |.header {
      |		display: flex;
      |		align-items: center;
      |		justify-content: space-between;
      |		width: 100%;
      |}
      |""".stripMargin),
        Panel(
          _ => width := "100%",
          _.slots.header := div(
            className := "header",
            h1("Countries"),
            div(
              Button(_ => "Edit"),
              Button(_.design := ButtonDesign.Emphasized, _ => "Add"),
              Button(_.design := ButtonDesign.Negative, _ => "Remove")
            )
          ),
          _ =>
            UList(
              _.mode := ListMode.MultiSelect,
              _ => countries.map(country => UList.Li(_ => country))
            )
        )
      )
      //-- End
    )
  )

}
