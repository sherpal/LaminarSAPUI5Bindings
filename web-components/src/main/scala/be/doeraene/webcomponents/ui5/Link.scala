package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, EmbeddingAsIsCodec, IconName, LinkDesign, LinkTarget}
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.configkeys.WrappingType
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget

/** A link to another page.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Link/">the doc</a> for more information.
  */
object Link extends HasDesign with HasIcon with HasAccessibleName {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/Link.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = Link.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-link")

  val id: ReactiveProp[String, String] = idAttr

  val disabled: ReactiveHtmlAttr[Boolean] =
    customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)
  val href: ReactiveHtmlAttr[String]       = customHtmlAttr("href", StringAsIsCodec)
  val target: ReactiveHtmlAttr[LinkTarget] = customHtmlAttr("target", LinkTarget.AsStringCodec)

  val design: ReactiveHtmlAttr[LinkDesign] =
    customHtmlAttr("design", LinkDesign.AsStringCodec)

  val wrappingType: ReactiveHtmlAttr[WrappingType] = customHtmlAttr("wrapping-type", WrappingType.AsStringCodec)

  object slots {}

  object events {
    val onClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("click")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(Link)): _*)

}
