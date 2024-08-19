package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.SemanticColour
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

/** The ui5-tab represents a selectable item inside a ui5-tabcontainer. It defines both the item in the tab strip (top
  * part of the ui5-tabcontainer) and the content that is presented to the user once the tab is selected.
  */
object Tab extends WebComponent with HasIcon with HasText {

  @js.native
  trait RawElement extends js.Object {
    def getDomRefInStrip(): dom.Element = js.native
  }

  object RawElement {
    extension (rawElement: RawElement) {
      @deprecated("getTabInStripDomRef method of Tab element has been renamed to getDomRefInStrip", since = "2.0.0")
      def getTabInStripDomRef(): dom.Element = rawElement.getDomRefInStrip()
    }
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/Tab.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-tab")

  lazy val disabled: HtmlAttr[Boolean]      = htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val selected: HtmlAttr[Boolean]      = htmlAttr("selected", BooleanAsAttrPresenceCodec)
  lazy val design: HtmlAttr[SemanticColour] = htmlAttr("design", SemanticColour.AsStringCodec)
  lazy val additionalText: HtmlAttr[String] = htmlAttr("additional-text", StringAsIsCodec)
  lazy val movable: HtmlAttr[Boolean]       = htmlAttr("movable", BooleanAsAttrPresenceCodec)

  object slots {
    @deprecated("subTabs Tab slot has been renamed to items")
    def subTabs: Slot = items

    val items: Slot = Slot("items")
  }

}
