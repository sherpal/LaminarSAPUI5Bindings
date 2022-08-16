package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}
import org.scalajs.dom
import scala.scalajs.js

object UploadCollectionExample extends Example("UploadCollection") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Upload Collection") {
      //-- Begin: Upload Collection
      val allStagedFilesVar: Var[List[(dom.File, js.Date)]] = Var(Nil)

      val newFilesArrivedObserver = allStagedFilesVar.updater[List[(dom.File, js.Date)]](_ ++ _)

      val stagedFilesCount = allStagedFilesVar.signal.map(_.length)

      val uploadAllBus: EventBus[Unit] = new EventBus

      UploadCollection(
        _.slots.header := div(
          Title.h2(_ => child.text <-- stagedFilesCount.map(count => s"Uploaded ($count)")),
          Label(_ => "Add new files and press to start uploading pending files:"),
          child <-- allStagedFilesVar.signal.mapTo(
            // Note that we need to re-create a new file uploader each time, otherwise there is a glitch preventing
            // from adding twice the same group of file in a row. (The glitch actually exists on their official docs)
            FileUploader(
              _.hideInput := true,
              _.multiple := true,
              _ => Button(_.icon := IconName.add, _.design := ButtonDesign.Transparent),
              _.events.onChange.map(_.target.files.map(_ -> new js.Date())) --> newFilesArrivedObserver
            )
          ),
          Button(_ => "Upload all", _.events.onClick.mapTo(()) --> uploadAllBus.writer)
        ),
        _.mode := ListMode.Delete,
        _.events.onItemDelete.map(_.detail.item.dataset("index").toInt) --> allStagedFilesVar.updater[Int](
          _.patch(_, Nil, 1)
        ),
        _ =>
          // This is where the logic of actually uploading files should live
          uploadAllBus.events.sample(allStagedFilesVar.signal) --> Observer[List[(dom.File, js.Date)]](files =>
            files.foreach((file, date) => dom.console.log(file, date))
          ),
        _ =>
          children <-- allStagedFilesVar.signal.map(_.zipWithIndex.map { case ((file, selectedAt), index) =>
            UploadCollection.item(
              _.fileName := file.name,
              _.fileNameClickable := true,
              _ => s"Selected at: $selectedAt",
              _ => dataAttr("index") := index.toString,
              _.slots.thumbnail := img(
                src := dom.URL.createObjectURL(file),
                inContext(el => onLoad --> Observer[Any](_ => dom.URL.revokeObjectURL(el.ref.src)))
              )
            )
          }),
        _.events.onDrop.preventDefault.map(
          _.dataTransfer.files.toList.map(_ -> new js.Date)
        ) --> newFilesArrivedObserver
      )
      //-- End
    },
    DemoPanel("UploadCollection With Drag and Drop and No Initial Data") {
      //-- Begin: UploadCollection With Drag and Drop and No Initial Data
      val allStagedFilesVar: Var[List[(dom.File, js.Date)]] = Var(Nil)

      val newFilesArrivedObserver = allStagedFilesVar.updater[List[(dom.File, js.Date)]](_ ++ _)

      UploadCollection(
        _.slots.header := div(Title.h2(_ => "Attachments")),
        _.events.onDrop.preventDefault.map(
          _.dataTransfer.files.toList.map(_ -> new js.Date)
        ) --> newFilesArrivedObserver,
        _ =>
          children <-- allStagedFilesVar.signal.map(_.zipWithIndex.map { case ((file, selectedAt), index) =>
            UploadCollection.item(
              _.fileName := file.name,
              _.fileNameClickable := true,
              _ => s"Selected at: $selectedAt",
              _ => dataAttr("index") := index.toString,
              _.slots.thumbnail := img(
                src := dom.URL.createObjectURL(file),
                inContext(el => onLoad --> Observer[Any](_ => dom.URL.revokeObjectURL(el.ref.src)))
              )
            )
          })
      )
      //-- End
    }
  )

}
