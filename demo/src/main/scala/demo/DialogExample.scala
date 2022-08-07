package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example}

object DialogExample extends Example("Dialog") {

  def component: HtmlElement = div(
    DemoPanel(
      "Basic Dialog", {
        val openDialogBus: EventBus[Boolean] = new EventBus
        div(
          styleTagForLoginFormClass,
          Button(
            _.design := ButtonDesign.Emphasized,
            _ => "Open Dialog",
            _.events.onClick.mapTo(true) --> openDialogBus.writer
          ),
          div(
            MessageStrip(
              _.design := MessageStripDesign.Information,
              _ =>
                "The opening of this dialog works using an `EventBus`. " +
                  "Clicking on the 'Open Dialog' button writes to the bus, and the Dialog listens to it."
            )
          ),
          Dialog(
            _ =>
              inContext(el => openDialogBus.events --> Observer[Boolean](if _ then el.ref.show() else el.ref.close())),
            _.headerText := "Register Form",
            _ =>
              section(
                className := loginFormClass,
                div(
                  Label(_.forId := "username", _.required := true, _ => "Username:"),
                  Input(_.id := "username")
                ),
                div(
                  Label(_.forId := "password", _.required := true, _ => "Password:"),
                  Input(_.id := "password", _.tpe := InputType.Password, _.valueState := ValueState.Error)
                ),
                div(
                  Label(_.forId := "email", _.required := true, _ => "Email:"),
                  Input(_.id := "email", _.tpe := InputType.Email)
                )
              ),
            _.slots.footer := div(
              div(flex := "1"),
              Button(
                _.design := ButtonDesign.Emphasized,
                _ => "Register",
                _.onClick.mapTo(false) --> openDialogBus.writer
              )
            )
          )
        )
      }
    ),
    DemoPanel(
      "Draggable and Resizable Dialog", {
        val dialogId = "the-dialog-id"
        div(
          Button(
            _ => "Open Draggable/Resizable dialog",
            _.events.onClick.mapTo(Dialog.getDialogById(dialogId)) --> Observer[Option[Dialog.Ref]] {
              case Some(dialog) => dialog.show()
              case None         => throw new IllegalStateException(s"The Dialog with id $dialogId does not exist.")
            }
          ),
          MessageStrip(
            _.design := MessageStripDesign.Information,
            _ => "The opening of this dialog works by finding the dialog by id (with `Dialog.getDialogById`)."
          ),
          Dialog(
            _.id := dialogId,
            _.headerText := "Draggable/Resizable dialog",
            _ =>
              section(
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
                _ => "Close",
                _.onClick.mapTo(Dialog.getDialogById(dialogId)) --> Observer[Option[Dialog.Ref]] {
                  case Some(dialog) => dialog.close()
                  case None         => throw new IllegalStateException(s"Dialog with id $dialogId does not exist.")
                }
              )
            ),
            _.draggable := true,
            _.resizable := true
          )
        )
      }
    )
  )

}
