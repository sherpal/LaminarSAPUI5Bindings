package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object InputExample extends Example("Input") {

  private val countries = List("Argentina", "Belgium", "Bulgaria", "Canada", "Columbia", "Croatia", "Denmark")

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic Input")(
      //-- Begin: Basic Input
      div(
        Input(_.showClearIcon := true, _.value := "Input"),
        Input(_.readonly := true, _.value := "Readonly Input"),
        Input(_.disabled := true, _.value := "Disabled Input")
      )
      //-- End
    ),
    DemoPanel("Input With Suggestions (note: the usage depends on the framework you are using)") {
      //-- Begin: Input With Suggestions (note: the usage depends on the framework you are using)

      val filterValueBus: EventBus[String] = new EventBus

      val suggestions =
        filterValueBus.events
          .map(input =>
            if input.trim.isEmpty then Nil
            else countries.filter(_.toLowerCase.contains(input.toLowerCase))
          )
          .startWith(Nil)
          .map(
            _.map(country =>
              Input.suggestion(
                _.icon := IconName.world,
                _.additionalText := "explore",
                _.additionalTextState := ValueState.Success,
                _.description := "travel the world",
                _.text := country
              )
            )
          )

      Input(
        _.showSuggestions := true,
        _.showClearIcon := true,
        _.placeholder := "Start typing country name",
        children <-- suggestions,
        _.events.onInput.mapToValue --> filterValueBus.writer
      )
      //-- End
    },
    DemoPanel("Input with Value State")(
      //-- Begin: Input with Value State
      div(ValueState.allValues.map(state => Input(_.value := state.value, _.valueState := state)))
      //-- End
    ),
    DemoPanel("Input with Suggestions and Value State message")(
      //-- Begin: Input with Suggestions and Value State message
      div(
        Input(
          _.placeholder := "Choose content density",
          _.showSuggestions := true,
          _.slots.valueStateMessage := div("This is an error message. Extra long text used as an error message."),
          List("Cozy", "Compact", "Condensed").map(item => UList.item(item))
        )
      )
      //-- End
    ),
    DemoPanel("Input as Search Field") {
      //-- Begin: Input as Search Field
      val searchCriteriaVar: Var[String] = Var("")

      val showSearchResultBus: EventBus[Unit]  = new EventBus
      val closeSearchResultBus: EventBus[Unit] = new EventBus

      div(
        Input(
          _.placeholder := "Enter search criteria",
          width := "100%",
          _.events.onInput.mapToValue --> searchCriteriaVar.writer,
          _.slots.icon := Icon(_.name := IconName.search, onClick.mapTo(()) --> showSearchResultBus.writer)
        ),
        Dialog(
          _.headerText := "Search result",
          child <-- showSearchResultBus.events
            .sample(searchCriteriaVar.signal)
            .map(searchCriteria => div(s"Here would go the results of search for '$searchCriteria'.")),
          _.slots.footer := div(
            Bar(
              _.slots.endContent := Button("Close", _.events.onClick.mapTo(()) --> closeSearchResultBus.writer),
              _.design := BarDesign.Footer
            )
          ),
          inContext(el =>
            showSearchResultBus.events.sample(searchCriteriaVar.signal).filter(_.nonEmpty).mapTo(()) --> Observer(_ =>
              el.ref.show()
            )
          ),
          inContext(el => closeSearchResultBus.events --> Observer(_ => el.ref.close()))
        )
      )
      //-- End
    },
    DemoPanel("Input with Label")(
      //-- Begin: Input with Label
      div(
        className := loginFormClass,
        styleTagForLoginFormClass,
        div(
          Label(_.forId := "myInput", _.required := true, _.showColon := true, "Name"),
          Input(_.id := "myInput", _.placeholder := "Enter your Name", _.required := true)
        ),
        div(
          Label(_.forId := "myPassword", _.required := true, _.showColon := true, "Secret Code"),
          Input(
            _.id := "myPassword",
            _.tpe := InputType.Password,
            _.valueState := ValueState.Error,
            _.placeholder := "Enter your Secret Code",
            _.required := true
          )
        )
      )
      //-- End
    )
  )

}
