package be.doeraene.webcomponents.ui5

import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.keys.HtmlAttr

object TableRow extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents-compat/dist/TableRow.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-table-row")

  lazy val navigated: HtmlAttr[Boolean] = htmlAttr("navigated", BooleanAsAttrPresenceCodec)

  //noinspection TypeAnnotation
  val cell = TableCell
}
