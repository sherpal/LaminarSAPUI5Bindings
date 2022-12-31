package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{InputType, ValueState}
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, IntAsStringCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.internal.Slot
import be.doeraene.webcomponents.ui5.eventtypes.{HasDetail, HasItem, HasTargetRef}
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.WebComponent

/** The ui5-input component allows the user to enter and edit text or numeric values in one line.
  *
  * Additionally, you can provide suggestionItems, that are displayed in a popover right under the input.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Input/">the doc</a> for more information.
  */
object Input extends WebComponent with HasValue with HasAccessibleName {

  @js.native
  trait RawElement extends js.Object {
    def value: String = js.native

    def openPicker(): Unit = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/Input.js", JSImport.Default)
  object RawImport extends WebComponent.WithMetadata

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-input")

  lazy val placeholder: ReactiveHtmlAttr[String] = customHtmlAttr("placeholder", StringAsIsCodec)

  lazy val disabled: ReactiveHtmlAttr[Boolean]    = customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val required: ReactiveHtmlAttr[Boolean]    = customHtmlAttr("required", BooleanAsAttrPresenceCodec)
  lazy val readonly: ReactiveHtmlAttr[Boolean]    = customHtmlAttr("readonly", BooleanAsAttrPresenceCodec)
  lazy val noTypeahead: ReactiveHtmlAttr[Boolean] = customHtmlAttr("no-typeahead", BooleanAsAttrPresenceCodec)

  lazy val tpe: ReactiveHtmlAttr[InputType] = customHtmlAttr("type", InputType.AsStringCodec)

  lazy val maxLength: ReactiveHtmlAttr[Int] = customHtmlAttr("maxlength", IntAsStringCodec)

  lazy val valueState: ReactiveHtmlAttr[ValueState] = customHtmlAttr("value-state", ValueState.AsStringCodec)

  lazy val showClearIcon: ReactiveHtmlAttr[Boolean]   = customHtmlAttr("show-clear-icon", BooleanAsAttrPresenceCodec)
  lazy val showSuggestions: ReactiveHtmlAttr[Boolean] = customHtmlAttr("show-suggestions", BooleanAsAttrPresenceCodec)

  object slots {
    val valueStateMessage: Slot = new Slot("valueStateMessage")

    // undocumented in SAP but it exists.
    val icon: Slot = new Slot("icon")
  }

  object events {
    val onChange: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("change")
    val onInput: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("input")

    val onSuggestionItemPreview =
      new EventProp[dom.Event & HasDetail[HasTargetRef[dom.HTMLElement] & HasItem[SuggestionItem.RawElement]]](
        "suggestion-item-preview"
      )
    val onSuggestionItemSelect =
      new EventProp[dom.Event & HasDetail[HasItem[SuggestionItem.RawElement]]]("suggestion-item-select")
  }

  

  def suggestion: SuggestionItem.type           = SuggestionItem
  def suggestionGroup: SuggestionGroupItem.type = SuggestionGroupItem

}
