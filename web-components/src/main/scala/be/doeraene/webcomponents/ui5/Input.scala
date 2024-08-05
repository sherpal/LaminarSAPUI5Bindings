package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{InputType, ValueState}
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, IntAsStringCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.internal.Slot
import be.doeraene.webcomponents.ui5.eventtypes.{HasDetail, HasItem, HasTargetRef}
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.WebComponent

/** <ui5-input> The ui5-input component allows the user to enter and edit text or numeric values in one line.
  *
  * Additionally, you can provide suggestionItems, that are displayed in a popover right under the input.
  */
object Input extends WebComponent with HasValue with HasAccessibleName {

  @js.native
  trait RawElement extends js.Object {
    def value: String = js.native
  }

  object RawElement {
    extension (rawElement: RawElement) {
      @deprecated("openPicker method has been removed. Use the public member 'open' instead", since = "2.0.0")
      def openPicker(): Unit = rawElement.asInstanceOf[js.Dynamic].updateDynamic("open")(true)
    }
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/Input.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-input")

  lazy val placeholder: HtmlAttr[String] = htmlAttr("placeholder", StringAsIsCodec)

  lazy val disabled: HtmlAttr[Boolean]    = htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val required: HtmlAttr[Boolean]    = htmlAttr("required", BooleanAsAttrPresenceCodec)
  lazy val readonly: HtmlAttr[Boolean]    = htmlAttr("readonly", BooleanAsAttrPresenceCodec)
  lazy val noTypeahead: HtmlAttr[Boolean] = htmlAttr("no-typeahead", BooleanAsAttrPresenceCodec)

  lazy val tpe: HtmlAttr[InputType] = htmlAttr("type", InputType.AsStringCodec)

  lazy val maxLength: HtmlAttr[Int] = htmlAttr("maxlength", IntAsStringCodec)

  lazy val valueState: HtmlAttr[ValueState] = htmlAttr("value-state", ValueState.AsStringCodec)

  lazy val showClearIcon: HtmlAttr[Boolean]   = htmlAttr("show-clear-icon", BooleanAsAttrPresenceCodec)
  lazy val showSuggestions: HtmlAttr[Boolean] = htmlAttr("show-suggestions", BooleanAsAttrPresenceCodec)

  lazy val open: HtmlAttr[Boolean] = htmlAttr("open", BooleanAsAttrPresenceCodec)

  object slots {
    val valueStateMessage: Slot = new Slot("valueStateMessage")

    // undocumented in SAP but it exists.
    val icon: Slot = new Slot("icon")
  }

  object events {
    val onChange: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("change")
    val onInput: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("input")

    val onSelect: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("select")
    val onOpen: EventProp[EventWithPreciseTarget[Ref]]   = new EventProp("open")
    val onClose: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("close")

    val onSelectionChange: EventProp[
      EventWithPreciseTarget[Ref] &
        HasDetail[HasTargetRef[dom.HTMLElement] & HasItem[js.UndefOr[SuggestionItem.RawElement]]]
    ] = new EventProp("selection-change")

    @deprecated("onSuggestionItemPreview has been changed to onSelectionChange", since = "2.0.0")
    def onSuggestionItemPreview = onSelectionChange

    @scala.annotation.compileTimeOnly("onSuggestionItemSelect has been removed. Used onSelectionChange instead.")
    def onSuggestionItemSelect: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasItem[SuggestionItem.RawElement]]] =
      ???
  }

  def suggestion: SuggestionItem.type           = SuggestionItem
  def suggestionGroup: SuggestionGroupItem.type = SuggestionGroupItem

}
