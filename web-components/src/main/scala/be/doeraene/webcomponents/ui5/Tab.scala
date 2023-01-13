package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.SemanticColour
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.{HtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** Element contained in a [[TabContainer]].
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/TabContainer/">the doc</a> for more
  *   information.
  */
object Tab extends WebComponent with HasIcon with HasText {

  @js.native
  trait RawElement extends js.Object {
    def getTabInStripDomRef(): dom.Element = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/Tab.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-tab")

  lazy val disabled: HtmlAttr[Boolean] = customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val selected: HtmlAttr[Boolean] = customHtmlAttr("selected", BooleanAsAttrPresenceCodec)

  lazy val design: HtmlAttr[SemanticColour] = customHtmlAttr("design", SemanticColour.AsStringCodec)

  lazy val additionalText: HtmlAttr[String] = customHtmlAttr("additional-text", StringAsIsCodec)

  object slots {
    val subTabs: Slot = new Slot("subTabs")
  }

  

}
