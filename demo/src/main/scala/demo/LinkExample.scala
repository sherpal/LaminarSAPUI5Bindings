package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}
import be.doeraene.webcomponents.ui5.datatypes.AccessibilityAttributes

object LinkExample extends Example("Link") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Different Link Designs")(
      //-- Begin: Different Link Designs
      div(
        LinkDesign.allValues.map(design =>
          Link(
            _.href   := "https://www.scala-js.org/",
            _.target := LinkTarget._blank,
            _.design := design,
            s"$design Link",
            marginRight := "3em"
          )
        ),
        Link(_.href := "https://www.scala-js.org/", _.target := LinkTarget._blank, _.disabled := true, "Disabled")
      )
      //-- End
    ),
    DemoPanel("Link with Button Role") {
      //-- Begin: Link with Button Role
      val expandedVar = Var(false)
      div(
        Link(
          _.accessibleRole := LinkAccessibleRole.Button,
          _.accessibilityAttributes <-- expandedVar.signal.map(expanded =>
            AccessibilityAttributes(expanded = expanded, controls = "link-example-details")
          ),
          _.endIcon  <-- expandedVar.signal.map(if _ then IconName.`slim-arrow-up` else IconName.`slim-arrow-down`),
          child.text <-- expandedVar.signal.map(if _ then "Hide details" else "Show details"),
          _.events.onClick --> (_ => expandedVar.update(!_))
        ),
        div(
          idAttr   := "link-example-details",
          display <-- expandedVar.signal.map(if _ then "block" else "none"),
          "Some details that the link-styled button can show or hide."
        )
      )
      //-- End
    }
  )

}
