package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}
import org.scalajs.dom.HTMLElement

object PopoverExample extends Example("Popover") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic Popover") {
      //-- Begin: Basic Popover
      val openPopoverBus: EventBus[Option[HTMLElement]] = new EventBus

      div(
        Button(
          "Open Popover",
          _.design := ButtonDesign.Emphasized,
          _.events.onClick.map(_.target).map(Some(_)) --> openPopoverBus.writer
        ),
        Popover(
          _.showAtFromEvents(openPopoverBus.events.collect { case Some(opener) => opener }),
          _.closeFromEvents(openPopoverBus.events.collect { case None => () }),
          _.headerText := "Newsletter subscription",
          div(
            className := loginFormClass,
            styleTagForLoginFormClass,
            div(
              Label(_.forId := "emailInput", _.required := true, "Email"),
              Input(_.id := "emailInput", _.placeholder := "Enter Email", _.tpe := InputType.Email)
            )
          ),
          _.slots.footer := div(
            div(flex := "1"),
            Button(
              _.design := ButtonDesign.Emphasized,
              "Subscribe",
              _.events.onClick.mapTo(None) --> openPopoverBus.writer
            )
          )
        )
      )
      //-- End
    }
  )

}
