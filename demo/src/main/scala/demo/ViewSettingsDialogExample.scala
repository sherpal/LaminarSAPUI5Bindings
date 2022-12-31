package demo

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

import scala.scalajs.js.JSON

object ViewSettingsDialogExample extends Example("ViewSettingsDialog") {

  def webComponent: WebComponent = ViewSettingsDialog

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
          _.showFromEvents(showSettingsDialogBus.events),
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
              _.text := "Position",
              _.slots.values := List("CTO", "CPO", "VP").map(position => FilterItem.option(_.text := position))
            ),
            FilterItem(
              _.text := "Department",
              _.slots.values := List("Sales", "Management", "PR").map(position => FilterItem.option(_.text := position))
            ),
            FilterItem(
              _.text := "Location",
              _.slots.values := List("Walldorf", "New York", "London").map(position =>
                FilterItem.option(_.text := position)
              )
            ),
            FilterItem(
              _.text := "Report to",
              _.slots.values := List("CTO", "CPO", "VP").map(position => FilterItem.option(_.text := position))
            )
          )
        ),
        pre(child.text <-- settingsBus.events.map(settings => JSON.stringify(settings, space = 2))),
        pre(child.text <-- settingsBus.events.map(settings => settings.filters.toString))
      )
      //-- End
    }
  )

}
