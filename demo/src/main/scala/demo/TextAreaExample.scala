package demo

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object TextAreaExample extends Example("TextArea") {

  def webComponent: WebComponent = TextArea

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic TextArea")(
      //-- Begin: Basic TextArea
      TextArea(_.placeholder := "Type as much text as you wish")
      //-- End
    ),
    DemoPanel("TextArea with Maximum length")(
      //-- Begin: TextArea with Maximum length
      TextArea(
        _.placeholder := "Type some text",
        _.maxLength := 10,
        _.showExceededText := true
      )
      //-- End
    )
  )

}
