package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.IllustratedMessageType
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.{HtmlAttr}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.internal.Slot
import be.doeraene.webcomponents.WebComponent

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
object IllustratedMessage extends WebComponent with HasIcon {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/IllustratedMessage.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = htmlTag("ui5-illustrated-message")

  lazy val accessibleNameRef: HtmlAttr[String] = htmlAttr("accessible-name-ref", StringAsIsCodec)
  lazy val name: HtmlAttr[IllustratedMessageType] =
    htmlAttr("name", IllustratedMessageType.AsStringCodec)
  
  /** Alternative to "name" for Scala 2.13 users. */
  lazy val nameStr: HtmlAttr[String] = htmlAttr("name", StringAsIsCodec)
  lazy val subtitleText: HtmlAttr[String] = htmlAttr("subtitle-text", StringAsIsCodec)
  lazy val titleText: HtmlAttr[String]    = htmlAttr("title-text", StringAsIsCodec)

  object slots {
    val subtitle: Slot = new Slot("subtitle")
    val title: Slot = new Slot("title")
  }

  object events {}

  

}
