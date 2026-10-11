package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object NumberInputExample extends Example("NumberInput") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    //-- Begin Common
    styleTag("""
    |.number-inputs > * {
    |  width: 300px;
    |  margin-bottom: 0.5rem;
    |  display: block;
    |}
    |""".stripMargin),
    //-- End Common
    MessageStrip(
      _.design := MessageStripDesign.Information,
      "The NumberInput component is experimental in UI5 and its API may change."
    ),
    DemoPanel("Basic Number Input") {
      //-- Begin: Basic Number Input
      div(
        className := "number-inputs",
        NumberInput(_.value := 5),
        NumberInput(_.readonly := true, _.value := 5),
        NumberInput(_.disabled := true, _.value := 5)
      )
      //-- End
    },
    DemoPanel("Number Input with min, max, step and valuePrecision") {
      //-- Begin: Number Input with min, max, step and valuePrecision
      div(
        className := "number-inputs",
        NumberInput(_.value := 5, _.min := 0, _.max := 10, _.step := 1),
        NumberInput(_.value := 0, _.min := -100, _.max := 100, _.step := 10),
        NumberInput(_.value := 10, _.min := 0, _.max := 20, _.step := 0.5, _.valuePrecision := 1)
      )
      //-- End
    },
    DemoPanel("Number Input with Value State") {
      //-- Begin: Number Input with Value State
      div(
        className := "number-inputs",
        ValueState.allValues.map(state => NumberInput(_.valueState := state))
      )
      //-- End
    },
    DemoPanel("Number Input bound to a Var") {
      //-- Begin: Number Input bound to a Var
      val valueVar: Var[Double]          = Var(3)
      val valueStateVar: Var[ValueState] = Var(ValueState.None)
      div(
        className := "number-inputs",
        NumberInput(
          _.min    := 0,
          _.max    := 10,
          _.value <-- valueVar.signal,
          _.events.onChange.map(_.target.value) --> valueVar.writer,
          _.events.onValueStateChange.map(_.detail.valueState) --> valueStateVar.writer
        ),
        div("Current value: ", child.text <-- valueVar.signal.map(_.toString)),
        div("Last value state: ", child.text <-- valueStateVar.signal.map(_.value))
      )
      //-- End
    }
  )

}
