package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example}

object TextAreaExample extends Example("TextArea") {

  def component: HtmlElement = div(
    DemoPanel("Basic TextArea", TextArea(_.placeholder := "Type as much text as you wish")),
    DemoPanel(
      "TextArea with Maximum length",
      TextArea(
        _.placeholder := "Type some text",
        _.maxLength := 10,
        _.showExceededText := true
      )
    )
  )

}
