package demo

import be.doeraene.webcomponents.ui5.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example}

object InputExample extends Example("Input") {

  def component: HtmlElement = Input(_.placeholder := "Fill Me")

}
