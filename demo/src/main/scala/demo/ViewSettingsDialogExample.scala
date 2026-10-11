package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}
import scala.scalajs.js.JSON

object ViewSettingsDialogExample extends Example("ViewSettingsDialog") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    MessageStrip(
      _.design := MessageStripDesign.Information,
      "Using the ViewSettingsDialog is straightforward on paper as shown below. However, don't be fooled. You still" +
        " need to process by hand whatever it spits out. Given it's dynamic nature, it's not as trivial as it " +
        "may seem. (But perhaps adding some magic on top would make it more delightful to use.)"
    ),
    DemoPanel("Usage") {
      //-- Begin: Usage
      val settingsBus: EventBus[ViewSettingsDialog.ViewSettings] = new EventBus
      val showSettingsDialogBus: EventBus[Unit]                  = new EventBus
      div(
        Button("Open ViewSettingsDialog", _.events.onClick.mapTo(()) --> showSettingsDialogBus.writer),
        ViewSettingsDialog(
          _.open <-- showSettingsDialogBus.events.mapTo(true),
          _.events.onCancel.map(_.detail) --> settingsBus.writer,
          _.events.onConfirm.map(_.detail) --> settingsBus.writer,
          _.slots.sortItems := List(
            SortItem(_.text := "Name", _.selected := true),
            SortItem(_.text := "Position"),
            SortItem(_.text := "Company"),
            SortItem(_.text := "Department")
          ),
          _.slots.filterItems := List(
            FilterItem(
              _.text         := "Position",
              _.slots.values := List("CTO", "CPO", "VP").map(position => FilterItem.option(_.text := position))
            ),
            FilterItem(
              _.text         := "Department",
              _.slots.values := List("Sales", "Management", "PR").map(position => FilterItem.option(_.text := position))
            ),
            FilterItem(
              _.text := "Location",
              _.slots.values := List("Walldorf", "New York", "London").map(position =>
                FilterItem.option(_.text := position)
              )
            ),
            FilterItem(
              _.text         := "Report to",
              _.slots.values := List("CTO", "CPO", "VP").map(position => FilterItem.option(_.text := position))
            )
          )
        ),
        pre(child.text <-- settingsBus.events.map(settings => JSON.stringify(settings, space = 2))),
        pre(child.text <-- settingsBus.events.map(settings => settings.filters.toString))
      )
      //-- End
    },
    DemoPanel("Grouping and custom tab") {
      //-- Begin: Grouping and custom tab
      val openVar             = Var(false)
      val showInactiveVar     = Var(false)
      val confirmedTextVar    = Var("Nothing confirmed yet")
      val customTabChangedVar = Var(false)
      div(
        Button("Open ViewSettingsDialog", _.events.onClick.mapTo(true) --> openVar.writer),
        ViewSettingsDialog(
          _.open <-- openVar.signal,
          _.events.onClose.mapTo(false) --> openVar.writer,
          // the dialog can't know whether our custom tab changed, so we tell it to enable the reset button
          _.resetEnabled <-- customTabChangedVar.signal,
          _.events.onReset.mapTo(false) --> showInactiveVar.writer,
          _.events.onReset.mapTo(false) --> customTabChangedVar.writer,
          _.events.onConfirm.map { event =>
            val detail = event.detail
            s"Sort by ${detail.sortBy} (${detail.sortOrder}), " +
              s"group by ${detail.groupBy.getOrElse("-")} (descending: ${detail.groupDescending}), " +
              s"show inactive: ${showInactiveVar.now()}"
          } --> confirmedTextVar.writer,
          _.slots.sortItems := List(
            SortItem(_.text := "Name", _.selected := true),
            SortItem(_.text := "Position")
          ),
          _.slots.groupItems := List(
            GroupItem(_.text := "(Not Grouped)"),
            GroupItem(_.text := "Department", _.selected := true),
            GroupItem(_.text := "Location")
          ),
          _.slots.customTabs := ViewSettingsDialogCustomTab(
            _.titleText := "Display Options",
            _.tooltip   := "Display Options",
            _.icon      := IconName.`show-edit`,
            div(
              padding := "1rem",
              CheckBox(
                _.text := "Show inactive employees",
                _.checked <-- showInactiveVar.signal,
                _.events.onChange.map(_.target.checked) --> showInactiveVar.writer,
                _.events.onChange.mapTo(true) --> customTabChangedVar.writer
              )
            )
          )
        ),
        div(Text(child.text <-- confirmedTextVar.signal))
      )
      //-- End
    }
  )

}
