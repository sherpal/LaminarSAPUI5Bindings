package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, IconName}
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.{HtmlAttr}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** This is the item to use inside a ui5-tree. You can represent an arbitrary tree structure by recursively nesting tree
  * items.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Tree/">the doc</a> for more information.
  */
object TreeItem extends WebComponent with HasIcon with HasText {

  @js.native
  trait RawElement extends js.Object {
    def toggle(): Unit = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/TreeItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = htmlTag("ui5-tree-item")

  lazy val expanded: HtmlAttr[Boolean]     = htmlAttr[Boolean]("expanded", BooleanAsAttrPresenceCodec)
  lazy val hasChildren: HtmlAttr[Boolean]  = htmlAttr[Boolean]("has-children", BooleanAsAttrPresenceCodec)
  lazy val intermediate: HtmlAttr[Boolean] = htmlAttr[Boolean]("intermediate", BooleanAsAttrPresenceCodec)
  lazy val selected: HtmlAttr[Boolean]     = htmlAttr[Boolean]("selected", BooleanAsAttrPresenceCodec)

  def item: TreeItem.type = this

}
