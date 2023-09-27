package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import be.doeraene.webcomponents.ui5.scaladsl.csssize.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object ToolbarExample extends Example("Toolbar") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic")(
      //-- Begin: Basic
      Toolbar(
        _.button(_.icon := IconName.decline, _.text  := "Mid 2"),
        _.button(_.icon := IconName.add, _.text      := "Right 1"),
        _.button(_.icon := IconName.employee, _.text := "Right 4"),
        _.button(_.icon := IconName.employee, _.text := "Call me later")
      )
      //-- End
    ),
    DemoPanel("With Spacer")(
      //-- Begin: With Spacer
      Toolbar(
        _.button(
          _.icon   := IconName.add,
          _.text   := "Left 1 (long)",
          _.width  := 150.px,
          _.design := ButtonDesign.Default
        ),
        _.button(_.icon := IconName.decline, _.text  := "Left 2"),
        _.button(_.icon := IconName.employee, _.text := "Left 3"),
        _.select(
          _.option("1"),
          _.option(_.selected := true, "2"),
          _.option("3")
        ),
        _.button(_.icon := IconName.add, _.text := "Mid 1"),
        _.spacer(),
        _.button(_.icon := IconName.decline, _.text  := "Mid 2"),
        _.button(_.icon := IconName.add, _.text      := "Right 1"),
        _.button(_.icon := IconName.employee, _.text := "Right 2"),
        _.button(_.icon := IconName.employee, _.text := "Call me later")
      )
      //-- End
    ),
    DemoPanel("With Separator")(
      //-- Begin: With Separator
      Toolbar(
        _.button(
          _.icon   := IconName.add,
          _.text   := "Left 1 (long)",
          _.width  := 150.px,
          _.design := ButtonDesign.Default
        ),
        _.button(_.icon := IconName.decline, _.text  := "Left 2"),
        _.button(_.icon := IconName.employee, _.text := "Left 3"),
        _.select(
          _.option("1"),
          _.option(_.selected := true, "2"),
          _.option("3")
        ),
        _.button(_.icon := IconName.add, _.text := "Mid 1"),
        _.separator(),
        _.button(_.icon := IconName.decline, _.text  := "Mid 2"),
        _.button(_.icon := IconName.add, _.text      := "Right 1"),
        _.button(_.icon := IconName.employee, _.text := "Right 2"),
        _.button(_.icon := IconName.employee, _.text := "Call me later")
      )
      //-- End
    ),
    DemoPanel("With 'Start' aligned Items")(
      //-- Begin: With 'Start' aligned Items
      Toolbar(
        _.alignContent := ToolbarAlign.Start,
        _.button(_.icon := IconName.decline, _.text  := "Left 1"),
        _.button(_.icon := IconName.add, _.text      := "Left 2"),
        _.button(_.icon := IconName.employee, _.text := "Left 3"),
        _.button(_.icon := IconName.employee, _.text := "Call me later")
      )
      //-- End
    )
  )

}
