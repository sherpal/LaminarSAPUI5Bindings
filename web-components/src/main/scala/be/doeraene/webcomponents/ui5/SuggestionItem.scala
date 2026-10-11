package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{IconName, ListItemType, ValueState}
import be.doeraene.webcomponents.ui5.eventtypes.{EventWithPreciseTarget, HasDetail}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** The ui5-suggestion-item represents the suggestion item of the ui5-input.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/SuggestionItem/">the doc</a> for more information.
  */
object SuggestionItem extends WebComponent with HasText with HasAdditionalText {

  @js.native
  trait RawElement extends js.Object {
    def text: String = js.native

    def additionalText: js.UndefOr[String] = js.native
  }

  /** Importing the input suggestions feature also registers the ui5-suggestion-item and ui5-suggestion-item-group. */
  @js.native
  @JSImport("@ui5/webcomponents/dist/features/InputSuggestions.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-suggestion-item")

  private final val removedMessage =
    "This attribute has been removed from ui5-suggestion-item in UI5 2.0. Only text and additionalText remain. Use ui5-suggestion-item-custom (SuggestionItemCustom) for custom content."

  @scala.annotation.compileTimeOnly(removedMessage)
  def icon: HtmlAttr[IconName] = ???

  @scala.annotation.compileTimeOnly(removedMessage)
  def iconString: HtmlAttr[String] = ???

  @scala.annotation.compileTimeOnly(removedMessage)
  def description: HtmlAttr[String] = ???

  @scala.annotation.compileTimeOnly(removedMessage)
  def iconEnd: HtmlAttr[Boolean] = ???

  @scala.annotation.compileTimeOnly(removedMessage)
  def image: HtmlAttr[String] = ???

  @scala.annotation.compileTimeOnly(removedMessage)
  def tpe: HtmlAttr[ListItemType] = ???

  @scala.annotation.compileTimeOnly(removedMessage)
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
