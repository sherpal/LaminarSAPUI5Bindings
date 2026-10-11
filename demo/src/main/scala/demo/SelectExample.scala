package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub, MTG}

object SelectExample extends Example("Select") {

  //-- Begin Common
  val someCountries: List[String]    = List("Austria", "Belgium", "Bulgaria", "Germany", "United Kingdom", "Kazakhstan")
  val someCountryCodes: List[String] = List("AT", "BE", "BG", "DE", "UK", "KZ")
  //-- End Common

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic Select")(
      //-- Begin: Basic Select
      div(
        Select(
          _.value := "Desktop",
          _.option(_.icon := IconName.iphone, "Phone"),
          _.option(_.icon := IconName.ipad, "Tablet"),
          _.option(_.icon := IconName.laptop, "Desktop")
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
      Select(
        someCountries
          .zip(someCountryCodes)
          .map((country, code) =>
            Select.option(
              _.additionalText := code,
              country
            )
          )
      )
      //-- End
    ),
    DemoPanel("Read-only Select with Text Separator")(
      //-- Begin: Read-only Select with Text Separator
      div(
        SelectTextSeparator.allValues.map(separator =>
          Select(
            _.readonly      := true,
            _.textSeparator := separator,
            _.value         := "Belgium",
            marginRight     := "1em",
            someCountries.zip(someCountryCodes).map((country, code) => Select.option(_.additionalText := code, country))
          )
        )
      )
      //-- End
    ),
    DemoPanel("Select with Option Groups")(
      //-- Begin: Select with Option Groups
      Select(
        _.value := "Carrot",
        _.optionGroup(
          _.headerText := "Fruits",
          _.option("Apple"),
          _.option("Banana"),
          _.option("Cherry")
        ),
        _.optionGroup(
          _.headerText := "Vegetables",
          _.option("Carrot"),
          _.option("Leek"),
          _.option("Potato")
        )
      )
      //-- End
    ),
    DemoPanel("Select with Live Change") {
      //-- Begin: Select with Live Change
      val previewedVar: Var[String] = Var(someCountries.head)
      val selectedVar: Var[String]  = Var(someCountries.head)
      div(
        Select(
          _.value <-- selectedVar.signal,
          someCountries.map(country => Select.option(country)),
          _.events.onLiveChange.map(_.detail.selectedOption.textContent) --> previewedVar.writer,
          _.events.onChange.map(_.detail.selectedOption.textContent) --> selectedVar.writer
        ),
        div(
          marginTop := "0.5rem",
          "Previewed (navigate with arrow keys in the opened list): ",
          child.text <-- previewedVar.signal,
          ", selected: ",
          child.text <-- selectedVar.signal
        )
      )
      //-- End
    },
    DemoPanel("Keeping Select in sync with Signal") {
      //-- Begin: Keeping Select in sync with Signal
      val currentCountryVar = Var(someCountries.head)

      def select = Select(
        _.events.onChange.map(_.detail.selectedOption.dataset.get("name").get) --> currentCountryVar.writer,
        marginRight := "1em",
        _.value <-- currentCountryVar.signal,
        someCountries.map(country => Select.option(country, dataAttr("name") := country))
      )

      div(
        p("These two select are bound (two-ways) to the same Var and are therefore kept in sync:"),
        select,
        select
      )
      //-- End
    },
    DemoPanel("Adding a bit of abstraction on top of Select") {
      //-- Begin: Adding a bit of abstraction on top of Select
      /** Select instances of type T, based on their key, showing their repr to the user. Selected instance feed all the
        * provided observers.
        */
      def BetterSelect[T](
          options: Seq[T]
      )(key: T => String, repr: T => String, observers: Observer[T]*)(mods: Select.ComponentMod*) = {
        val keysToT = options.map(t => key(t) -> t).toMap
        Select(
          options.map(t => Select.option(repr(t), dataAttr("key") := key(t))) +:
            (_.events.onChange.map(_.detail.selectedOption.dataset.get("key").get).map(keysToT.apply) --> Observer
              .combine(
                observers*
              )) +:
            mods*
        )
      }

      val selectedCardVar = Var(MTG.cards.head)

      div(
        div(
          Label("Select a card: "),
          BetterSelect(MTG.cards)(_.name, card => s"${card.name} (${card.cost})", selectedCardVar.writer)()
        ),
        div(
          "You selected ",
          child.text <-- selectedCardVar.signal.map(_.name),
          "."
        )
      )
      //-- End
    },
    DemoPanel("Custom Options") {
      //-- Begin: Custom Options
      def customOption(displayText: String, fullText: String) =
        Select.optionCustom(
          _.displayText := displayText,
          div(
            display.flex,
            justifyContent.spaceBetween,
            width := "100%",
            Icon(_.name := IconName.soccer),
            fullText,
            Icon(_.name := IconName.employee)
          )
        )

      Select(
        customOption("BE", "Belgium"),
        customOption("CH", "Switzerland"),
        customOption("FR", "France")
      )
      //-- End
    }
  )

}
