package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.DemoPanel

object BreadcrumbsExample {
  def apply(): HtmlElement = div(
    DemoPanel(
      "Standard Breadcrumbs",
      Breadcrumbs(
        _.Item(
          _.href := "https://github.com/sherpal/LaminarSAPUI5Bindings",
          _.target := LinkTarget._blank,
          _ => "Root page"
        ),
        _.Item(_.href := "https://github.com/sherpal/LaminarSAPUI5Bindings", _ => "Parent page"),
        _.Item(_ => "Current page"),
        _.events.onItemClick --> Observer(x => org.scalajs.dom.console.log(x))
      )
    ),
    DemoPanel(
      "Breadcrumbs with no current page",
      Breadcrumbs(
        _.design := BreadcrumbsDesign.NoCurrentPage,
        _.Item(
          _.href := "https://github.com/sherpal/LaminarSAPUI5Bindings",
          _.target := LinkTarget._blank,
          _ => "Root page"
        ),
        _.Item(_.href := "https://github.com/sherpal/LaminarSAPUI5Bindings", _ => "Parent page")
      )
    ),
    DemoPanel(
      "Breadcrumbs with specific separator",
      div(
        BreadcrumbsSeparatorStyle.allValues.map(separatorStyle =>
          Breadcrumbs(
            _.separatorStyle := separatorStyle,
            _.Item(
              _.href := "https://github.com/sherpal/LaminarSAPUI5Bindings",
              _.target := LinkTarget._blank,
              _ => "Root page"
            ),
            _.Item(_.href := "https://github.com/sherpal/LaminarSAPUI5Bindings", _ => "Parent page"),
            _.Item(_ => "Current page")
          )
        )
      )
    )
  )
}
