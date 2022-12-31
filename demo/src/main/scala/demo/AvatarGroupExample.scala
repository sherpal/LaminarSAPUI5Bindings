package demo

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}
import org.scalajs.dom

object AvatarGroupExample extends Example("AvatarGroup") {

  def webComponent: WebComponent = AvatarGroup

  def component(using demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo): HtmlElement = div(
    DemoPanel("Avatar Group - type 'Individual'") {
      //-- Begin: Avatar Group - type 'Individual'
      AvatarGroup(
        _.tpe := AvatarGroupType.Individual,
        someIconValues.take(5).map(icon => Avatar(_.icon := icon, _.size := AvatarSize.M))
      )
      //-- End
    },
    DemoPanel("Avatar Group - type 'Group'") {
      //-- Begin: Avatar Group - type 'Group'
      AvatarGroup(
        _.tpe := AvatarGroupType.Group,
        someIconValues.take(5).map(icon => Avatar(_.icon := icon, _.size := AvatarSize.M))
      )
      //-- End
    },
    DemoPanel("Avatar Group with overflow") {
      //-- Begin: Avatar Group with overflow
      def allAvatars = someIconValues.take(10).map(icon => Avatar(_.icon := icon, _.size := AvatarSize.M))

      val avatarsForPopoverBus: EventBus[List[HtmlElement]] = new EventBus
      val popoverOpenerBus: EventBus[dom.HTMLElement]       = new EventBus

      div(
        AvatarGroup(
          _.tpe := AvatarGroupType.Individual,
          allAvatars,
          width := "400px",
          _.events.onClick.map(_.target.hiddenItems.length).map(allAvatars.takeRight) --> avatarsForPopoverBus.writer,
          _.events.onClick.filter(_.detail.overflowButtonClicked).map(_.detail.targetRef) --> popoverOpenerBus.writer
        ),
        Popover(
          _.showAtFromEvents(popoverOpenerBus.events),
          children <-- avatarsForPopoverBus
        )
      )
      //-- End
    }
  )

}
