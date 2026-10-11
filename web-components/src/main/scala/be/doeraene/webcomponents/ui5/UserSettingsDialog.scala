package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.eventtypes.HasEscPressed
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-user-settings-dialog is an SAP Fiori specific web component, typically opened from the [[UserMenu]], that
  * allows the user to easily view information and settings for an account.
  *
  * Children are [[UserSettingsItem]]s (if none is `selected`, the first one is). Items that should stay at the bottom
  * of the side list go in the `fixedItems` slot.
  *
  * @since UI5 2.8.0
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/UserSettingsDialog/">the doc</a> for more
  *   information.
  */
object UserSettingsDialog extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    var open: Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/UserSettingsDialog.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-user-settings-dialog")

  lazy val headerText: HtmlAttr[String] = htmlAttr("header-text", StringAsIsCodec)
  lazy val open: HtmlAttr[Boolean]      = htmlAttr("open", BooleanAsAttrPresenceCodec)

  /** Whether the search field is displayed (not by default). */
  lazy val showSearchField: HtmlAttr[Boolean] = htmlAttr("show-search-field", BooleanAsAttrPresenceCodec)

  /** When true, the footer has a "Save" and a "Cancel" button (firing the `save` and `cancel` events) instead of the
    * default "Close" button. The application is then responsible for closing the dialog.
    */
  lazy val saveMode: HtmlAttr[Boolean] = htmlAttr("save-mode", BooleanAsAttrPresenceCodec)

  object slots {

    /** The fixed user settings items ([[UserSettingsItem]]s). */
    val fixedItems: Slot = Slot("fixedItems")
  }

  object events {

    trait SelectionChangeDetail extends js.Object {
      def item: UserSettingsItem.Ref
    }

    val onOpen: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("open")
    val onClose: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("close")

    /** Fired before the dialog is closed. Can be prevented (e.g., to prompt the user about unsaved changes). */
    val onBeforeClose: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasEscPressed]] = new EventProp(
      "before-close"
    )

    /** Fired when an item is selected. Can be prevented. */
    val onSelectionChange: EventProp[EventWithPreciseTarget[Ref] & HasDetail[SelectionChangeDetail]] =
      new EventProp("selection-change")

    /** Fired when the "Save" button is clicked (in `saveMode`). The dialog does not close automatically. */
    val onSave: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("save")

    /** Fired when the "Cancel" button is clicked (in `saveMode`). The dialog does not close automatically. */
    val onCancel: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("cancel")
  }

  def item: UserSettingsItem.type = UserSettingsItem

}
