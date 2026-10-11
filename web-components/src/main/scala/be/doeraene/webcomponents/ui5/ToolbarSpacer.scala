package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.scaladsl.csssize.CSSSize
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-toolbar-spacer is an element used for taking needed space for toolbar items to take 100% width. It takes no
  * space in calculating toolbar items width.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/ToolbarSpacer/">the doc</a> for more information.
  */
object ToolbarSpacer extends WebComponent with HasToolbarOverflow {

  @js.native
  trait RawElement extends js.Object

  @js.native
  @JSImport("@ui5/webcomponents/dist/ToolbarSpacer.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-toolbar-spacer")

  lazy val widthJS: HtmlAttr[String] = htmlAttr("width", StringAsIsCodec)
  lazy val width: HtmlAttr[CSSSize]  = htmlAttr("width", CSSSize.AsStringCodec)

  object slots {}

  object events {}

}
