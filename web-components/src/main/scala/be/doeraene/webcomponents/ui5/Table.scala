package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object Table {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/Table.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = Table.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-table")

  val id: ReactiveProp[String, String]         = idAttr
  val growing: ReactiveHtmlAttr[String]        = customHtmlAttr("growing", StringAsIsCodec)
  val growingSubText: ReactiveHtmlAttr[String] = customHtmlAttr("growing-button-subtext", StringAsIsCodec)

  val noDataText: ReactiveHtmlAttr[String] = customHtmlAttr("no-data-text", StringAsIsCodec)

  val onLoadMore = new EventProp[dom.Event]("load-more")

  object slots {
    val columns: Slot = new Slot("columns")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(Table)): _*)

}
