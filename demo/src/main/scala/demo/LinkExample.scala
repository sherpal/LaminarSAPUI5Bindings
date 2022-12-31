package demo

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object LinkExample extends Example("Link") {

  def webComponent: WebComponent = Link

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Different Link Designs")(
      //-- Begin: Different Link Designs
      div(
        LinkDesign.allValues.map(design =>
          Link(
            _.href := "https://www.scala-js.org/",
            _.target := LinkTarget._blank,
            _.design := design,
            s"$design Link",
            marginRight := "3em"
          )
        ),
        Link(_.href := "https://www.scala-js.org/", _.target := LinkTarget._blank, _.disabled := true, "Disabled")
      )
      //-- End
    )
  )

}
