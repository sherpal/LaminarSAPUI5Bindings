package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object SelectExample extends Example("SelectExample") {

  private val someCountries    = List("Austria", "Belgium", "Bulgaria", "Germany", "United Kingdom", "Kazakhstan")
  private val someCountryCodes = List("AT", "BE", "BG", "DE", "UK", "KZ")

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic Select")(
      //-- Begin: Basic Select
      div(
        Select(
          _.option(_.icon := IconName.iphone, "Phone"),
          _.option(_.icon := IconName.ipad, "Tablet"),
          _.option(_.icon := IconName.laptop, "Desktop", _.selected := true)
        ),
        span(padding := "1em"),
        Select(
          _.disabled := true,
          _.option(_.icon := IconName.iphone, "Phone")
        )
      )
      //-- End
    ),
    DemoPanel("Select with Two-Column Layout Items")(
      //-- Begin: Select with Two-Column Layout Items
      Select(        someCountries
          .zip(someCountryCodes)
          .map((country, code) =>
            Select.option(
              _.additionalText := code,
              country
            )
          )
      )
      //-- End
    )
  )

}
