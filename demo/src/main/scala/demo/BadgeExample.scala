package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example}

object BadgeExample extends Example("Badge") {

  private val someTexts = List(
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

  def component: HtmlElement = div(
    DemoPanel("Basic badge")(
      //-- Begin: Basic badge
      div(
        ColourScheme.allValues
          .zip(someTexts)
          .map((colourScheme, text) => Badge(_.colourScheme := colourScheme, _ => text)),
        Badge(_ => width := "200px", _ => "This text is very long and it will be truncated with ellipsis")
      )
      //-- End
    ),
    DemoPanel("Badge with icon")(
      //-- Begin: Badge with icon
      div(
        Badge(_.colourScheme := ColourScheme._1, _.slots.icon := Icon(_.name := IconName.add), _ => "Add"),
        Badge(_.colourScheme := ColourScheme._2, _.slots.icon := Icon(_.name := IconName.customer))
      )
      //-- End
    )
  )

}
