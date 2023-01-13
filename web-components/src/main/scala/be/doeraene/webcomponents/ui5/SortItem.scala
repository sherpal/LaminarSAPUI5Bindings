package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.{HtmlAttr}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** For the ui5-sort-item
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/ViewSettingsDialog/">the doc</a> for more
  *   information.
  */
object SortItem extends WebComponent with HasText {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/SortItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = htmlTag("ui5-sort-item")

  lazy val selected: HtmlAttr[Boolean] = htmlAttr("selected", BooleanAsAttrPresenceCodec)

  object slots {}

  object events {}

  

}
