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

/** The ui5-SuggestionGroupItem is a small non-interactive component which contains text information and color chosen
  * from a list of predefined color schemes. It serves the purpose to attract the user attention to some piece of
  * information (state, quantity, condition, etc.).
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/SuggestionGroupItem/">the doc</a> for more
  *   information.
  */
object SuggestionGroupItem extends HasText {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/features/InputSuggestions.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = SuggestionGroupItem.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-ui5-suggestion-group-item")

  val id: ReactiveProp[String, String] = idAttr

  object slots {}

  object events {}

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(SuggestionGroupItem)): _*)

}
