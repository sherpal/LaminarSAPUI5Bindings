package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object RadioButtonExample extends Example("RadioButton") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic RadioButton Types") {
      //-- Begin: Basic RadioButton Types
      val texts = LazyList.from(0).map('A' + _).map(_.toChar).map("Option " ++ _.toString)
      val name  = "GroupA"

      div(
        RadioButton(_.text := texts(0), _.checked := true, _.name := name),
        RadioButton(_.text := texts(1), _.valueState := ValueState.None, _.name := name),
        RadioButton(_.text := texts(2), _.valueState := ValueState.Warning, _.name := name),
        RadioButton(_.text := texts(6), _.disabled := true, _.name := name),
        RadioButton(_.text := texts(7), _.readonly := true, _.name := name)
      )
      //-- End
    },
    DemoPanel("RadioButton in group - navigate via [UP/Right] and [DOWN/Left] arrow keys") {
      //-- Begin: RadioButton in group - navigate via [UP/Right] and [DOWN/Left] arrow keys
      val selectedValueVar: Var[String] = Var("None")

      div(
        h1("Group of states"),
        Label(child.text <-- selectedValueVar.signal.map(state => s"Selected radio: $state")),
        div(
          display := "flex",
          flexDirection := "column",
          ValueState.allValues.map(state =>
            RadioButton(
              _.name := "GroupB",
              _.text := state.value,
              _.events.onChange.mapToChecked.filter(identity).mapTo(state.value) --> selectedValueVar.writer,
              _.checked <-- selectedValueVar.signal.map(_ == state.value),
              _.valueState := state
            )
          )
        )
      )
      //-- End
    },
    DemoPanel("RadioButton with Text Wrapping")(
      //-- Begin: RadioButton with Text Wrapping
      div(
        RadioButton(
          width := "300px",
          _.text := "ui5-radio-button with 'wrapping-type=Normal' set and some long text",
          _.wrappingType := WrappingType.Normal,
          _.name := "GroupD"
        ),
        RadioButton(
          width := "200px",
          _.text := "Another ui5-radio-button with very long text here",
          _.wrappingType := WrappingType.Normal,
          _.name := "GroupD"
        )
      )
      //-- End
    )
  )

}
