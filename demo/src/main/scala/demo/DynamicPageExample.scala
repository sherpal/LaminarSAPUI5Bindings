package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}
import demo.helpers.MTG

object DynamicPageExample extends Example("DynamicPage") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic example") {
      val currentlySelectedVar = Var(MTG.cards.head)
      //-- Begin: Basic example
      DynamicPage(
        height       := "500px",
        _.showFooter := true,
        _.slots.titleArea := DynamicPageTitle(
          _.slots.breadcrumbs := Breadcrumbs(
            _.item(_.href := "#", "Magic the Gathering"),
            _.item(_.href := "#", "Cards")
          ),
          _.slots.heading := Title("Magic the Gathering cards"),
          _.slots.snappedHeading := div(
            Avatar(
              _.shape       := AvatarShape.Square,
              _.icon        := IconName.card,
              _.colorScheme := AvatarColorScheme.Accent5,
              _.size        := AvatarSize.S
            ),
            Title("Magic the Gathering cards")
          ),
          _.slots.subheading        := p("MTG"),
          _.slots.snappedSubheading := p("MTG"),
          Tag(_.colourScheme := ColourScheme._7, _.wrappingType := WrappingType.None, "Taggerino"),
          _.slots.actionsBar := Toolbar(
            _.design := ToolbarDesign.Transparent,
            _.button(_.text   := "Create"),
            _.button(_.design := ButtonDesign.Transparent, _.text := "Edit"),
            _.button(_.design := ButtonDesign.Transparent, _.text := "Paste")
          ),
          _.slots.navigationBar := Toolbar(
            _.design := ToolbarDesign.Transparent,
            _.button(_.design := ButtonDesign.Transparent, _.icon := IconName.share),
            _.button(_.design := ButtonDesign.Transparent, _.icon := IconName.`action-settings`)
          )
        ),
        _.slots.headerArea <-- currentlySelectedVar.signal.map { card =>
          DynamicPageHeader(
            display.flex,
            justifyContent.spaceBetween,
            Avatar(
              _.shape       := AvatarShape.Square,
              _.icon        := IconName.laptop,
              _.colorScheme := AvatarColorScheme.Accent5
            ),
            div(
              display.flex,
              alignContent.start,
              justifyContent.spaceBetween,
              div(Label("Name"), p(card.name)),
              div(Label("Type"), p(card.tpe)),
              div(Label("Cost"), p(card.cost))
            )
          )
        },
        UList(
          _.selectionMode := ListMode.Single,
          MTG.cards.map { card =>
            UList.item(
              _.description    := card.comment,
              _.icon           := IconName.`slim-arrow-right`,
              _.iconEnd        := true,
              _.additionalText := card.cost,
              card.name,
              dataAttr("access") := card.name.replaceAll(" ", "-")
            )
          },
          _.events.onItemClick
            .map(_.detail.item.dataset("access"))
            .map(cardName =>
              MTG.cards.find(_.name.replaceAll(" ", "-") == cardName).get
            ) --> currentlySelectedVar.writer
        )
      )
      //-- End
    }
  )

}
