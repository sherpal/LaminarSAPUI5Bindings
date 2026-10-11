package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}
import org.scalajs.dom

object ResponsivePopoverExample extends Example("ResponsivePopover") {

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
          _.open      <-- closePopoverBus.events.mapTo(false),
          _.headerText := "Newsletter subscription",
          div(
            className := loginFormClass,
            styleTagForLoginFormClass,
            div(
              Label(_.forId := "emailInput", _.required    := true, "Email"),
              Input(_.id    := "emailInput", _.placeholder := "Enter Email", _.tpe := InputType.Email)
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
    },
    DemoPanel("Resizable ResponsivePopover with Placement") {
      //-- Begin: Resizable ResponsivePopover with Placement
      val openerId    = "resizable-responsive-popover-opener"
      val openPopover = Var(false)
      div(
        Button(idAttr := openerId, "Open below", _.events.onClick.mapTo(true) --> openPopover.writer),
        ResponsivePopover(
          _.openerId            := openerId,
          _.open               <-- openPopover.signal,
          _.headerText          := "Resizable",
          _.placement           := PopoverPlacementType.Bottom,
          _.resizable           := true,
          _.preventInitialFocus := true,
          _.events.onClose.mapTo(false) --> openPopover.writer,
          p("On desktop, drag the resize handle to change the size of this popover. On phones, this is a dialog.")
        )
      )
      //-- End
    }
  )

}
