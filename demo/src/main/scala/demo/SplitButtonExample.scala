package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}
import org.scalajs.dom

object SplitButtonExample extends Example("SplitButton") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Default SplitButton") {
      //-- Begin: Default SplitButton
      div(SplitButton(_ => "Default"), SplitButton(_.disabled := true, _ => "Default"))
      //-- End
    },
    DemoPanel("SplitButton with Design") {
      //-- Begin: SplitButton with Design
      div(ButtonDesign.allValues.map(design => SplitButton(_.design := design, _ => design.value)))
      //-- End
    },
    DemoPanel("SplitButton with Icons") {
      //-- Begin: SplitButton with Icons
      div(
        SplitButton(_ => "Icon", _.icon := IconName.add),
        SplitButton(_ => "Icon + Active Icon", _.icon := IconName.add, _.activeIcon := IconName.accept)
      )
      //-- End
    },
    DemoPanel("SplitButton opening Popover on arrow-click") {
      //-- Begin: SplitButton opening Popover on arrow-click
      val arrowClickBus: EventBus[dom.HTMLElement] = new EventBus

      div(
        Popover(
          _.showAtFromEvents(arrowClickBus.events),
          _ => "Put whatever you want do show on arrow-click."
        ),
        SplitButton(
          _ => "Expand ->",
          _.events.onArrowClick.map(_.target) --> arrowClickBus.writer
        )
      )
      //-- End
    }
  )

}
