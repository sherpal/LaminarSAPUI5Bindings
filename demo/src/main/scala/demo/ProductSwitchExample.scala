package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}
import org.scalajs.dom.HTMLElement

object ProductSwitchExample extends Example("ProductSwitch") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic sample")(
      //-- Begin: Basic sample
      ProductSwitch(
        _.item(_.titleText := "Home", _.subtitleText := "Central Home", _.icon := IconName.home),
        _.item(
          _.titleText    := "Analytics Cloud",
          _.subtitleText := "Analystics Could",
          _.icon         := IconName.`business-objects-experience`
        ),
        _.item(_.titleText := "Catalog", _.subtitleText          := "Ariba", _.icon  := IconName.contacts),
        _.item(_.titleText := "Travel & Expense", _.subtitleText := "Concur", _.icon := IconName.flight)
      )
      //-- End
    ),
    DemoPanel("ProductSwitch within ShellBar") {
      //-- Begin: ProductSwitch within ShellBar
      val togglePopoverOpeningEventBus: EventBus[HTMLElement] = new EventBus

      val togglePopoverOpeningEvents = togglePopoverOpeningEventBus.events
        .scanLeft(Option.empty[HTMLElement]) {
          case (Some(_), _)    => None
          case (None, element) => Some(element)
        }
        .changes

      div(
        ShellBar(
          _.primaryTitle      := "Corporate Portal",
          _.secondaryTitle    := "secondary title",
          _.slots.logo        := img(src := "/images/avatars/scala-logo.png"),
          _.showProductSwitch := true,
          _.events.onProductSwitchClick.map(_.detail.targetRef) --> togglePopoverOpeningEventBus.writer
        ),
        Popover(
          _.showAtAndCloseFromEvents(togglePopoverOpeningEvents),
          _.placement := PopoverPlacementType.Bottom,
          ProductSwitch(
            _.item(_.titleText := "Home", _.subtitleText := "Central Home", _.icon := IconName.home),
            _.item(
              _.titleText    := "Analytics Cloud",
              _.subtitleText := "Analystics Could",
              _.icon         := IconName.`business-objects-experience`
            ),
            _.item(_.titleText := "Catalog", _.subtitleText          := "Ariba", _.icon  := IconName.contacts),
            _.item(_.titleText := "Travel & Expense", _.subtitleText := "Concur", _.icon := IconName.flight)
          )
        )
      )
      //-- End
    },
    DemoPanel("Items with images") {
      //-- Begin: Items with images
      def avatar(initials: (Char, Char)) = Avatar(
        _.initials    := initials,
        _.size        := AvatarSize.S,
        _.shape       := AvatarShape.Square,
        _.colorScheme := AvatarColorScheme.Accent6
      )
      ProductSwitch(
        _.item(_.titleText := "Home", _.subtitleText := "Central Home", _.slots.image := avatar(('C', 'H'))),
        _.item(_.titleText := "Analytics Cloud", _.subtitleText := "Analytics", _.slots.image := avatar(('A', 'C'))),
        _.item(
          _.titleText    := "Scala",
          _.subtitleText := "Logo image",
          _.slots.image  := img(src := "/images/avatars/scala-logo.png", width := "2rem")
        )
      )
      //-- End
    }
  )

}
