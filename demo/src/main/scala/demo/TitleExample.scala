package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example}

object TitleExample extends Example("Title") {

  def component: HtmlElement = div(
    DemoPanel(
      "Title in All Available Levels",
      div(
        TitleLevel.allValues.map(level => Title(_.level := level, _ => s"Title level ${level.value.tail}"))
      )
    )
  )

}
