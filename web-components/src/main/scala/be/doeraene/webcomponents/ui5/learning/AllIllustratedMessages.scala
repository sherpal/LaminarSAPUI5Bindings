package be.doeraene.webcomponents.ui5.learning

import be.doeraene.webcomponents.ui5.IllustratedMessage
import be.doeraene.webcomponents.ui5.configkeys.IllustrationMessageType
import com.raquo.laminar.api.L.*

object AllIllustratedMessages {

  def apply(): HtmlElement = div(
    className := "AllIllustratedMessages",
    IllustrationMessageType.allValues.map { messageType =>
      div(
        messageType.value,
        IllustratedMessage(_.name := messageType)
      )
    },
    pre(
      IllustrationMessageType.allValues
        .map(_.importStatement)
        .map(imp => s"""import "$imp"""")
        .mkString("\n")
    )
  )

}
