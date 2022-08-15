package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.IllustrationMessageType
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.internal.Slot

/** Simple UI button
  *
  * /!\ Used [[IllustrationMessageType]] must be registered before they can be used. Please refer to the official
  * documentation for more information. The [[IllustrationMessageType]] has a facility method to know how to register
  * it.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/IllustratedMessage/">the doc</a> for more
  *   information.
  */
object IllustratedMessage extends HasIcon {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/IllustratedMessage.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = IllustratedMessage.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-illustrated-message")

  val id: ReactiveProp[String, String] = idAttr

  val name: ReactiveHtmlAttr[IllustrationMessageType] = customHtmlAttr("name", IllustrationMessageType.AsStringCodec)
  val subtitleText: ReactiveHtmlAttr[String]          = customHtmlAttr("subtitle-text", StringAsIsCodec)
  val titleText: ReactiveHtmlAttr[String]             = customHtmlAttr("title-text", StringAsIsCodec)

  object slots {
    val subtitle: Slot = new Slot("subtitle")
  }

  object events {}

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(IllustratedMessage)): _*)

}
