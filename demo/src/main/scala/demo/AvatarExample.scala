package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example}
import demo.helpers.MTG

object AvatarExample extends Example("Avatar") {

  private def sherpal                       = img(src := "/images/avatars/sherpal.png", alt := "sherpal")
  private def manaSymbolImage(name: String) = img(src := MTG.manaSymbolsRefs(name), alt := name)

  def component: HtmlElement = div(
    DemoPanel(
      "Basic examples",
      div(Avatar(_ => sherpal), Avatar(_.shape := AvatarShape.Square, _ => sherpal))
    ),
    DemoPanel(
      "Avatar sizes",
      div(
        AvatarSize.allValues
          .zip(MTG.manaSymbolsNames)
          .map((size, mana) => Avatar(_.size := size, _ => manaSymbolImage(mana)))
      )
    ),
    DemoPanel(
      "Avatar with icons",
      div(AvatarSize.allValues.zip(IconName.allValues).map((size, icon) => Avatar(_.size := size, _.icon := icon)))
    ),
    DemoPanel(
      "Avatar with initials",
      div(AvatarSize.allValues.map { size =>
        val initials: AvatarInitials = size.value.toList.take(2) match {
          case c :: Nil        => c
          case c1 :: c2 :: Nil => (c1, c2)
          case _               => throw new RuntimeException("Impossible")
        }
        Avatar(_.size := size, _.initials := initials)
      })
    )
  )

}
