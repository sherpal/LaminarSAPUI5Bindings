package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, IconName}
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.configkeys.ValueState

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

    def hasChildren: Boolean = js.native

    def selected: Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/TreeItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-tree-item")

  lazy val expanded: HtmlAttr[Boolean]     = htmlAttr("expanded", BooleanAsAttrPresenceCodec)
  lazy val hasChildren: HtmlAttr[Boolean]  = htmlAttr("has-children", BooleanAsAttrPresenceCodec)
  lazy val intermediate: HtmlAttr[Boolean] = htmlAttr("intermediate", BooleanAsAttrPresenceCodec)
  lazy val selected: HtmlAttr[Boolean]     = htmlAttr("selected", BooleanAsAttrPresenceCodec)
  lazy val highlight: HtmlAttr[ValueState] = ValueState.asHtmlAttr("highlight")
  lazy val tooltip: HtmlAttr[String]       = htmlAttr("tooltip", StringAsIsCodec)
  lazy val navigated: HtmlAttr[Boolean]    = htmlAttr("navigated", BooleanAsAttrPresenceCodec)
  lazy val movable: HtmlAttr[Boolean]      = htmlAttr("movable", BooleanAsAttrPresenceCodec)

  object slots {}

  object events {
    val detailClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("detail-click")
  }

  def item: TreeItem.type             = this
  def customItem: TreeItemCustom.type = TreeItemCustom

}
