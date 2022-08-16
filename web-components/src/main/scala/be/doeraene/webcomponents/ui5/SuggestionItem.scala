package be.doeraene.webcomponents.ui5

import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.configkeys.ListItemType
import be.doeraene.webcomponents.ui5.configkeys.ValueState
import be.doeraene.webcomponents.WebComponent

/** The ui5-suggestion-item represents the suggestion item of the ui5-input.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Input/">the doc</a> for more information.
  */
object SuggestionItem extends WebComponent with HasIcon with HasDescription with HasText with HasAdditionalText {

  @js.native
  trait RawElement extends js.Object {
    def text: String = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/features/InputSuggestions.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = SuggestionItem.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-suggestion-item")

  lazy val iconEnd: ReactiveHtmlAttr[Boolean] = customHtmlAttr("icon-end", BooleanAsAttrPresenceCodec)

  lazy val image: ReactiveHtmlAttr[String] = customHtmlAttr("image", StringAsIsCodec)

  lazy val tpe: ReactiveHtmlAttr[ListItemType] = customHtmlAttr("tpe", ListItemType.AsStringCodec)

  lazy val additionalTextState: ReactiveHtmlAttr[ValueState] =
    customHtmlAttr("additional-text-state", ValueState.AsStringCodec)

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(SuggestionItem)): _*)

}
