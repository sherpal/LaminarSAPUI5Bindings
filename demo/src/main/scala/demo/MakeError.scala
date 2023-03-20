package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object MakeError extends Example("MakeError") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    p("press this button then have a look in the dev console at the stacktrace. You should see links to .scala files"),
    br(),
    Button(
      _.design := ButtonDesign.Negative, "Default", 
      onClick --> Observer{_ => throw new Exception("")}
    ),
  )

}
