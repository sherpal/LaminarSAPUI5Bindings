package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.DemoPanel

object BarExample {

  private def basicBarContent(title: String) = List[Bar.ModFunction](
    _.slots.startContent := Button(
      _.icon := IconName.home,
      _.tooltip := "Go home",
      _.design := ButtonDesign.Transparent
    ),
    _ => Label(_ => title),
    _.slots.endContent := Button(_.icon := IconName.`action-settings`, _.tooltip := "Go to settings")
  )

  def apply(): HtmlElement = div(
    BarDesign.allValues.map(design =>
      DemoPanel(
        s"${design.value} Bar",
        Bar((basicBarContent(s"${design.value} Title") :+ (_.design := design)): _*)
      )
    )
  )
}
