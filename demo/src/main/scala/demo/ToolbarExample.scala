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
    ),
    DemoPanel("Toolbar with any component (since 2.20.0)")(
      //-- Begin: Toolbar with any component (since 2.20.0)
      Toolbar(
        _.item(Input(_.placeholder := "Search...")),
        _.item(CheckBox(_.text := "Only mine")),
        _.item(
          _.overflowPriority := ToolbarItemOverflowBehavior.NeverOverflow,
          Switch(_.textOn := "On", _.textOff := "Off")
        ),
        _.spacer(),
        _.button(_.icon := IconName.add, _.text := "Add"),
        _.button(_.icon := IconName.delete, _.text := "Delete")
      )
      //-- End
    ),
    DemoPanel("Overflow priorities and groups")(
      //-- Begin: Overflow priorities and groups
      div(
        maxWidth := "450px",
        Toolbar(
          _.alignContent := ToolbarAlign.Start,
          _.button(
            _.icon             := IconName.home,
            _.text             := "Home (never overflows)",
            _.overflowPriority := ToolbarItemOverflowBehavior.NeverOverflow
          ),
          _.button(_.icon := IconName.edit, _.text := "Edit", _.showOverflowText := true),
          _.separator(),
          _.button(_.icon := IconName.copy, _.text  := "Copy (clipboard group)", _.overflowGroup  := "clipboard"),
          _.button(_.icon := IconName.paste, _.text := "Paste (clipboard group)", _.overflowGroup := "clipboard"),
          _.button(
            _.icon             := IconName.`action-settings`,
            _.text             := "Settings (always overflows)",
            _.overflowPriority := ToolbarItemOverflowBehavior.AlwaysOverflow
          ),
          _.button(
            _.icon                   := IconName.refresh,
            _.text                   := "Refresh (keeps the popover open)",
            _.overflowPriority       := ToolbarItemOverflowBehavior.AlwaysOverflow,
            _.preventOverflowClosing := true
          )
        )
      )
      //-- End
    ),
    DemoPanel("Toolbar select with label and value") {
      //-- Begin: Toolbar select with label and value
      val selectedValueVar = Var("medium")
      div(
        Toolbar(
          _.alignContent := ToolbarAlign.Start,
          _.select(
            _.slots.label := Label("Size:"),
            _.value <-- selectedValueVar.signal,
            _.events.onChange.map(_.detail.selectedToolbarOption.toOption.flatMap(_.value.toOption)) -->
              Observer[Option[String]](_.foreach(selectedValueVar.set)),
            List("small" -> "Small", "medium" -> "Medium", "large" -> "Large").map((value, text) =>
              Toolbar.select.option(_.value := value, text)
            )
          ),
          _.button(_.text := "Reset", _.events.onClick.mapTo("medium") --> selectedValueVar.writer)
        ),
        div(child.text <-- selectedValueVar.signal.map(value => s"Selected value: $value"))
      )
      //-- End
    }
  )

}
