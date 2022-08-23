package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object BreadcrumbsExample extends Example("Breadcrumbs") {
  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Standard Breadcrumbs")(
      //-- Begin: Standard Breadcrumbs
      Breadcrumbs(
        _.Item(
          _.href := "https://github.com/sherpal/LaminarSAPUI5Bindings",
          _.target := LinkTarget._blank,
          "Root page"
        ),
        _.Item(_.href := "https://github.com/sherpal/LaminarSAPUI5Bindings", "Parent page"),
        _.Item("Current page"),
        _.events.onItemClick.map(_.detail.item) --> Observer(x => org.scalajs.dom.console.log(x))
      )
      //-- End
    ),
    DemoPanel("Breadcrumbs with no current page")(
      //-- Begin: Breadcrumbs with no current page
      Breadcrumbs(
        _.design := BreadcrumbsDesign.NoCurrentPage,
        _.Item(
          _.href := "https://github.com/sherpal/LaminarSAPUI5Bindings",
          _.target := LinkTarget._blank,
          "Root page"
        ),
        _.Item(_.href := "https://github.com/sherpal/LaminarSAPUI5Bindings", "Parent page")
      )
      //-- End
    ),
    DemoPanel("Breadcrumbs with specific separator")(
      //-- Begin: Breadcrumbs with specific separator
      div(
        BreadcrumbsSeparatorStyle.allValues.map(separatorStyle =>
          Breadcrumbs(
            _.separatorStyle := separatorStyle,
            _.Item(
              _.href := "https://github.com/sherpal/LaminarSAPUI5Bindings",
              _.target := LinkTarget._blank,
              "Root page"
            ),
            _.Item(_.href := "https://github.com/sherpal/LaminarSAPUI5Bindings", "Parent page"),
            _.Item("Current page")
          )
        )
      )
      //-- End
    )
  )
}
