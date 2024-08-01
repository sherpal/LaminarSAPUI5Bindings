package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object DialogExample extends Example("Dialog") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic Dialog") {
      //-- Begin: Basic Dialog
      val openDialogBus: EventBus[Boolean] = new EventBus
      div(
        styleTagForLoginFormClass,
        Button(
          _.design := ButtonDesign.Emphasized,
          "Open Dialog",
          _.events.onClick.mapTo(true) --> openDialogBus.writer
        ),
        Dialog(
          _.showFromEvents(openDialogBus.events.filter(identity).mapTo(())),
          _.closeFromEvents(openDialogBus.events.map(!_).filter(identity).mapTo(())),
          _.headerText := "Register Form",
          sectionTag(
            className := loginFormClass,
            div(
              Label(_.forId := "username", _.required := true, "Username:"),
              Input(_.id    := "username")
            ),
            div(
              Label(_.forId := "password", _.required := true, "Password:"),
              Input(_.id    := "password", _.tpe      := InputType.Password, _.valueState := ValueState.Negative)
            ),
            div(
              Label(_.forId := "email", _.required := true, "Email:"),
              Input(_.id    := "email", _.tpe      := InputType.Email)
            )
          ),
          _.slots.footer := div(
            div(flex := "1"),
            Button(
              _.design := ButtonDesign.Emphasized,
              "Register",
              _.events.onClick.mapTo(false) --> openDialogBus.writer
            )
          )
        )
      )
      //-- End
    },
    DemoPanel("Draggable and Resizable Dialog") {
      //-- Begin: Draggable and Resizable Dialog
      val openDialogBus: EventBus[Boolean] = new EventBus
      div(
        Button(
          "Open Draggable/Resizable dialog",
          _.events.onClick.mapTo(true) --> openDialogBus.writer
        ),
        Dialog(
          _.open      <-- openDialogBus.events,
          _.headerText := "Draggable/Resizable dialog",
          sectionTag(
            "Resize this dialog by dragging it by its resize handle.",
            br(),
            "This feature is available only on Desktop",
            br(),
            "Move this dialog around the screen by dragging it by its header",
            br(),
            "This feature is available only on Desktop"
          ),
          _.slots.footer := div(
            div(flex := "1"),
            Button(
              _.design := ButtonDesign.Emphasized,
              "Close",
              _.events.onClick.mapTo(false) --> openDialogBus.writer
            )
          ),
          _.draggable := true,
          _.resizable := true
        )
      )
      //-- End
    }
  )

}
