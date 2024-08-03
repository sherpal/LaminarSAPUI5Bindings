package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}
import demo.helpers.MTG

object MediaGalleryExample extends Example("MediaGallery") {

  //-- Begin Common
  def fiveMagicCards = MTG.cardImages.values.toVector
    .take(5)
    .map(link => MediaGallery.item(img(src := link)))
  //-- End Common

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Usage") {
      //-- Begin: Usage
      div(width := "800px", MediaGallery(_.showAllThumbnails := true, fiveMagicCards))
      //-- End
    },
    DemoPanel("MediaGallery with vertical layout") {
      //-- Begin: MediaGallery with vertical layout
      div(
        width := "800px",
        MediaGallery(_.layout := MediaGalleryLayout.Vertical, _.showAllThumbnails := true, fiveMagicCards)
      )
      //-- End
    },
    DemoPanel("MediaGallery with thumbnails on the right") {
      //-- Begin: MediaGallery with thumbnails on the right
      div(
        width := "800px",
        MediaGallery(
          _.layout              := MediaGalleryLayout.Horizontal,
          _.menuHorizontalAlign := MediaGalleryMenuHorizontalAlign.Right,
          _.showAllThumbnails   := true,
          fiveMagicCards
        )
      )
      //-- End
    },
    mtgImageWarning
  )

}
