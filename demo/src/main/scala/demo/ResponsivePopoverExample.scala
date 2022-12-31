package demo

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}
import org.scalajs.dom

object ResponsivePopoverExample extends Example("ResponsivePopover") {

  def webComponent: WebComponent = ResponsivePopover

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic ResponsivePopover") {
      //-- Begin: Basic ResponsivePopover
      val openPopoverBus: EventBus[dom.HTMLElement] = new EventBus
      val closePopoverBus: EventBus[Unit]           = new EventBus

      div(
        Button(
          _.design := ButtonDesign.Emphasized,
          "Open Popover",
          _.events.onClick.map(_.target) --> openPopoverBus.writer
        ),
        ResponsivePopover(
          _.showAtFromEvents(openPopoverBus.events),
          _.closeFromEvents(closePopoverBus.events),
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
              _.events.onClick.mapTo(()) --> closePopoverBus.writer
            )
          )
        )
      )
      //-- End
    }
  )

}
