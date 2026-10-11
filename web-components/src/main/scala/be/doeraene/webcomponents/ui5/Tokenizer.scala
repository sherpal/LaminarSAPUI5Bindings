package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.eventtypes.{EventWithPreciseTarget, HasDetail}
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, JSName}
import be.doeraene.webcomponents.WebComponent

/** A ui5-tokenizer is an invisible container for [[Token]]s (its children) that supports keyboard navigation and token
  * selection. It displays the available tokens, and an "n more" indicator with the number of the remaining tokens that
  * cannot be displayed due to the limited space.
  *
  * Note: the deletion of a token must be handled by the application with the `token-delete` event.
  *
  * Note: this component is experimental in UI5 and its API may change.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/Tokenizer/">the doc</a> for more information.
  */
object Tokenizer extends WebComponent with HasAccessibleName with HasName {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/Tokenizer.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-tokenizer")

  lazy val disabled: HtmlAttr[Boolean] = htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val readonly: HtmlAttr[Boolean] = htmlAttr("readonly", BooleanAsAttrPresenceCodec)

  /** Whether tokens are displayed on multiple lines (instead of a single line with an "n more" indicator). Since UI5
    * 2.5.0.
    */
  lazy val multiLine: HtmlAttr[Boolean] = htmlAttr("multi-line", BooleanAsAttrPresenceCodec)

  /** Whether the "Clear All" button is present. Only taken into account when `multiLine` is `true`. Since UI5 2.5.0.
    */
  lazy val showClearAll: HtmlAttr[Boolean] = htmlAttr("show-clear-all", BooleanAsAttrPresenceCodec)

  object slots {}

  object events {

    trait HasTokens extends js.Object {
      @JSName("tokens")
      def tokensJS: js.Array[Token.Ref]
    }

    object HasTokens {
      extension (detail: HasTokens) {
        def tokens: List[Token.Ref] = detail.tokensJS.toList
      }
    }

    /** Fired when tokens are being deleted (delete icon, delete or backspace key, "Clear All"). The tokens must be
      * removed by the application.
      */
    val onTokenDelete: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasTokens]] = new EventProp("token-delete")

    /** Fired when token selection is changed by user interaction. The detail contains the selected tokens. */
    val onSelectionChange: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasTokens]] =
      new EventProp("selection-change")
  }

  def token: Token.type = Token

}
