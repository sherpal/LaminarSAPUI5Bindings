package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example}

object LinkExample extends Example("Link") {

  def component: HtmlElement = div(
    DemoPanel("Different Link Designs")(
      //-- Begin: Different Link Designs
      div(
        LinkDesign.allValues.map(design =>
          Link(
            _.href := "https://www.scala-js.org/",
            _.target := LinkTarget._blank,
            _.design := design,
            _ => s"$design Link",
            _ => marginRight := "3em"
          )
        ),
        Link(_.href := "https://www.scala-js.org/", _.target := LinkTarget._blank, _.disabled := true, _ => "Disabled")
      )
      //-- End
    )
  )

}
