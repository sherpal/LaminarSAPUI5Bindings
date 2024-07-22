package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object BadgeExample extends Example("Badge") {

  private val someTexts = LazyList
    .continually(
      List(
        "Hello",
        "3",
        "I'm cool",
        "Some value",
        "Mathematics",
        "available 2",
        "some stuff that is nice",
        "8",
        "there you go",
        "the last"
      )
    )
    .flatten

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic badge")(
      //-- Begin: Basic badge
      div(
        ColourScheme.allValues
          .zip(someTexts)
          .map((colourScheme, text) => Badge(_.colourScheme := colourScheme, text)),
        BadgeDesign.allValues
          .zip(someTexts)
          .map((design, text) => Badge(_.design := design, text)),
        Badge(width := "200px", "This text is very long and it will be truncated with ellipsis")
      )
      //-- End
    ),
    DemoPanel("Badge with icon")(
      //-- Begin: Badge with icon
      div(
        Badge(_.colourScheme := ColourScheme._1, _.slots.icon := Icon(_.name := IconName.add), "Add"),
        Badge(_.colourScheme := ColourScheme._2, _.slots.icon := Icon(_.name := IconName.customer))
      )
      //-- End
    )
  )

}
