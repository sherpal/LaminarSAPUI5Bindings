package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object TitleExample extends Example("Title") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Title in All Available Sizes")(
      //-- Begin: Title in All Available Sizes
      div(TitleLevel.allValues.map(level => Title(_.size := level, s"Title level ${level.value.tail}")))
      //-- End
    )
  )

}
