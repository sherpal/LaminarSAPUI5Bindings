package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*

object AvatarExample {

  private def sherpal = img(src := "/images/avatars/sherpal.png", alt := "sherpal")

  def apply(): HtmlElement = div(
    div(
      h2("Basic examples"),
      div(Avatar(_ => sherpal), Avatar(_.shape := AvatarShape.Square, _ => sherpal))
    ),
    div(
      h2("Avatar sizes"),
      div(AvatarSize.allValues.map(size => Avatar(_.size := size, _ => sherpal)))
    ),
    div(
      h2("Avatar with icons"),
      div(AvatarSize.allValues.zip(IconName.allValues).map((size, icon) => Avatar(_.size := size, _.icon := icon)))
    ),
    div(
      h2("Avatar with initials"),
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
