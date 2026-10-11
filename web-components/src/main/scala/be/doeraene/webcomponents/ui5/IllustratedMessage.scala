package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.IllustratedMessageSize
import be.doeraene.webcomponents.ui5.configkeys.IllustratedMessageType
import be.doeraene.webcomponents.ui5.configkeys.TitleLevel
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.configkeys.WrappingType

/** An IllustratedMessage is a recommended combination of a solution-oriented message, an engaging illustration, and
  * conversational tone to better communicate an empty or a success state than just show a message alone. The children
  * (typically [[Button]]s) are displayed as actions below the message.
  *
  * /!\ Used [[IllustratedMessageType]] must be registered before they can be used. Please refer to the official
  * documentation for more information. The [[IllustratedMessageType]] has a facility method to know how to register
  * it.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/IllustratedMessage/">the doc</a> for more
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

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-illustrated-message")

  lazy val accessibleNameRef: HtmlAttr[String] = htmlAttr("accessible-name-ref", StringAsIsCodec)
  lazy val name: HtmlAttr[IllustratedMessageType] =
    htmlAttr("name", IllustratedMessageType.AsStringCodec)

  /** Alternative to "name" for Scala 2.13 users. */
  lazy val nameStr: HtmlAttr[String] = htmlAttr("name", StringAsIsCodec)

  /** Size of the illustration (`Auto` by default, adapting to the available width). */
  lazy val design: HtmlAttr[IllustratedMessageSize] = IllustratedMessageSize.asHtmlAttr("design")

  lazy val subtitleText: HtmlAttr[String] = htmlAttr("subtitle-text", StringAsIsCodec)
  lazy val titleText: HtmlAttr[String]    = htmlAttr("title-text", StringAsIsCodec)

  /** Whether the illustration is decorative (hidden from assistive technologies). */
  lazy val decorative: HtmlAttr[Boolean] = htmlAttr("decorative", BooleanAsAttrPresenceCodec)

  @deprecated("text is not part of the ui5-illustrated-message API and has no effect", since = "2.0.0")
  lazy val text: HtmlAttr[String] = htmlAttr("text", StringAsIsCodec)

  @deprecated("wrappingType is not part of the ui5-illustrated-message API and has no effect", since = "2.0.0")
  lazy val wrappingType: HtmlAttr[WrappingType] = WrappingType.asHtmlAttr("wrapping-type")

  @scala.annotation.compileTimeOnly("The titleLevel property has been removed and replaced by using the title slot")
  def titleLevel: HtmlAttr[TitleLevel] = ???

  @deprecated("size property of IllustratedMessage has been renamed to design")
  def size: HtmlAttr[IllustratedMessageSize] = design

  object slots {

    /** Custom subtitle (overrides `subtitleText`), e.g. to include a [[Link]]. */
    val subtitle: Slot = new Slot("subtitle")

    /** Custom title (overrides `titleText`), typically a [[Title]]. */
    val title: Slot = new Slot("title")
  }

  object events {}

}
