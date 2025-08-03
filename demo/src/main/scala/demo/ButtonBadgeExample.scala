package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object ButtonBadgeExample extends Example("ButtonBadge") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement =
    div(
      h1("Button"),
      DemoPanel("Basic Button with badge")(
        //-- Begin: Basic Button
        div(
          Button("Click me", _.slots.badge := ButtonBadge(_.design := ButtonBadgeDesign.AttentionDot)),
          Button("Click me", _.slots.badge := ButtonBadge(_.text := "3")),
          Button("Click me", _.slots.badge := ButtonBadge(_.text := "9+", _.design := ButtonBadgeDesign.OverlayText))
        )
        //-- End
      )
    )

}
