package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.{IntAsStringCodec, StringAsIsCodec}
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.configkeys.TitleLevel
import be.doeraene.webcomponents.ui5.configkeys.ItemSpacing
import be.doeraene.webcomponents.ui5.internal.Slot

/** The FormGroup (ui5-form-item) represents pair of a label and one or more components (text or text fields),
  * associated to it.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/components/FormGroup/">the doc</a> for more inFormGroupation.
  */
object FormGroup extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/FormGroup.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-form-group")

  lazy val headerText: HtmlAttr[String]      = htmlAttr("header-text", StringAsIsCodec)
  lazy val headerLevel: HtmlAttr[TitleLevel] = htmlAttr("header-level", TitleLevel.AsStringCodec)
  lazy val columnSpan: HtmlAttr[Int]         = htmlAttr("column-span", IntAsStringCodec)

  object slots {}

  object events {}

}
