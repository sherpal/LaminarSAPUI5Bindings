package demo

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object TitleExample extends Example("Title") {

  def webComponent: WebComponent = Title

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Title in All Available Levels")(
      //-- Begin: Title in All Available Levels
      div(TitleLevel.allValues.map(level => Title(_.level := level, s"Title level ${level.value.tail}")))
      //-- End
    )
  )

}
