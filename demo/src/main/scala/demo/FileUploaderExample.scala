package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}
import org.scalajs.dom

object FileUploaderExample extends Example("FileUploader") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Upload multiple images") {
      //-- Begin: Upload multiple images
      // contains the selected images. Starts with None before the user chose files
      val selectedImagesVar: Var[Option[List[dom.File]]] = Var(None)

      div(
        div(
          FileUploader(
            _.accept   := List("image/*"),
            _.multiple := true,
            _.events.onChange.map(_.target.files) --> selectedImagesVar.writer.contramapSome,
            Button("Upload Images", _.icon := IconName.upload)
          )
        ),
        div(
          className := "results-container",
          styleTag("""
          |.results-container > img {
          |  margin: 0.5rem;
          |}
          |""".stripMargin),
          children <-- selectedImagesVar.signal.changes.collect { case Some(files) => files }.map {
            case Nil => List(span("No files selected."))
            case files =>
              files.map { file =>
                img(
                  widthAttr  := 100,
                  heightAttr := 100,
                  src        := dom.URL.createObjectURL(file),
                  inContext(el => onLoad --> Observer[Any](_ => dom.URL.revokeObjectURL(el.ref.src)))
                )
              }
          }
        )
      )
      //-- End
    },
    DemoPanel("File Uploader With No Input") {
      //-- Begin: File Uploader With No Input
      FileUploader(_.hideInput := true, Button("Upload File"))
      //-- End
    },
    DemoPanel("Custom File Uploaders") {
      //-- Begin: Custom File Uploaders
      div(
        FileUploader(_.hideInput := true, Avatar(_.icon := IconName.upload)),
        FileUploader(_.hideInput := true, Tag("Upload File"))
      )
      //-- End
    },
    DemoPanel("Button With Icon File Uploader") {
      //-- Begin: Button With Icon File Uploader
      div(
        FileUploader(Button(_.icon := IconName.upload, "Upload")),
        FileUploader(Button(_.endIcon := IconName.upload, "Upload")),
        FileUploader(Button(_.icon := IconName.upload, _.iconOnly := true))
      )
      //-- End
    }
  )

}
