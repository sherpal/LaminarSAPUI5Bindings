package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{EmbeddingAsIsCodec, TableColumnPopinDisplay}
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import com.raquo.laminar.codecs.IntAsStringCodec
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.TableColumnPopinDisplay

object TableColumn extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/TableColumn.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-table-column")

  lazy val demandPopin: HtmlAttr[Boolean] = htmlAttr("demand-popin", BooleanAsAttrPresenceCodec)
  lazy val minWidth: HtmlAttr[Int]        = htmlAttr("min-width", IntAsStringCodec)
  lazy val popinText: HtmlAttr[String]    = htmlAttr("popin-text", StringAsIsCodec)
  lazy val popinDisplay: HtmlAttr[TableColumnPopinDisplay] =
    htmlAttr("popin-display", TableColumnPopinDisplay.AsStringCodec)

}
