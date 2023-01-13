package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{InputType, ValueState}
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, IntAsStringCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.{HtmlAttr, ReactiveProp, ReactiveStyle}
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
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-input")

  lazy val placeholder: HtmlAttr[String] = customHtmlAttr("placeholder", StringAsIsCodec)

  lazy val disabled: HtmlAttr[Boolean]    = customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val required: HtmlAttr[Boolean]    = customHtmlAttr("required", BooleanAsAttrPresenceCodec)
  lazy val readonly: HtmlAttr[Boolean]    = customHtmlAttr("readonly", BooleanAsAttrPresenceCodec)
  lazy val noTypeahead: HtmlAttr[Boolean] = customHtmlAttr("no-typeahead", BooleanAsAttrPresenceCodec)

  lazy val tpe: HtmlAttr[InputType] = customHtmlAttr("type", InputType.AsStringCodec)

  lazy val maxLength: HtmlAttr[Int] = customHtmlAttr("maxlength", IntAsStringCodec)

  lazy val valueState: HtmlAttr[ValueState] = customHtmlAttr("value-state", ValueState.AsStringCodec)

  lazy val showClearIcon: HtmlAttr[Boolean]   = customHtmlAttr("show-clear-icon", BooleanAsAttrPresenceCodec)
  lazy val showSuggestions: HtmlAttr[Boolean] = customHtmlAttr("show-suggestions", BooleanAsAttrPresenceCodec)

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
