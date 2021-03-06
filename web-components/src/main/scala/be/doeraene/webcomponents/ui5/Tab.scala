package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.SemanticColour
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** Element contained in a [[TabContainer]].
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/TabContainer/">the doc</a> for more
  *   information.
  */
object Tab extends HasIcon with HasText {

  @js.native
  trait RawElement extends js.Object {
    def getTabInStripDomRef(): dom.Element = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/Tab.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = Tab.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-tab")

  val id: ReactiveProp[String, String] = idAttr

  val disabled: ReactiveHtmlAttr[Boolean] = customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)
  val selected: ReactiveHtmlAttr[Boolean] = customHtmlAttr("selected", BooleanAsAttrPresenceCodec)

  val design: ReactiveHtmlAttr[SemanticColour] = customHtmlAttr("design", SemanticColour.AsStringCodec)

  val additionalText: ReactiveHtmlAttr[String] = customHtmlAttr("additional-text", StringAsIsCodec)

  object slots {
    val subTabs: Slot = new Slot("subTabs")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(Tab)): _*)

}
