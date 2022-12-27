package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}
import demo.helpers.MTG

object AvatarExample extends Example("Avatar") {

  private def sherpal                       = img(src := "images/avatars/sherpal.png", alt := "sherpal")
  private def manaSymbolImage(name: String) = img(src := MTG.manaSymbolsRefs(name), alt := name)

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic examples") {
      //-- Begin: Basic examples
      div(Avatar(sherpal), Avatar(_.shape := AvatarShape.Square, sherpal))
      //-- End
    },
    DemoPanel("Avatar sizes") {
      //-- Begin: Avatar sizes
      div(
        AvatarSize.allValues
          .zip(MTG.manaSymbolsShortNames)
          .map((size, mana) => Avatar(_.size := size, manaSymbolImage(mana)))
      )
      //-- End
    },
    DemoPanel("Avatar with icons") {
      //-- Begin: Avatar with icons
      div(AvatarSize.allValues.zip(someIconValues).map((size, icon) => Avatar(_.size := size, _.icon := icon)))
      //-- End
    },
    DemoPanel("Avatar with initials") {
      //-- Begin: Avatar with initials
      div(AvatarSize.allValues.map { size =>
        val initials: AvatarInitials = size.value.toList.take(2) match {
          case c :: Nil        => c
          case c1 :: c2 :: Nil => (c1, c2)
          case _               => throw new RuntimeException("Impossible")
        }
        Avatar(_.size := size, _.initials := initials)
      })
      //-- End
    },
    DemoPanel("Avatar with 3 initials (since 1.9.0)") {
      //-- Begin: Avatar with 3 initials
      div(AvatarSize.allValues.map { size =>
        Avatar(_.size := size, _.initials := ('A', 'b', 'C'))
      })
      //-- End
    }
  )

}
