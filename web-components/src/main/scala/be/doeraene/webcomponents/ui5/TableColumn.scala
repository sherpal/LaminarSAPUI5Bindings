package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.EmbeddingAsIsCodec
import com.raquo.domtypes.generic.codecs.StringAsIsCodec
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.ReactiveHtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import com.raquo.domtypes.generic.codecs.IntAsStringCodec
import com.raquo.domtypes.generic.codecs.BooleanAsAttrPresenceCodec

object TableColumn {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/TableColumn.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = TableColumn.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-table-column")

  val demandPopin: ReactiveHtmlAttr[Boolean] = customHtmlAttr("demand-popin", BooleanAsAttrPresenceCodec)
  val minWidth: ReactiveHtmlAttr[Int]        = customHtmlAttr("min-width", IntAsStringCodec)
  val popinText: ReactiveHtmlAttr[String]    = customHtmlAttr("popin-text", StringAsIsCodec)

  val slot: ReactiveHtmlAttr["columns" | "default"] =
    customHtmlAttr("slot", EmbeddingAsIsCodec.apply)

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(TableColumn)): _*)

}
