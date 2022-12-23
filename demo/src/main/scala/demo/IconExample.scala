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
    MessageStrip(_.design := MessageStripDesign.Information,
      div(
        p("Using the icons in this demo required to add the following code."),
        pre(
          someIconValues.map(name =>
            s"""
               |@js.native
               |@JSImport("@ui5/webcomponents-icons/dist/$name.js", JSImport.Default)
               |object `$name` extends js.Object
               |`$name`
               |""".stripMargin).mkString("\n")
        ),
        p("You can also evaluate `be.doeraene.webcomponents.ui5.AllIconsImport` if you prefer.")
      ))
  )

}
