package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.*
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, JSName}
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.WebComponent

/** The ui5-view-settings-dialog component helps the user to sort, filter and group data within a list or a table. It
  * consists of several lists like Sort order which is built-in and Sort By, Filter By and Group By lists, for which you
  * must be provide items ([[SortItem]], [[FilterItem]] and [[GroupItem]] respectively). These options can be used to
  * create sorters for a table. Additional custom tabs can be added with [[ViewSettingsDialogCustomTab]]s.
  *
  * The ui5-view-settings-dialog interrupts the current application processing as it is the only focused UI element and
  * the main screen is dimmed/blocked. The ui5-view-settings-dialog is modal, which means that user action is required
  * before returning to the parent window is possible.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/ViewSettingsDialog/">the doc</a> for more
  *   information.
  */
object ViewSettingsDialog extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def setConfirmedSettings(settings: ViewSettings): Unit = js.native
  }

  object RawElement {
    extension (rawElement: RawElement) {
      @deprecated("The public show method has been replaced by using the open property", since = "2.0.0")
      def show(): Unit =
        rawElement.asInstanceOf[js.Dynamic].updateDynamic("open")(true)
    }
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/ViewSettingsDialog.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-view-settings-dialog")

  lazy val sortDescending: HtmlAttr[Boolean] = htmlAttr("sort-descending", BooleanAsAttrPresenceCodec)
  lazy val open: HtmlAttr[Boolean]           = htmlAttr("open", BooleanAsAttrPresenceCodec)

  /** Initial group order (descending or not).
    *
    * @since UI5 2.13.0
    */
  lazy val groupDescending: HtmlAttr[Boolean] = htmlAttr("group-descending", BooleanAsAttrPresenceCodec)

  /** Forces the Reset button to be enabled.
    *
    * By default, the Reset button is only enabled when the built-in settings (sort, filter, group) differ from their
    * initial state. Since the component cannot detect changes inside custom tabs, set this to `true` when the user made
    * changes inside a custom tab (and back to `false` once its content is back to its initial state).
    *
    * @since UI5 2.22.0
    */
  lazy val resetEnabled: HtmlAttr[Boolean] = htmlAttr("reset-enabled", BooleanAsAttrPresenceCodec)

  object slots {
    val filterItems: Slot = Slot("filterItems")
    val sortItems: Slot   = Slot("sortItems")

    /** Should contain [[GroupItem]]s. */
    val groupItems: Slot = Slot("groupItems")

    /** Should contain [[ViewSettingsDialogCustomTab]]s.
      *
      * @since UI5 2.22.0
      */
    val customTabs: Slot = Slot("customTabs")
  }

  trait ViewSettings extends js.Object {
    def sortOrder: "Ascending" | "Descending"

    def sortBy: String

    def sortDescending: Boolean

    @JSName("filters")
    def filtersJS: js.Array[js.Dictionary[js.Array[String]]]

    /** Text of the selected group order (undefined if there are no group items). */
    val groupOrder: js.UndefOr[String] = js.undefined

    /** Text of the selected [[GroupItem]] (undefined if there are no group items). */
    val groupBy: js.UndefOr[String] = js.undefined
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

    trait HasGroupByItem extends js.Object {

      /** The selected [[GroupItem]] (undefined if there are no group items). */
      def groupByItem: js.UndefOr[dom.HTMLElement]

      def groupDescending: Boolean
    }

    /** Fired when the cancel button is pressed. The detail contains the settings as they were before opening. */
    val onCancel: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ViewSettings & HasSortByItem & HasGroupByItem]] =
      new EventProp(
        "cancel"
      )

    /** Fired when the confirmation button is pressed. */
    val onConfirm: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ViewSettings & HasSortByItem & HasGroupByItem]] =
      new EventProp(
        "confirm"
      )

    /** Fired after the dialog is opened. */
    val onOpen: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("open")

    /** Fired after the dialog is closed. */
    val onClose: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("close")

    /** Fired when the Reset button is pressed.
      *
      * The built-in settings (sort, filter, group) are reset automatically, but it is your responsibility to reset the
      * content of your custom tabs.
      *
      * @since UI5 2.22.0
      */
    val onReset: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("reset")
  }

  /** Feed an instance of [[ViewSettingsDialog]] ref to this observer in order to show it. */
  @deprecated("showObserver has been replaced by using the open property", since = "2.0.0")
  def showObserver: Observer[Ref] = Observer(_.show())

  /** [[Mod]] showing the [[ViewSettingsDialog]] when the specified stream emits. */
  @deprecated("showFromEvents has been replaced by using the open property", since = "2.0.0")
  def showFromEvents(viewSettingsDialogShowEvents: EventStream[Unit]) =
    open <-- viewSettingsDialogShowEvents.mapTo(true)

  /** Feed an instance of [[ViewSettingsDialog]] ref with the desired [[ViewSettings]] to set these to it. */
  val setConfirmedSettingsObserver: Observer[(Ref, ViewSettings)] = Observer(_.setConfirmedSettings(_))

  /** [[Mod]] settings the [[ViewSettings]] to the [[ViewSettingsDialog]]. */
  def setConfirmedSettingsFromEvents(settingsEvent: EventStream[ViewSettings]) =
    inContext[ReactiveHtmlElement[Ref]](el => settingsEvent.map(el.ref -> _) --> setConfirmedSettingsObserver)

}
