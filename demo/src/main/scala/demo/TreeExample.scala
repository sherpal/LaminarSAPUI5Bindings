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
          _.text := "Tree 1",
          _.icon := IconName.paste,
          _.selected := true,
          _.item(
            _.expanded := true,
            _.text := "Tree 1.1",
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
          _.text := "Tree 3 (no icon)",
          _.expanded := true
        )
      )
      //-- End
    ),
    DemoPanel("Tree with multiple selection")(
      //-- Begin: Tree with multiple selection
      Tree(
        _.mode := ListMode.MultiSelect,
        _.events.onSelectionChange
          .map(_.detail.selectedItems) --> Observer[List[TreeItem.Ref]](_.foreach(dom.console.log(_))),
        width := "100%",
        _.item(
          _.expanded := true,
          _.text := "Tree 1",
          _.icon := IconName.paste,
          _.selected := true,
          _.item(
            _.expanded := true,
            _.text := "Tree 1.1",
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
          _.text := "Tree 3 (no icon)",
          _.expanded := true
        )
      )
      //-- End
    )
  )

}
