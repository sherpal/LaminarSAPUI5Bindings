package demo.helpers

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*

object DemoPanel {
  def apply(title: String, body: => HtmlElement): HtmlElement = div(
    h2(title),
    div(
      padding := "1em",
      border := "0.0625rem solid #C1C1C1",
      backgroundColor := "#f7f7f7",
      body
    )
    // div(
    //   border := "0.0625rem solid #C1C1C1",
    //   backgroundColor := "#f5f6fa"
    // )
  )
}
