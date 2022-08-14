package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object StepInputExample extends Example("StepInput") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    //-- Begin Common
    styleTag("""
    |.shorter {
    |  width: 300px;
    |}
    |""".stripMargin)
    //-- End Common
    ,
    DemoPanel("Basic Step Input") {
      //-- Begin: Basic Step Input
      div(
        className := "shorter",
        StepInput(_.value := 5),
        StepInput(_.readonly := true, _.value := 5),
        StepInput(_.disabled := true, _.value := 5)
      )
      //-- End
    },
    DemoPanel("Step Input with alignment") {
      //-- Begin: Step Input with alignment
      div(
        className := "shorter",
        StepInput(_.value := 5),
        StepInput(_.value := 5, _ => textAlign := "center"),
        StepInput(_.value := 5, _ => textAlign := "right")
      )
      //-- End
    },
    DemoPanel("Step Input with min, max, step and valuePrecision") {
      //-- Begin: Step Input with min, max, step and valuePrecision
      div(
        className := "shorter",
        StepInput(_.value := 5, _.min := 0, _.max := 10, _.step := 1),
        StepInput(_.value := 0, _.min := -100, _.max := 100, _.step := 10),
        StepInput(_.value := 10, _.min := 0, _.max := 20, _.valuePrecision := 1)
      )
      //-- End
    },
    DemoPanel("Step Input with Value State") {
      //-- Begin: Step Input with Value State
      div(
        className := "shorter",
        ValueState.allValues.map(state => StepInput(_.valueState := state, _ => marginTop := "0.5rem"))
      )
      //-- End
    }
  )

}
