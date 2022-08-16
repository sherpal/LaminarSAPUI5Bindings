package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, IconName}
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
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

  type Ref         = dom.html.Element with RawElement
  type ModFunction = TreeItem.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-tree-item")

  lazy val expanded: ReactiveHtmlAttr[Boolean]     = customHtmlAttr[Boolean]("expanded", BooleanAsAttrPresenceCodec)
  lazy val hasChildren: ReactiveHtmlAttr[Boolean]  = customHtmlAttr[Boolean]("has-children", BooleanAsAttrPresenceCodec)
  lazy val intermediate: ReactiveHtmlAttr[Boolean] = customHtmlAttr[Boolean]("intermediate", BooleanAsAttrPresenceCodec)
  lazy val selected: ReactiveHtmlAttr[Boolean]     = customHtmlAttr[Boolean]("selected", BooleanAsAttrPresenceCodec)

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(TreeItem)): _*)

  def item: TreeItem.type = this

}
