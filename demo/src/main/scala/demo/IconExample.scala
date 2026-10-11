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
      div(someIconValues.take(10).map(name => Icon(_.name := name, marginRight := "5px")))
      //-- End
    ),
    DemoPanel("Customized Icons")(
      //-- Begin: Customized Icons
      div(
        someIconValues.reverse
          .take(3)
          .map(name =>
            Icon(
              _.name := name,
              marginRight := "1em",
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
    ),
    DemoPanel("Icon with Custom Font Icon (since 2.23.0)")(
      //-- Begin: Icon with Custom Font Icon (since 2.23.0)
      div(
        // Any font icon library (e.g. Font Awesome) can be used, here we simply use unicode characters
        List("★", "♥", "☀").map(char =>
          Icon(
            _.accessibleName := s"Font icon $char",
            _.showTooltip    := true,
            _.slots.fontIcon := span(char),
            marginRight      := "1em",
            fontSize         := "1.5rem"
          )
        )
      )
      //-- End
    )
  )

}
