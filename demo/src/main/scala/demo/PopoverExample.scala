package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example}
import org.scalajs.dom.HTMLElement

object PopoverExample extends Example("Popover") {

  def component: HtmlElement = div(
    DemoPanel("Basic Popover") {
      //-- Begin: Basic Popover
      val openPopoverBus: EventBus[Option[HTMLElement]] = new EventBus

      div(
        Button(
          _ => "Open Popover",
          _.design := ButtonDesign.Emphasized,
          _.events.onClick.map(_.target).map(Some(_)) --> openPopoverBus.writer
        ),
        Popover(
          _ =>
            inContext(el =>
              openPopoverBus.events --> Observer[Option[HTMLElement]] {
                case Some(element) => el.ref.showAt(element)
                case None          => el.ref.close()
              }
            ),
          _.headerText := "Newsletter subscription",
          _ =>
            div(
              className := loginFormClass,
              styleTagForLoginFormClass,
              div(
                Label(_.forId := "emailInput", _.required := true, _ => "Email"),
                Input(_.id := "emailInput", _.placeholder := "Enter Email", _.tpe := InputType.Email)
              )
            ),
          _.slots.footer := div(
            div(flex := "1"),
            Button(
              _.design := ButtonDesign.Emphasized,
              _ => "Subscribe",
              _.events.onClick.mapTo(None) --> openPopoverBus.writer
            )
          )
        )
      )
      //-- End
    }
  )

}
