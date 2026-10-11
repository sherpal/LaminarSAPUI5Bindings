package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-group-item defines a grouping criterion (e.g. a column or field) for the data in a [[ViewSettingsDialog]]
  * (to be put in its `groupItems` slot).
  *
  * @since UI5 2.15.0
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/GroupItem/">the doc</a> for more information.
  */
object GroupItem extends WebComponent with HasText {

  @js.native
  trait RawElement extends js.Object {
    def text: js.UndefOr[String] = js.native
    def selected: Boolean        = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/GroupItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-group-item")

  lazy val selected: HtmlAttr[Boolean] = htmlAttr("selected", BooleanAsAttrPresenceCodec)

  object slots {}

  object events {}

}
