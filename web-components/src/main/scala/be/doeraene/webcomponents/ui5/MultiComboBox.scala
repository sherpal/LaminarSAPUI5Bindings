package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ComboBoxFilter, ValueState}
import be.doeraene.webcomponents.ui5.eventtypes.{EventWithPreciseTarget, HasDetail, HasItems}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, Codec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.{HtmlAttr, HtmlProp}
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.JSConverters.*
import scala.scalajs.js.annotation.{JSImport, JSName}
import be.doeraene.webcomponents.WebComponent

/** The ui5-multi-combobox component consists of a list box with items and a text field allowing the user to either type
  * a value directly into the text field, or choose from the list of existing items.
  *
  * Items can be [[MultiComboBoxItem]]s, [[MultiComboBoxItemCustom]]s (custom content) and [[MultiComboBoxGroupItem]]s.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/MultiComboBox/">the doc</a> for more information.
  */
object MultiComboBox extends WebComponent with HasAccessibleName with HasValue with HasName {

  @js.native
  trait RawElement extends js.Object {
    def value: String = js.native

    @JSName("selectedValues")
    def selectedValuesJS: js.Array[String] = js.native

    def open: Boolean = js.native
  }

  object RawElement {
    extension (element: RawElement) {

      /** Values of the selected items (see [[MultiComboBox.selectedValues]]). */
      def selectedValues: List[String] = element.selectedValuesJS.toList
    }
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/MultiComboBox.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-multi-combobox")

  lazy val disabled: HtmlAttr[Boolean]      = htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val filter: HtmlAttr[ComboBoxFilter] = htmlAttr("filter", ComboBoxFilter.AsStringCodec)
  lazy val loading: HtmlAttr[Boolean]       = htmlAttr("loading", BooleanAsAttrPresenceCodec)
  lazy val placeholder: HtmlAttr[String]    = htmlAttr("placeholder", StringAsIsCodec)
  lazy val readonly: HtmlAttr[Boolean]      = htmlAttr("readonly", BooleanAsAttrPresenceCodec)
  lazy val noValidation: HtmlAttr[Boolean]  = htmlAttr("no-validation", BooleanAsAttrPresenceCodec)
  lazy val noTypeahead: HtmlAttr[Boolean]   = htmlAttr("no-typeahead", BooleanAsAttrPresenceCodec)
  lazy val required: HtmlAttr[Boolean]      = htmlAttr("required", BooleanAsAttrPresenceCodec)
  lazy val valueState: HtmlAttr[ValueState] = htmlAttr("value-state", ValueState.AsStringCodec)
  lazy val showClearIcon: HtmlAttr[Boolean] = htmlAttr("show-clear-icon", BooleanAsAttrPresenceCodec)

  /** Whether the "select all" checkbox is visible on top of the suggestions. */
  lazy val showSelectAll: HtmlAttr[Boolean] = htmlAttr("show-select-all", BooleanAsAttrPresenceCodec)

  /** Whether the items picker is open. */
  lazy val open: HtmlAttr[Boolean] = htmlAttr("open", BooleanAsAttrPresenceCodec)

  /** Values of the selected items (matched against the `value` attribute of the items). This is the recommended way to
    * programmatically control the selection (since UI5 2.20.0).
    *
    * This is a property, not an attribute (it takes an array).
    */
  lazy val selectedValues: HtmlProp[List[String], js.Array[String]] = htmlProp(
    "selectedValues",
    new Codec[List[String], js.Array[String]] {
      def decode(domValue: js.Array[String]): List[String] = domValue.toList
      def encode(scalaValue: List[String]): js.Array[String] = scalaValue.toJSArray
    }
  )

  @deprecated("allCustomValues has been renamed to noValidation", since = "2.0.0")
  def allowCustomValues = noValidation

  object slots {
    val default: Slot           = new Slot("default")
    val icon: Slot              = new Slot("icon")
    val valueStateMessage: Slot = new Slot("valueStateMessage")
  }

  object events {

    trait ValueStateChangeDetail extends js.Object {
      @JSName("valueState")
      def valueStateJS: String
    }

    object ValueStateChangeDetail {
      extension (detail: ValueStateChangeDetail) {
        def valueState: ValueState = ValueState.AsStringCodec.decode(detail.valueStateJS)
      }
    }

    val onChange: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("change")
    val onInput: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("input")

    /** Fired when the selection has changed. The items in the detail are the currently selected items (they can also be
      * [[MultiComboBoxItemCustom]]s).
      */
    val onSelectionChange: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasItems[MultiComboBoxItem.Ref]]] =
      new EventProp("selection-change")
    val onOpen: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("open")
    val onClose: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("close")

    /** Fired before the value state of the component is updated internally. The event is preventable, meaning that if
      * it's default action is prevented, the component will not update the value state. Since UI5 2.19.0.
      */
    val onValueStateChange: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ValueStateChangeDetail]] =
      new EventProp("value-state-change")
  }

  def item: MultiComboBoxItem.type             = MultiComboBoxItem
  def itemCustom: MultiComboBoxItemCustom.type = MultiComboBoxItemCustom
  def group: MultiComboBoxGroupItem.type       = MultiComboBoxGroupItem
}
