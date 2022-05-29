package be.doeraene.webcomponents.ui5

import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** Simple UI button
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Label/">the doc</a> for more information.
  */
object Label extends HasIcon with HasOnClick {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/Label.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = Label.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-label")

  val id: ReactiveProp[String, String] = idAttr

  val required: ReactiveHtmlAttr[Boolean]  = customHtmlAttr("required", BooleanAsAttrPresenceCodec)
  val showColon: ReactiveHtmlAttr[Boolean] = customHtmlAttr("show-colon", BooleanAsAttrPresenceCodec)
  val forId: ReactiveHtmlAttr[String]      = customHtmlAttr("for", StringAsIsCodec)

  val isRequired: Setter[HtmlElement] = required := true

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(Label)): _*)

}
