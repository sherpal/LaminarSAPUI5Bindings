package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object TagExample extends Example("Tag") {

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
    DemoPanel("Basic tag")(
      //-- Begin: Basic tag
      div(
        ColourScheme.allValues
          .zip(someTexts)
          .map((colourScheme, text) => Tag(_.colourScheme := colourScheme, text)),
        TagDesign.allValues
          .zip(someTexts)
          .map((design, text) => Tag(_.design := design, text)),
        Tag(
          width := "200px",
          "This text is very long and it will be truncated with ellipsis",
          _.wrappingType := WrappingType.None
        )
      )
      //-- End
    ),
    DemoPanel("Tag with icon")(
      //-- Begin: Tag with icon
      div(
        Tag(_.colourScheme := ColourScheme._1, _.slots.icon := Icon(_.name := IconName.add), "Add"),
        Tag(_.colourScheme := ColourScheme._2, _.slots.icon := Icon(_.name := IconName.customer))
      )
      //-- End
    )
  )

}
