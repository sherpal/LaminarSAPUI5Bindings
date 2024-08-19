package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub, MTG}

object CheckBoxExample extends Example("CheckBox") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    maxWidth := "calc(100% - 300px)",
    DemoPanel("Basic CheckBox")(
      //-- Begin: Basic CheckBox
      div(
        CheckBox(_.text := "Chocolate", _.checked  := true),
        CheckBox(_.text := "Strawberry", _.checked := true),
        CheckBox(_.text := "Waffles", _.checked    := true, _.valueState := ValueState.Negative),
        CheckBox(_.text := "Cake", _.checked       := true, _.valueState := ValueState.Critical)
      )
      //-- End
    ),
    DemoPanel("CheckBox states")(
      //-- Begin: CheckBox states
      div(
        for {
          valueState      <- ValueState.allValues
          isDisabled      <- List(false, true)
          isReadonly      <- List(true, false)
          isIndeterminate <- List(true, false)
          text = s"${valueState.value}" ++ (if isDisabled then " disabled" else "") ++ (if isReadonly then " readonly"
                                                                                        else "") ++ (if isIndeterminate then
                                                                                                       " indeterminate"
                                                                                                     else "")
        } yield CheckBox(
          _.text          := text,
          _.valueState    := valueState,
          _.indeterminate := isIndeterminate,
          _.readonly      := isReadonly,
          _.disabled      := isDisabled,
          _.checked       := true
        )
      )
      //-- End
    ),
    DemoPanel("CheckBox text is wrapped by default since 2.0")(
      //-- Begin: CheckBox text is wrapped by default since 2.0
      div(
        CheckBox(
          _.text := "ui5-checkbox set and some very long text that will be wrapped. Before 2.0, you needed to explicity set the wrapping-type to Normal.",
          width := "200px"
        ),
        CheckBox(
          _.text := "Another ui5-checkbox with very long text here",
          width  := "200px"
        ),
        CheckBox(
          _.text         := "Here the text is cropped because we set wrapping-type to None",
          _.wrappingType := WrappingType.None
        )
      )
      //-- End
    ),
    DemoPanel("CheckBox with indeterminate") {
      //-- Begin: CheckBox with indeterminate
      val texts = List("English", "German", "French")

      /** [[Var]] containing the state of all checkboxes. The few following lines help to keep that in sync. */
      val textsStatusesVar = Var(Map("English" -> true, "German" -> false, "French" -> false))

      val textsStatusesUpdater    = textsStatusesVar.updater[(String, Boolean)](_ + _)
      val textsStatusesAllUpdater = textsStatusesVar.updater[Boolean]((map, b) => map.map((key, _) => key -> b))

      val numberOfCheckedSignal = textsStatusesVar.signal.map(_.values.count(identity))

      div(
        CheckBox(
          _.text           := "Select / deselect all",
          _.indeterminate <-- numberOfCheckedSignal.map(count => count != 0 && count != 3),
          _.checked       <-- numberOfCheckedSignal.map(_ > 0),
          // mapToChecked is possible but a bit hacky
          _.events.onChange.mapToChecked --> textsStatusesAllUpdater
        ),
        hr(),
        texts.map(text =>
          CheckBox(
            _.text     := text,
            _.checked <-- textsStatusesVar.signal.map(_(text)),
            // map(_.target.checked) is completely typesafe
            _.events.onChange.map(_.target.checked).map(text -> _) --> textsStatusesUpdater
          )
        )
      )
      //-- End
    }
  )

}
