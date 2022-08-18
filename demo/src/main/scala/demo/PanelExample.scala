package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object PanelExample extends Example("Panel") {

  private val countries = List("Argentina", "Belgium", "Bulgaria", "Canada", "Columbia", "Croatia", "Denmark")

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic Panel")(
      //-- Begin: Basic Panel
      Panel(
        width := "100%",
        _.headerText := "Both expandable and expanded",
        h1("I am a native heading!"),
        Label(_.wrappingType := WrappingType.Normal, "Short text."),
        br(),
        Label(_.wrappingType := WrappingType.Normal, "Another text."),
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
        width := "100%",
                  UList(
            _.mode := ListMode.MultiSelect,
            countries.map(country => UList.item(country))
          )
      )
      //-- End
    ),
    DemoPanel("Fixed Panel (Can't be Collapsed/Expanded)")(
      //-- Begin: Fixed Panel (Can't be Collapsed/Expanded)
      Panel(
        _.fixed := true,
        _.headerText := "Country Of Birth",
                  UList(
            _.mode := ListMode.SingleSelectBegin,
            countries.map(country => UList.item(country))
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
          width := "100%",
          _.slots.header := div(
            className := "header",
            h1("Countries"),
            div(
              Button("Edit"),
              Button(_.design := ButtonDesign.Emphasized, "Add"),
              Button(_.design := ButtonDesign.Negative, "Remove")
            )
          ),
                      UList(
              _.mode := ListMode.MultiSelect,
              countries.map(country => UList.item(country))
            )
        )
      )
      //-- End
    )
  )

}
