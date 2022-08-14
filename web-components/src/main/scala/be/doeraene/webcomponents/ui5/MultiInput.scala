package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import com.raquo.domtypes.generic.codecs.IntAsStringCodec
import be.doeraene.webcomponents.ui5.configkeys.InputType
import be.doeraene.webcomponents.ui5.configkeys.ValueState
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.eventtypes.HasItem

/** A ui5-multi-input field allows the user to enter multiple values, which are displayed as ui5-token. User can choose
  * interaction for creating tokens. Fiori Guidelines say that user should create tokens when:
  *
  *   - Type a value in the input and press enter or focus out the input field (change event is fired)
  *   - Select a value from the suggestion list (suggestion-item-select event is fired)
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/MultiInput/">the doc</a> for more
  *   information.
  */
object MultiInput extends HasAccessibleName with HasName with HasValue {

  @js.native
  trait RawElement extends js.Object {
    def previewItem: suggestion.Ref = js.native

    def openPicker(): Unit = js.native

    def tokensJS: js.Array[token.Ref] = js.native

    def value: String = js.native
  }

  object RawElement {
    extension (element: RawElement) def tokens: List[token.Ref] = element.tokensJS.toList
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/MultiInput.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = MultiInput.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-multi-input")

  val id: ReactiveProp[String, String] = idAttr

  val showValueHelpIcon: ReactiveHtmlAttr[Boolean] = customHtmlAttr("show-value-help-icon", BooleanAsAttrPresenceCodec)

  val disabled: ReactiveHtmlAttr[Boolean] = customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)

  val maxlength: ReactiveHtmlAttr[Int] = customHtmlAttr("maxlength", IntAsStringCodec)

  // todo[1.4.0]
  //val noTypeahead: ReactiveHtmlAttr[Boolean] = customHtmlAttr("no-typeahead", BooleanAsAttrPresenceCodec)

  val placeholder: ReactiveHtmlAttr[String] = customHtmlAttr("placeholder", StringAsIsCodec)

  val readonly: ReactiveHtmlAttr[Boolean] = customHtmlAttr("readonly", BooleanAsAttrPresenceCodec)

  val showClearIcon: ReactiveHtmlAttr[Boolean] = customHtmlAttr("show-clear-icon", BooleanAsAttrPresenceCodec)

  val showSuggestions: ReactiveHtmlAttr[Boolean] = customHtmlAttr("show-suggestions", BooleanAsAttrPresenceCodec)

  val tpe: ReactiveHtmlAttr[InputType] = customHtmlAttr("type", InputType.AsStringCodec)

  val valueState: ReactiveHtmlAttr[ValueState] = customHtmlAttr("value-state", ValueState.AsStringCodec)

  object slots {

    val tokens: Slot = new Slot("tokens")

    // note that unlike most elements that have an attribute Icon, this element has a slot icon instead.
    // most of the time you will want to use a ui5-icon for this slot.
    val icon: Slot = new Slot("icon")

    val valueStateMessage: Slot = new Slot("valueStateMessage")
  }

  object events {
    trait HasToken extends js.Object {
      def token: MultiInput.token.Ref
    }

    val onTokenDelete: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasToken]] = new EventProp("token-delete")

    val onValueHelpTrigger: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("value-help-trigger")

    val onChange: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("change")

    val onInput: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("input")

    trait SuggestionItemPreviewInfo extends js.Object {
      def item: suggestion.Ref

      def targetRef: suggestion.Ref
    }

    val onSuggestionItemPreview: EventProp[EventWithPreciseTarget[Ref] & HasDetail[SuggestionItemPreviewInfo]] =
      new EventProp("suggestion-item-preview")

    val onSuggestionItemSelect: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasItem[suggestion.Ref]]] =
      new EventProp("suggestion-item-select")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(MultiInput)): _*)

  def suggestion: SuggestionItem.type = SuggestionItem

  def token: Token.type = Token

}
