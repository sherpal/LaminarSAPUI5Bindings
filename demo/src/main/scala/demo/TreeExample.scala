package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}
import org.scalajs.dom

object TreeExample extends Example("Tree") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic Tree")(
      //-- Begin: Basic Tree
      Tree(
        width := "100%",
        _.item(
          _.expanded := true,
          _.text     := "Tree 1",
          _.icon     := IconName.paste,
          _.selected := true,
          _.item(
            _.expanded := true,
            _.text     := "Tree 1.1",
            _.selected := true,
            _.item(_.text := "Tree 1.1.1"),
            _.item(_.text := "Tree 1.1.2")
          )
        ),
        _.item(
          _.text := "Tree 2",
          _.icon := IconName.copy,
          _.item(
            _.text := "Tree 2.1",
            _.item(_.text := "Tree 2.1.1"),
            _.item(_.text := "Tree 2.1.2", (1 to 4).toList.map(j => Tree.item(_.text := s"Tree 2.1.2.$j")))
          )
        ),
        _.item(
          _.text     := "Tree 3 (no icon)",
          _.expanded := true
        )
      )
      //-- End
    ),
    DemoPanel("Tree with multiple selection")(
      //-- Begin: Tree with multiple selection
      Tree(
        _.selectionMode := ListMode.Multiple,
        _.events.onSelectionChange
          .map(_.detail.selectedItems) --> Observer[List[TreeItem.Ref]](_.foreach(dom.console.log(_))),
        width := "100%",
        _.item(
          _.expanded := true,
          _.text     := "Tree 1",
          _.icon     := IconName.paste,
          _.selected := true,
          _.item(
            _.expanded := true,
            _.text     := "Tree 1.1",
            _.selected := true,
            _.item(_.text := "Tree 1.1.1"),
            _.item(_.text := "Tree 1.1.2")
          )
        ),
        _.item(
          _.text := "Tree 2",
          _.icon := IconName.copy,
          _.item(
            _.text := "Tree 2.1",
            _.item(_.text := "Tree 2.1.1"),
            _.item(_.text := "Tree 2.1.2", (1 to 4).toList.map(j => Tree.item(_.text := s"Tree 2.1.2.$j")))
          )
        ),
        _.item(
          _.text     := "Tree 3 (no icon)",
          _.expanded := true
        )
      )
      //-- End
    ),
    DemoPanel("Tree items with additional text and indeterminate state")(
      //-- Begin: Tree items with additional text and indeterminate state
      Tree(
        _.selectionMode := ListMode.Multiple,
        _.headerText    := "Partially selected nodes are indeterminate",
        _.item(
          _.expanded            := true,
          _.text                := "Fruits",
          _.icon                := IconName.`nutrition-activity`,
          _.indeterminate       := true,
          _.additionalText      := "1 of 2 selected",
          _.additionalTextState := ValueState.Information,
          _.item(
            _.text                := "Apple",
            _.selected            := true,
            _.additionalText      := "In stock",
            _.additionalTextState := ValueState.Positive
          ),
          _.item(
            _.text                := "Banana",
            _.additionalText      := "Out of stock",
            _.additionalTextState := ValueState.Negative
          )
        ),
        _.item(
          _.text           := "Vegetables (inactive, not clickable)",
          _.tpe            := ListItemType.Inactive,
          _.tooltip        := "This item has type Inactive",
          _.additionalText := "Empty"
        )
      )
      //-- End
    ),
    DemoPanel("Tree with Drag and Drop (since 2.0)") {
      //-- Begin: Tree with Drag and Drop (since 2.0)
      MessageStrip(
        "Looking for an example with Drag and Drop? Check out examples for tabs or lists, they are similar (you can also contribute to make one here!)",
        _.design          := MessageStripDesign.Information,
        _.hideCloseButton := true
      )
      //-- End
    }
  )

}
