package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object MediaGalleryExample extends Example("MediaGallery") {

  //-- Begin Common
  def fiveMagicWallpapers = List(
    "https://media.magic.wizards.com/images/wallpaper/senseis-divining-top-2x2-background-1280x960.jpg",
    "https://media.magic.wizards.com/images/wallpaper/mana-vault-2x2-background-1280x960.jpg",
    "https://media.magic.wizards.com/images/wallpaper/sparas_headquarters_kieran_yanner_1280x960_poozxbqpcw.jpg",
    "https://media.magic.wizards.com/images/wallpaper/baldurs-gate-clb-background-1280x960.jpg",
    "https://media.magic.wizards.com/images/wallpaper/1280x960-neo-ukiyo-e-plains.jpg"
  ).map(link => MediaGallery.item(_ => img(src := link)))
  //-- End Common

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Usage") {
      //-- Begin: Usage
      div(width := "800px", MediaGallery(_.showAllThumbnails := true, _ => fiveMagicWallpapers))
      //-- End
    },
    DemoPanel("MediaGallery with vertical layout") {
      //-- Begin: MediaGallery with vertical layout
      div(
        width := "800px",
        MediaGallery(_.layout := MediaGalleryLayout.Vertical, _.showAllThumbnails := true, _ => fiveMagicWallpapers)
      )
      //-- End
    },
    DemoPanel("MediaGallery with thumbnails on the right") {
      //-- Begin: MediaGallery with thumbnails on the right
      div(
        width := "800px",
        MediaGallery(
          _.layout := MediaGalleryLayout.Horizontal,
          _.menuHorizontalAlign := MediaGalleryMenuHorizontalAlign.Right,
          _.showAllThumbnails := true,
          _ => fiveMagicWallpapers
        )
      )
      //-- End
    },
    mtgImageWarning
  )

}
