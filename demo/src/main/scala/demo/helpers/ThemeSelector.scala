package demo.helpers

import com.raquo.laminar.api.L.*
import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.theming.Theming

object ThemeSelector {

  /** Allows to select the theme for the web-components.
    *
    * That does not take care of the "rest" of the ui (for example, the general background colour, or text colours...)
    *
    * You would need to use the `selectedChoiceVar` current value and adjust remaining css when relevant.
    */
  def apply(): HtmlElement = {

    val themeChoices = Vector(
      "sap_fiori_3",
      "sap_fiori_3_dark"
    )

    val selectedChoiceVar = Var(themeChoices(0))

    Select(
      themeChoices.map { theme =>
        Select.option(
          theme,
          _.value     := theme,
          _.selected <-- selectedChoiceVar.signal.map(_ == theme)
        )
      },
      _.events.onChange.map(_.detail.selectedOption.maybeValue.getOrElse(themeChoices(0))) --> selectedChoiceVar.writer,
      selectedChoiceVar.signal.changes --> Observer(Theming.setTheme)
    )

  }

  // registering the themes
  Theming.WebComponentsAssets
  Theming.WebComponentsFioriAssets
  Theming.WebComponentsCompatAssets

}
