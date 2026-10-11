package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}
import org.scalajs.dom.HTMLElement

object MenuExample extends Example("Menu") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic Menu") {
      //-- Begin: Basic Menu
      // feed the bus to open or close the menu
      val openMenuBus: EventBus[Boolean] = new EventBus

      val menuOpenerId = "basic-menu-opener-id"

      div(
        Button(idAttr := menuOpenerId, "Open Menu", _.events.onClick.mapTo(true) --> openMenuBus.writer),
        Menu(
          _.open    <-- openMenuBus.events,
          _.openerId := menuOpenerId,
          _.item(_.text := "New File", _.icon   := IconName.`add-document`),
          _.item(_.text := "New Folder", _.icon := IconName.`add-folder`, _.disabled := true),
          _.separator(),
          _.item(_.text := "Open", _.icon := IconName.`open-folder`),
          _.item(_.text := "Close"),
          _.separator(),
          _.item(_.text := "Preferences", _.icon := IconName.`action-settings`),
          _.item(_.text := "Exit", _.icon        := IconName.`journey-arrive`)
        )
      )
      //-- End
    },
    DemoPanel("Menu with Sub-menu items") {
      //-- Begin: Menu with Sub-menu items
      // feed the bus to open or close the menu
      val openMenuBus: EventBus[Boolean] = new EventBus

      val menuOpenerId = "menu-with-sub-menu-items-opener-id"

      div(
        Button(idAttr := menuOpenerId, "Open Menu", _.events.onClick.mapTo(true) --> openMenuBus.writer),
        Menu(
          _.open    <-- openMenuBus.events,
          _.openerId := menuOpenerId,
          _.item(_.text := "New File", _.icon   := IconName.`add-document`, _.additionalText := "Ctrl+N"),
          _.item(_.text := "New Folder", _.icon := IconName.`add-folder`, _.disabled         := true),
          _.separator(),
          _.item(
            _.text := "Open",
            _.icon := IconName.`open-folder`,
            _.item(
              _.text := "Open Locally",
              _.icon := IconName.`open-folder`,
              _.item(_.text := "Open from C"),
              _.item(_.text := "Open from D"),
              _.item(_.text := "Open from E")
            ),
            _.item(_.text := "Open from Cloud")
          ),
          _.item(
            _.text := "Save",
            _.icon := IconName.save,
            _.item(
              _.text := "Save Locally",
              _.icon := IconName.save,
              _.item(_.text := "Save from C", _.icon := IconName.save),
              _.item(_.text := "Save from D", _.icon := IconName.save),
              _.item(_.text := "Save from E", _.icon := IconName.save)
            )
          ),
          _.item(_.text := "Close"),
          _.separator(),
          _.item(_.text := "Preferences", _.icon := IconName.`action-settings`),
          _.item(_.text := "Exit", _.icon        := IconName.`journey-arrive`)
        )
      )
      //-- End
    },
    DemoPanel("Menu with checkable item groups (since 2.12.0)") {
      //-- Begin: Menu with checkable item groups (since 2.12.0)
      val openMenuBus: EventBus[Boolean] = new EventBus

      val menuOpenerId = "menu-with-item-groups-opener-id"

      val alignments = List(
        "Left"   -> IconName.`text-align-left`,
        "Center" -> IconName.`text-align-center`,
        "Right"  -> IconName.`text-align-right`
      )
      val alignmentVar  = Var("Left")
      val formattingVar = Var(Set("Bold"))

      div(
        Button(idAttr := menuOpenerId, "Text options", _.events.onClick.mapTo(true) --> openMenuBus.writer),
        div(
          child.text <-- alignmentVar.signal.combineWith(formattingVar.signal).map { (alignment, formatting) =>
            s"Alignment: $alignment, formatting: ${formatting.toList.sorted.mkString(", ")}"
          }
        ),
        Menu(
          _.open    <-- openMenuBus.events,
          _.openerId := menuOpenerId,
          _.group(
            _.checkMode := MenuItemGroupCheckMode.Single,
            alignments.map((alignment, icon) =>
              MenuItem(
                _.text := alignment,
                _.icon := icon,
                _.checked <-- alignmentVar.signal.map(_ == alignment),
                _.events.onCheck.filter(_.target.checked).mapTo(alignment) --> alignmentVar.writer
              )
            )
          ),
          _.separator(),
          _.group(
            _.checkMode := MenuItemGroupCheckMode.Multiple,
            List("Bold", "Italic", "Underline").map(formatting =>
              MenuItem(
                _.text := formatting,
                _.checked <-- formattingVar.signal.map(_.contains(formatting)),
                _.events.onCheck.map(_.target.checked) --> formattingVar.updater[Boolean] { (current, checked) =>
                  if checked then current + formatting else current - formatting
                }
              )
            )
          )
        )
      )
      //-- End
    },
    DemoPanel("Menu placement (since 2.16.0)") {
      //-- Begin: Menu placement (since 2.16.0)
      val openMenuBus: EventBus[Boolean]   = new EventBus
      val clickedItemBus: EventBus[String] = new EventBus

      val menuOpenerId = "menu-placement-opener-id"

      div(
        Button(idAttr := menuOpenerId, "Open Menu on the right", _.events.onClick.mapTo(true) --> openMenuBus.writer),
        Menu(
          _.open      <-- openMenuBus.events,
          _.openerId   := menuOpenerId,
          _.placement  := PopoverPlacementType.End,
          _.headerText := "Actions",
          _.events.onItemClick.map(_.detail.text) --> clickedItemBus.writer,
          _.item(_.text := "Edit", _.icon := IconName.edit, _.tooltip := "Edit the document"),
          _.item(_.text := "Share", _.icon := IconName.share, _.tooltip := "Share the document"),
          _.item(_.text := "Delete", _.icon := IconName.delete, _.highlight := ValueState.Negative)
        ),
        Toast(_.showFromTextEvents(clickedItemBus.events.map(text => s"Clicked on $text")))
      )
      //-- End
    }
  )

}
