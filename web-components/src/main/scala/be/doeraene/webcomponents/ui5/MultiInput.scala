package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import com.raquo.laminar.codecs.IntAsStringCodec
import be.doeraene.webcomponents.ui5.configkeys.InputSuggestionsFilter
import be.doeraene.webcomponents.ui5.configkeys.InputType
import be.doeraene.webcomponents.ui5.configkeys.ValueState
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.eventtypes.HasItem
import be.doeraene.webcomponents.WebComponent
import scala.scalajs.js.annotation.JSName

/** A ui5-multi-input field allows the user to enter multiple values, which are displayed as ui5-token. User can choose
  * interaction for creating tokens. Fiori Guidelines say that user should create tokens when:
  *
  *   - Type a value in the input and press enter or focus out the input field (change event is fired)
  *   - Move between suggestion items (selection-change event is fired)
  *   - Clicking on a suggestion item (selection-change event is fired if the clicked item is different than the current
  *     value. Also change event is fired)
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/MultiInput/">the doc</a> for more information.
  */
object MultiInput extends WebComponent with HasAccessibleName with HasName with HasValue {

  @js.native
  trait RawElement extends js.Object {
    @deprecated("previewItem has been removed in UI5 2.0. Use the selection-change event instead.", since = "2.0.0")
    def previewItem: suggestion.Ref = js.native

    @JSName("tokens")
    def tokensJS: js.Array[token.Ref] = js.native

    def value: String = js.native
  }

  object RawElement {
    extension (element: RawElement) {
      def tokens: List[token.Ref] = element.tokensJS.toList

      @deprecated("openPicker method has been removed. Use the public member 'open' instead", since = "2.0.0")
      def openPicker(): Unit = element.asInstanceOf[js.Dynamic].updateDynamic("open")(true)
    }
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/MultiInput.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-multi-input")

  lazy val showValueHelpIcon: HtmlAttr[Boolean] =
    htmlAttr("show-value-help-icon", BooleanAsAttrPresenceCodec)

  lazy val disabled: HtmlAttr[Boolean] = htmlAttr("disabled", BooleanAsAttrPresenceCodec)

  lazy val maxlength: HtmlAttr[Int] = htmlAttr("maxlength", IntAsStringCodec)

  lazy val noTypeahead: HtmlAttr[Boolean] = htmlAttr("no-typeahead", BooleanAsAttrPresenceCodec)

  lazy val placeholder: HtmlAttr[String] = htmlAttr("placeholder", StringAsIsCodec)

  lazy val readonly: HtmlAttr[Boolean] = htmlAttr("readonly", BooleanAsAttrPresenceCodec)

  lazy val required: HtmlAttr[Boolean] = htmlAttr("required", BooleanAsAttrPresenceCodec)

  lazy val showClearIcon: HtmlAttr[Boolean] = htmlAttr("show-clear-icon", BooleanAsAttrPresenceCodec)

  lazy val showSuggestions: HtmlAttr[Boolean] = htmlAttr("show-suggestions", BooleanAsAttrPresenceCodec)

  /** Whether the suggestions picker is open. */
  lazy val open: HtmlAttr[Boolean] = htmlAttr("open", BooleanAsAttrPresenceCodec)

  /** Filter type used for the suggestions (defaults to `None`). Since UI5 2.19.0. */
  lazy val filter: HtmlAttr[InputSuggestionsFilter] = InputSuggestionsFilter.asHtmlAttr("filter")

  lazy val tpe: HtmlAttr[InputType] = htmlAttr("type", InputType.AsStringCodec)

  lazy val valueState: HtmlAttr[ValueState] = htmlAttr("value-state", ValueState.AsStringCodec)

  lazy val accessibleDescription: HtmlAttr[String]    = htmlAttr("accessible-description", StringAsIsCodec)
  lazy val accessibleDescriptionRef: HtmlAttr[String] = htmlAttr("accessible-description-ref", StringAsIsCodec)

  object slots {

    val tokens: Slot = new Slot("tokens")

    // note that unlike most elements that have an attribute Icon, this element has a slot icon instead.
    // you can use a ui5-input-icon (MultiInput.inputIcon) or a ui5-icon for this slot.
    val icon: Slot = new Slot("icon")

    val valueStateMessage: Slot = new Slot("valueStateMessage")
  }

  object events {
    trait HasToken extends js.Object {
      @JSName("tokens")
      def tokensJS: js.Array[MultiInput.token.Ref]
    }

    object HasToken {
      extension (hasToken: HasToken) {
        def tokens: Vector[MultiInput.token.Ref] = hasToken.tokensJS.toVector

        def token: MultiInput.token.Ref = hasToken.tokensJS.head
      }
    }

    val onTokenDelete: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasToken]] = new EventProp("token-delete")

    val onValueHelpTrigger: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("value-help-trigger")

    val onChange: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("change")

    val onInput: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("input")

    /** Fired when the user selects a suggestion item (via Enter or a click). */
    val onSelect: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("select")

    val onOpen: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("open")
    val onClose: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("close")

    trait SelectionChangeDetail extends js.Object {

      /** The previewed suggestion item, or `null` when none. */
      def item: suggestion.Ref | Null
    }

    /** Fired when the user navigates to a suggestion item via the ARROW keys, as a preview, before the final selection.
      */
    val onSelectionChange: EventProp[EventWithPreciseTarget[Ref] & HasDetail[SelectionChangeDetail]] =
      new EventProp("selection-change")

    trait SuggestionItemPreviewInfo extends js.Object {
      def item: suggestion.Ref

      def targetRef: suggestion.Ref
    }

    @scala.annotation.compileTimeOnly(
      "suggestion-item-preview has been removed in UI5 2.0. Use onSelectionChange instead."
    )
    def onSuggestionItemPreview: EventProp[EventWithPreciseTarget[Ref] & HasDetail[SuggestionItemPreviewInfo]] = ???

    @scala.annotation.compileTimeOnly(
      "suggestion-item-select has been removed in UI5 2.0. Use onSelect (and onSelectionChange) instead."
    )
    def onSuggestionItemSelect: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasItem[suggestion.Ref]]] = ???
  }

  def suggestion: SuggestionItem.type             = SuggestionItem
  def suggestionCustom: SuggestionItemCustom.type = SuggestionItemCustom
  def suggestionGroup: SuggestionGroupItem.type   = SuggestionGroupItem

  def token: Token.type = Token

  /** Interactive icon to be put in the `icon` slot (since UI5 2.25.0). */
  def inputIcon: InputIcon.type = InputIcon

}
