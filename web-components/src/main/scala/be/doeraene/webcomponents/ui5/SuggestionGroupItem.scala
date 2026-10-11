package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.WrappingType
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** The ui5-suggestion-item-group is type of suggestion item, that can be used to split the ui5-input suggestions into
  * groups.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/SuggestionItemGroup/">the doc</a> for more information.
  */
object SuggestionGroupItem extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def headerText: String = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/SuggestionItemGroup.js", JSImport.Default)
  object RawImport extends js.Object

  /** The input suggestions feature must also be loaded for suggestions to work in the ui5-input. */
  @js.native
  @JSImport("@ui5/webcomponents/dist/features/InputSuggestions.js", JSImport.Default)
  object InputSuggestionsFeatureImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)
  used(InputSuggestionsFeatureImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-suggestion-item-group")

  lazy val headerText: HtmlAttr[String]           = htmlAttr("header-text", StringAsIsCodec)
  lazy val headerAccessibleName: HtmlAttr[String] = htmlAttr("header-accessible-name", StringAsIsCodec)

  /** Whether the group header text wraps (`Normal`) or is truncated (`None`, default). Since UI5 2.15.0. */
  lazy val wrappingType: HtmlAttr[WrappingType] = htmlAttr("wrapping-type", WrappingType.AsStringCodec)

  @deprecated("text has been replaced by headerText", since = "2.0.0")
  def text: HtmlAttr[String] = headerText

  object slots {}

  object events {}

  def item: SuggestionItem.type             = SuggestionItem
  def itemCustom: SuggestionItemCustom.type = SuggestionItemCustom

}
