package demo

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object MultiInputExample extends Example("MultiInput") {

  def webComponent: WebComponent = MultiInput

  //-- Begin Common
  val countries = List("Argentina", "Belgium", "Bulgaria", "Canada", "Columbia", "Croatia", "Denmark")
  //-- End Common

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    styleTagForLoginFormClass,
    DemoPanel("Basic Multi Input") {
      //-- Begin: Basic Multi Input
      val firstValueVar  = Var("basic input")
      val secondValueVar = Var("value help icon")
      div(
        display := "flex",
        className := loginFormClass,
        div(
          Label(
            _.wrappingType := WrappingType.Normal,
            width := "200px",
            "MultiInput",
            child.text <-- firstValueVar.signal.map(value => s" (current value is $value)")
          ),
          MultiInput(
            _.value <-- firstValueVar,
            _.events.onInput.mapToValue --> firstValueVar.writer,
            _.events.onChange.mapToValue --> firstValueVar.writer
          )
        ),
        div(
          Label(
            _.wrappingType := WrappingType.Normal,
            width := "200px",
            "MultiInput",
            child.text <-- secondValueVar.signal.map(value => s" (current value is $value)")
          ),
          MultiInput(
            _.showValueHelpIcon := true,
            _.value <-- secondValueVar,
            _.events.onInput.mapToValue --> secondValueVar.writer,
            _.events.onChange.mapToValue --> secondValueVar.writer
          )
        )
      )
      //-- End
    },
    DemoPanel("Multi Input with tokens") {
      //-- Begin: Multi Input with tokens
      div(
        div(
          MultiInput(_.slots.tokens := MultiInput.token(_.text := "Bulgaria")),
          MultiInput(_.slots.tokens := countries.map(country => MultiInput.token(_.text := country)))
        ),
        MessageStrip(
          _.design := MessageStripDesign.Information,
          "These input are only there for display. They won't add tokens dynamically. See below for such example."
        )
      )
      //-- End
    },
    DemoPanel("Multi Input and token creation onChange") {
      //-- Begin: Multi Input and token creation onChange
      val tokenValuesVar = Var(List("Argentina"))

      val changeBus: EventBus[String] = new EventBus

      // Emits the new values list, with whether or not they should be actually patched to the values
      // This check is required because we want tokens to be unique
      val newValuesWithShouldWeUpdate = changeBus.events
        .withCurrentValueOf(tokenValuesVar.signal)
        .map((newValue, previousValues) => (previousValues :+ newValue, !previousValues.contains(newValue)))

      // emits when token must be changed
      val newValuesChanges = newValuesWithShouldWeUpdate.collect { case (values, true) => values }

      // When the new value was already present, we issue the error message ...
      val valueStateBecomesErrorEvents = newValuesWithShouldWeUpdate.filter(!_._2).mapTo(ValueState.Error)
      // ... and we clear it 2 seconds later
      val valueStateBecomesNormalEvents = valueStateBecomesErrorEvents.delay(2000).mapTo(ValueState.None)

      val valueStateChanges = EventStream.merge(valueStateBecomesErrorEvents, valueStateBecomesNormalEvents)

      MultiInput(
        _.showSuggestions := true,
        _.valueState <-- valueStateChanges,
        width := "50%",
        _.slots.valueStateMessage := div("Token is already in the list"),
        countries.map(country => MultiInput.suggestion(_.text := country)),
        _.slots.tokens <-- tokenValuesVar.signal.map(_.map(tokenValue => MultiInput.token(_.text := tokenValue))),
        _.events.onChange.map(_.target.value) --> changeBus.writer,
        newValuesChanges --> tokenValuesVar.writer,
        _.events.onTokenDelete.map(_.detail.token.text) --> tokenValuesVar.updater((values, toRemove) =>
          values.filterNot(_ == toRemove)
        )
      )
      //-- End
    }
  )

}
