package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.ValueState
import be.doeraene.webcomponents.ui5.eventtypes.{EventWithPreciseTarget, HasDetail}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** The ui5-suggestion-item-custom is type of suggestion item, that can be used to place suggestion items with custom
  * content in the input. The text property is considered only for autocomplete. In case the user needs highlighting
  * functionality, check "@ui5/webcomponents-base/dist/util/generateHighlightedMarkup.js"
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/SuggestionItemCustom/">the doc</a> for more information.
  */
object SuggestionItemCustom extends WebComponent with HasText {

  @js.native
  trait RawElement extends js.Object {
    def text: String = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/SuggestionItemCustom.js", JSImport.Default)
  object RawImport extends js.Object

  /** The input suggestions feature must also be loaded for suggestions to work in the ui5-input. */
  @js.native
  @JSImport("@ui5/webcomponents/dist/features/InputSuggestions.js", JSImport.Default)
  object InputSuggestionsFeatureImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)
  used(InputSuggestionsFeatureImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-suggestion-item-custom")

  @scala.annotation.compileTimeOnly(
    "additionalTextState does not exist on ui5-suggestion-item-custom (removed in UI5 2.0). Put any custom content as children instead."
  )
  def additionalTextState: HtmlAttr[ValueState] = ???

  object slots {}

  object events {
    trait ClickDetail extends js.Object {
      def originalEvent: dom.Event
    }

    /** Fired when the item is clicked (since UI5 2.23.0). */
    val onClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ClickDetail]] = new EventProp("click")
  }

}
