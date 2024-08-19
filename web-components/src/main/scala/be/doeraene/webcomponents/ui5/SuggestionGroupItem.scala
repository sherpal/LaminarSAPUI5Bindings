package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** The ui5-SuggestionGroupItem
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Input/">the doc</a> for more information.
  */
object SuggestionGroupItem extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/features/SuggestionGroupItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-suggestion-item-group")

  lazy val headerText: HtmlAttr[String]           = htmlAttr("header-text", StringAsIsCodec)
  lazy val headerAccessibleName: HtmlAttr[String] = htmlAttr("header-accessible-name", StringAsIsCodec)

  @deprecated("text has been replaced by headerText", since = "2.0.0")
  def text: HtmlAttr[String] = headerText

  object slots {}

  object events {}

}
