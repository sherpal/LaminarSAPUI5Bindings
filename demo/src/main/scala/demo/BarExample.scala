package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example}

object BarExample extends Example("Bar") {

  private def headerBarContent(title: String) = List[Bar.ModFunction](
    _.slots.startContent := Button(
      _.icon := IconName.home,
      _.tooltip := "Go home",
      _.design := ButtonDesign.Transparent
    ),
    _ => Label(_ => title),
    _.slots.endContent := Button(_.icon := IconName.`action-settings`, _.tooltip := "Go to settings")
  )

  private def footerBarContent = List[Bar.ModFunction](
    _.slots.endContent := Button(_.design := ButtonDesign.Positive, _ => "Agree"),
    _.slots.endContent := Button(_.design := ButtonDesign.Negative, _ => "Decline"),
    _.slots.endContent := Button(_.design := ButtonDesign.Transparent, _ => "Cancel")
  )

  def component: HtmlElement = div(
    List(BarDesign.Header, BarDesign.Subheader).map(design =>
      DemoPanel(s"${design.value} Bar")(
        Bar((headerBarContent(s"${design.value} Title") :+ (_.design := design)): _*)
      )
    ),
    List(BarDesign.Footer, BarDesign.FloatingFooter).map(design =>
      DemoPanel(s"${design.value} Bar")(
        Bar(footerBarContent: _*)
      )
    )
  )
}
