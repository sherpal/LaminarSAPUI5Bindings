package demo

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object TabContainerExample extends Example("TabContainer") {

  def webComponent: WebComponent = TabContainer

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic TabContainer")(
      //-- Begin: Basic TabContainer
      TabContainer(
        width := "100%",
        _.tab(
          _.text := "Tab 1",
                      Label(              "100.1. These Magic rules apply to any Magic game with two or more players, including two-player games and multiplayer games."
            )
        ),
        _.tab(
          _.text := "Tab 2",
          _.icon := IconName.activities,
          _.selected := true,
          Label("100.1a A two-player game is a game that begins with only two players.")
        ),
        _.tab(
          _.text := "Tab 3",
          _.icon := IconName.add,
                      Label(              "100.1b A multiplayer game is a game that begins with more than two players. See section 8, " +
                "“Multiplayer Rules.”"
            )
        ),
        _.tab(
          _.text := "Tab 4",
          _.icon := IconName.calendar,
                      Label(              "100.2. To play, each player needs their own deck of traditional Magic cards, small items to represent" +
                " any tokens and counters, and some way to clearly track life totals."
            )
        ),
        _.tabSeparator,
        _.tab(
          _.text := "Tab 5",
          _.icon := IconName.`action-settings`,
                      Label(              "100.2a In constructed play (a way of playing in which each player creates their own deck ahead of " +
                "time), each deck has a minimum deck size of 60 cards. A constructed deck may contain any number of " +
                "basic land cards and no more than four of any card with a particular English name other than basic " +
                "land cards. For the purposes of deck construction, cards with interchangeable names have the same " +
                "English name (see rule 201.3)."
            )
        )
      )
      //-- End
    ),
    DemoPanel("TabContainer with text only tabs")(
      //-- Begin: TabContainer with text only tabs
      TabContainer(
        _.collapsed := true,
        _.fixed := true,
        _.tab(_.text := "Home"),
        _.tab(_.text := "What's new", _.selected := true),
        _.tab(_.text := "Who are we"),
        _.tab(_.text := "About"),
        _.tab(_.text := "Contacts")
      )
      //-- End
    ),
    DemoPanel("Text only End Overflow")(
      //-- Begin: Text only End Overflow
      TabContainer(
        width := "100%",
        _.collapsed := true,
        _.fixed := true,
        (1 to 23).toList.map(index => TabContainer.tab(_.text := s"Tab $index", _.selected := (index == 13)))
      )
      //-- End
    ),
    DemoPanel("Text only Start and End Overflow")(
      //-- Begin: Text only Start and End Overflow
      TabContainer(
        width := "100%",
        _.collapsed := true,
        _.fixed := true,
        _.tabsOverflowMode := TabsOverflowMode.StartAndEnd,
        (1 to 33).toList.map(index => TabContainer.tab(_.text := s"Tab $index", _.selected := (index == 17)))
      )
      //-- End
    ),
    DemoPanel("TabContainer with text and additional-text")(
      //-- Begin: TabContainer with text and additional-text
      TabContainer(
        _.collapsed := true,
        _.fixed := true,
        _.tabsOverflowMode := TabsOverflowMode.StartAndEnd,
        _.tab(_.text := "Info", _.additionalText := "3"),
        _.tab(_.text := "Attachments", _.additionalText := "24", _.selected := true),
        _.tab(_.text := "Notes", _.additionalText := "16"),
        _.tab(_.text := "People", _.additionalText := "34")
      )
      //-- End
    ),
    DemoPanel("TabContainer with nested tabs")(
      //-- Begin: TabContainer with nested tabs
      TabContainer(
        _.collapsed := true,
        _.tab(_.text := "Nodes", "Notes go here ..."),
        _.tab(
          _.text := "Products",
          "Products go here ...",
          _.slots.subTabs := TabContainer.tab(
            _.text := "Computers",
            "Computers go here ...",
            _.slots.subTabs := TabContainer.tab(_.text := "Laptops", "Laptops go here ..."),
            _.slots.subTabs := TabContainer.tab(
              _.text := "Desktops",
              _.slots.subTabs := TabContainer.tab(_.text := "Work Stations", "Work Stations go here ..."),
              _.slots.subTabs := TabContainer.tab(_.text := "Game Stations", "Game Stations to here"),
              "Desktops go here ..."
            )
          )
        ),
        _.tab(
          _.text := "Orders",
          "Orders go here ...",
          _.slots.subTabs := TabContainer.tab(_.text := "Attachments", "Attachments go here ...")
        )
      )
      //-- End
    )
  )

}
