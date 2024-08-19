package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.PageBackgroundDesign
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

/** The ui5-page is a container component that holds one whole screen of an application. The page has three distinct
  * areas that can hold content - a header, content area and a footer.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Page/">the doc</a> for more information.
  */
object Page extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/Page.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-page")

  lazy val backgroundDesign: HtmlAttr[PageBackgroundDesign] =
    htmlAttr("background-design", PageBackgroundDesign.AsStringCodec)

  lazy val noScrolling: HtmlAttr[Boolean] = htmlAttr("no-scrolling", BooleanAsAttrPresenceCodec)

  lazy val fixedFooter: HtmlAttr[Boolean] = htmlAttr("fixed-footer", BooleanAsAttrPresenceCodec)

  lazy val hideFooter: HtmlAttr[Boolean] =
    htmlAttr("hide-footer", BooleanAsAttrPresenceCodec)

  @deprecated("disableScrolling has been renamed to noScrolling", since = "2.0.0")
  def disableScrolling: HtmlAttr[Boolean] = noScrolling

  @scala.annotation.compileTimeOnly(
    "floatingFooter has been removed and is now the default. If you don't want your page footer to float, use the new fixedFooter property."
  )
  def floatingFooter: HtmlAttr[Boolean] = ???

  object slots {
    val footer: Slot = new Slot("footer")
    val header: Slot = new Slot("header")
  }

  object events {}

}
