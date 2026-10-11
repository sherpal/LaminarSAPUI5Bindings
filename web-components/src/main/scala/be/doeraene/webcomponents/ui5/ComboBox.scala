package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ComboBoxFilter, ComboBoxSelectionChangeTrigger, ValueState}
import be.doeraene.webcomponents.ui5.eventtypes.{EventWithPreciseTarget, HasDetail}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, JSName}
import be.doeraene.webcomponents.WebComponent

/** The ui5-combobox component represents a drop-down menu with a list of the available options and a text input field
  * to narrow down the options. It is commonly used to enable users to select an option from a predefined list.
  *
  * Items can be [[ComboBoxItem]]s, [[ComboBoxItemCustom]]s (custom content) and [[ComboBoxGroupItem]]s.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/ComboBox/">the doc</a> for more information.
  */
object ComboBox extends WebComponent with HasAccessibleName with HasValue with HasName {

  @js.native
  trait RawElement extends js.Object {
    def value: String = js.native

    /** Value of the currently selected item (if any), see [[ComboBox.selectedValue]]. */
    def selectedValue: js.UndefOr[String] = js.native

    def open: Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/ComboBox.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-combobox")

  lazy val disabled: HtmlAttr[Boolean]      = htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val filter: HtmlAttr[ComboBoxFilter] = htmlAttr("filter", ComboBoxFilter.AsStringCodec)
  lazy val loading: HtmlAttr[Boolean]       = htmlAttr("loading", BooleanAsAttrPresenceCodec)
  lazy val placeholder: HtmlAttr[String]    = htmlAttr("placeholder", StringAsIsCodec)
  lazy val readonly: HtmlAttr[Boolean]      = htmlAttr("readonly", BooleanAsAttrPresenceCodec)
  lazy val required: HtmlAttr[Boolean]      = htmlAttr("required", BooleanAsAttrPresenceCodec)
  lazy val valueState: HtmlAttr[ValueState] = htmlAttr("value-state", ValueState.AsStringCodec)
  lazy val noTypeahead: HtmlAttr[Boolean]   = htmlAttr("no-typeahead", BooleanAsAttrPresenceCodec)
  lazy val showClearIcon: HtmlAttr[Boolean] = htmlAttr("show-clear-icon", BooleanAsAttrPresenceCodec)

  /** Whether the items picker is open. */
  lazy val open: HtmlAttr[Boolean] = htmlAttr("open", BooleanAsAttrPresenceCodec)

  /** Defines the value of the selected item (matched against the `value` attribute of the items).
    *
    * Use this together with `value` on items when the display text differs from the value you want to keep (e.g.
    * display "Belgium" but keep "BE"). Since UI5 2.20.0.
    */
  lazy val selectedValue: HtmlAttr[String] = htmlAttr("selected-value", StringAsIsCodec)

  object slots {
    val default: Slot           = new Slot("default")
    val icon: Slot              = new Slot("icon")
    val valueStateMessage: Slot = new Slot("valueStateMessage")
  }

  object events {

    trait SelectionChangeDetail extends js.Object {

      /** The selected item, or `null` when the selection is cleared. */
      def item: ComboBoxItem.Ref | Null

      @JSName("trigger")
      def triggerJS: js.UndefOr[String]
    }

    object SelectionChangeDetail {
      extension (detail: SelectionChangeDetail) {
        def maybeItem: Option[ComboBoxItem.Ref] = Option(detail.item)

        /** What triggered the selection change (since UI5 2.24.0). */
        def trigger: Option[ComboBoxSelectionChangeTrigger] =
          detail.triggerJS.toOption.flatMap(ComboBoxSelectionChangeTrigger.fromString)
      }
    }

    val onChange: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("change")
    val onInput: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("input")
    val onOpen: EventProp[EventWithPreciseTarget[Ref]]   = new EventProp("open")
    val onClose: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("close")

    /** Fired when the selection has changed (via user interaction). Note that the item can be any [[ComboBoxItem]] or
      * [[ComboBoxItemCustom]].
      */
    val onSelectionChange: EventProp[EventWithPreciseTarget[Ref] & HasDetail[SelectionChangeDetail]] =
      new EventProp("selection-change")
  }

  def item: ComboBoxItem.type             = ComboBoxItem
  def itemCustom: ComboBoxItemCustom.type = ComboBoxItemCustom
  def group: ComboBoxGroupItem.type       = ComboBoxGroupItem
}
