package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}
import demo.helpers.MTG
import be.doeraene.webcomponents.ui5.datatypes.AccessibilityAttributes

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
      //-- Begin: Avatar with 3 initials (since 1.9.0)
      div(AvatarSize.allValues.map { size =>
        Avatar(_.size := size, _.initials := ('A', 'b', 'C'))
      })
      //-- End
    },
    DemoPanel("Avatar with badges (since 2.19.0)") {
      //-- Begin: Avatar with badges (since 2.19.0)
      div(
        Avatar(_.size := AvatarSize.M, sherpal, _.slots.badge := AvatarBadge(_.icon := IconName.edit)),
        Avatar(
          _.size        := AvatarSize.M,
          _.initials    := ('J', 'D'),
          _.slots.badge := AvatarBadge(_.icon := IconName.accept, _.state := ValueState.Positive)
        ),
        Avatar(
          _.size        := AvatarSize.M,
          _.icon        := IconName.employee,
          _.slots.badge := AvatarBadge(_.icon := IconName.alert, _.state := ValueState.Critical)
        ),
        Avatar(
          _.size     := AvatarSize.M,
          _.initials := ('A', 'B'),
          _.slots.badge := AvatarBadge(
            _.icon    := IconName.decline,
            _.state   := ValueState.Negative,
            _.tooltip := "Rejected"
          )
        ),
        Avatar(
          _.size        := AvatarSize.M,
          _.initials    := ('X', 'Y'),
          _.slots.badge := AvatarBadge(_.icon := IconName.information, _.colourScheme := ColourScheme._6)
        )
      )
      //-- End
    },
    DemoPanel("Avatar modes (since 2.20.0)") {
      //-- Begin: Avatar modes (since 2.20.0)
      val clickCountVar = Var(0)
      div(
        Avatar(_.mode := AvatarMode.Image, _.initials  := ('I', 'M'), _.accessibleName := "Image avatar"),
        Avatar(_.mode := AvatarMode.Decorative, _.icon := IconName.employee),
        Avatar(
          _.mode                    := AvatarMode.Interactive,
          _.initials                := ('C', 'K'),
          _.accessibleName          := "Click me",
          _.accessibilityAttributes := AccessibilityAttributes(hasPopup = AriaHasPopup.Menu),
          _.events.onClick --> (_ => clickCountVar.update(_ + 1))
        ),
        span(child.text <-- clickCountVar.signal.map(count => s"Interactive avatar clicked $count time(s)."))
      )
      //-- End
    }
  )

}
