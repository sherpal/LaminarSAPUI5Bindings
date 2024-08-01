package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub, MTG}

object CarouselExample extends Example("Carousel") {

  //-- Begin Common
  private def threeMagicCards = List(
    img(src := MTG.cardImages("Black Lotus")),
    img(src := MTG.cardImages("Ancestral Recall")),
    img(src := MTG.cardImages("Time Walk"))
  )
  //-- End Common

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    styleTag("""
    |ui5-carousel > img {
    |  max-width: 500px
    |}
    |""".stripMargin),
    DemoPanel("Carousel With Single Item per Page")(
      //-- Begin: Carousel With Single Item per Page
      Carousel(threeMagicCards*)
      //-- End
    ),
    DemoPanel("Carousel with Multiple items per Page")(
      //-- Begin: Carousel with Multiple items per Page
      Carousel(
        _.itemsPerPage := Carousel.ItemsPerPage(1, 2, 2, 2),
        // Before Version 2.0.0:
        // _.itemsPerPageS := 1,
        // _.itemsPerPageM := 2,
        // _.itemsPerPageL := 2,
        MTG.manaSymbolsShortNames.map(name => img(src := MTG.manaSymbolsRefs(name), alt := name))
      )
      //-- End
    ),
    DemoPanel("Carousel With Arrow Placement and Cyclic")(
      //-- Begin: Carousel With Arrow Placement and Cyclic
      Carousel(
        threeMagicCards ++ List(_.arrowsPlacement := CarouselArrowsPlacement.Navigation, _.cyclic := true)*
      )
      //-- End
    ),
    mtgImageWarning
  )

}
