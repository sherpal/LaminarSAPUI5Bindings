package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object IconExample extends Example("Icon") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic Icons")(
      //-- Begin: Basic Icons
      div(IconName.allValues.take(10).map(name => Icon(_.name := name, _ => marginRight := "5px")))
      //-- End
    ),
    DemoPanel("Customized Icons")(
      //-- Begin: Customized Icons
      div(
        IconName.allValues.reverse
          .take(3)
          .map(name =>
            Icon(
              _.name := name,
              _ => marginRight := "1em",
              _ =>
                List(
                  width := "3rem",
                  height := "3rem",
                  fontSize := "1.5rem",
                  color := "crimson",
                  backgroundColor := "#fafafa"
                )
            )
          )
      )
      //-- End
    )
  )

}
