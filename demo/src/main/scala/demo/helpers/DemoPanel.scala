package demo.helpers

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.facades.highlightjs.{hljs, hljsScala}

object DemoPanel {

  hljs.registerLanguage("scala", hljsScala)

  def apply(title: String)(body: HtmlElement)(using
      demoPanelInfo: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    h2(title),
    div(
      padding := "1em",
      border := "0.0625rem solid #C1C1C1",
      backgroundColor := "#f7f7f7",
      body
    ),
    demoPanelInfo.demoPanelInfo
      .get(title)
      .map(thisExampleInfo =>
        div(
          marginTop := "1em",
          overflowX := "auto",
          border := "0.0625rem solid #C1C1C1",
          backgroundColor := "#f5f6fa",
          padding := "1rem",
          Title.h3(_ => "Source code"),
          pre(
            code(
              className := "language-scala",
              demoPanelInfo.maybeStripIndentCommon.map(_ ++ "\n\n").getOrElse("") ++
                thisExampleInfo.stripIndent,
              onMountCallback(ctx => hljs.highlightElement(ctx.thisNode.ref))
            )
          )
        )
      )
  )
}
