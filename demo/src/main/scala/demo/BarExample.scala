package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object BarExample extends Example("Bar") {

  private def headerBarContent(title: String) = List[Bar.ModFunction](
    _.slots.startContent := Button(
      _.icon := IconName.home,
      _.tooltip := "Go home",
      _.design := ButtonDesign.Transparent
    ),
    _ => Label(title),
    _.slots.endContent := Button(_.icon := IconName.`action-settings`, _.tooltip := "Go to settings")
  )

  private def footerBarContent = List[Bar.ModFunction](
    _.slots.endContent := Button(_.design := ButtonDesign.Positive, "Agree"),
    _.slots.endContent := Button(_.design := ButtonDesign.Negative, "Decline"),
    _.slots.endContent := Button(_.design := ButtonDesign.Transparent, "Cancel")
  )

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    List(BarDesign.Header, BarDesign.Subheader).map(design =>
      DemoPanel(s"${design.value} Bar")(
        Bar((headerBarContent(s"${design.value} Title") :+ (_.design := design))*)
      )
    ),
    List(BarDesign.Footer, BarDesign.FloatingFooter).map(design =>
      DemoPanel(s"${design.value} Bar")(
        Bar(footerBarContent*)
      )
    )
  )
}
