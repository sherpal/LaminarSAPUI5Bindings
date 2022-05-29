package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.ListMode
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-tree component provides a tree structure for displaying data in a hierarchy.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Tree/">the doc</a> for more information.
  */
object Tree {

  //noinspection ScalaUnusedSymbol
  @js.native
  trait RawElement extends js.Object {
    def walk(callback: js.Function2[dom.HTMLElement, Int, Unit]): Unit = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/Tree.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = Tree.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-tree")

  val id: ReactiveProp[String, String] = idAttr

  val footerText: ReactiveHtmlAttr[String] = customHtmlAttr("footer-text", StringAsIsCodec)
  val headerText: ReactiveHtmlAttr[String] = customHtmlAttr("header-text", StringAsIsCodec)
  val noDataText: ReactiveHtmlAttr[String] = customHtmlAttr("no-data-text", StringAsIsCodec)

  val mode: ReactiveHtmlAttr[ListMode] = customHtmlAttr("mode", ListMode.AsStringCodec)

  // todo: events

  object slots {
    val header: Slot = new Slot("header")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(Tree)): _*)

  def item: TreeItem.type = TreeItem

}
