package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.configkeys.TitleLevel
import be.doeraene.webcomponents.ui5.configkeys.ItemSpacing
import be.doeraene.webcomponents.ui5.internal.Slot

/** The Form is a layout component that arranges labels and form fields (like input fields) pairs into a specific number
  * of columns.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/components/Form/">the doc</a> for more information.
  */
object Form extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/Form.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-form")

  lazy val accessibleName: HtmlAttr[String]   = htmlAttr("accessible-name", StringAsIsCodec)
  lazy val layout: HtmlAttr[String]           = htmlAttr("layout", StringAsIsCodec)
  lazy val labelSpan: HtmlAttr[String]        = htmlAttr("label-span", StringAsIsCodec)
  lazy val emptySpan: HtmlAttr[String]        = htmlAttr("label-span", StringAsIsCodec)
  lazy val headerText: HtmlAttr[String]       = htmlAttr("header-text", StringAsIsCodec)
  lazy val headerLevel: HtmlAttr[TitleLevel]  = htmlAttr("header-level", TitleLevel.AsStringCodec)
  lazy val itemSpacing: HtmlAttr[ItemSpacing] = htmlAttr("item-spacing", ItemSpacing.AsStringCodec)

  object slots {
    val header: Slot = Slot("header")
  }

  object events {}

  def item: FormItem.type   = FormItem
  def group: FormGroup.type = FormGroup

}
