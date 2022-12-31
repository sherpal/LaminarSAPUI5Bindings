package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.*
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, JSName}
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.WebComponent

/** The ui5-view-settings-dialog component helps the user to sort data within a list or a table. It consists of several
  * lists like Sort order which is built-in and Sort By and Filter By lists, for which you must be provide
  * items(ui5-sort-item & ui5-filter-item respectively) These options can be used to create sorters for a table. The
  * ui5-view-settings-dialog interrupts the current application processing as it is the only focused UI element and the
  * main screen is dimmed/blocked. The ui5-view-settings-dialog is modal, which means that user action is required
  * before returning to the parent window is possible.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/ViewSettingsDialog/">the doc</a> for more
  *   information.
  */
object ViewSettingsDialog extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def setConfirmedSettings(settings: ViewSettings): Unit = js.native

    def show(): Unit = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/ViewSettingsDialog.js", JSImport.Default)
  object RawImport extends WebComponent.WithMetadata

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-view-settings-dialog")

  lazy val sortDescending: ReactiveHtmlAttr[Boolean] = customHtmlAttr("sort-descending", BooleanAsAttrPresenceCodec)

  object slots {
    val filterItems: Slot = Slot("filterItems")
    val sortItems: Slot   = Slot("sortItems")
  }

  trait ViewSettings extends js.Object {
    def sortOrder: "Ascending" | "Descending"

    def sortBy: String

    def sortDescending: Boolean

    @JSName("filters")
    def filtersJS: js.Array[js.Dictionary[js.Array[String]]]
  }

  object ViewSettings {
    extension (settings: ViewSettings)
      def filters: Map[String, List[String]] =
        settings.filtersJS.flatMap(_.toMap.map((key, values) => (key, values.toList))).toMap
  }

  object events {
    val onBeforeOpen: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("before-open")

    trait HasSortByItem extends js.Object {
      def sortByItem: dom.HTMLElement
    }

    val onCancel: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ViewSettings & HasSortByItem]] = new EventProp(
      "cancel"
    )
    val onConfirm: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ViewSettings & HasSortByItem]] = new EventProp(
      "confirm"
    )
  }

  

  /** Feed an instance of [[ViewSettingsDialog]] ref to this observer in order to show it. */
  val showObserver: Observer[Ref] = Observer(_.show())

  /** [[Mod]] showing the [[ViewSettingsDialog]] when the specified stream emits. */
  def showFromEvents(viewSettingsDialogShowEvents: EventStream[Unit]) =
    inContext[ReactiveHtmlElement[Ref]](el => viewSettingsDialogShowEvents.mapTo(el.ref) --> showObserver)

  /** Feed an instance of [[ViewSettingsDialog]] ref with the desired [[ViewSettings]] to set these to it. */
  val setConfirmedSettingsObserver: Observer[(Ref, ViewSettings)] = Observer(_.setConfirmedSettings(_))

  /** [[Mod]] settings the [[ViewSettings]] to the [[ViewSettingsDialog]]. */
  def setConfirmedSettingsFromEvents(settingsEvent: EventStream[ViewSettings]) =
    inContext[ReactiveHtmlElement[Ref]](el => settingsEvent.map(el.ref -> _) --> setConfirmedSettingsObserver)

}
