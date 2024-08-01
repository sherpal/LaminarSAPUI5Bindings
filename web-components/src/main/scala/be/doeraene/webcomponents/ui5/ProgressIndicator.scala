package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.ValueState
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, IntAsStringCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import com.raquo.laminar.codecs.IntAsStringCodec
import be.doeraene.webcomponents.ui5.configkeys.ValueState
import be.doeraene.webcomponents.WebComponent

/** Shows the progress of a process in a graphical way. To indicate the progress, the inside of the component is filled
  * with a color.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/ProgressIndicator/">the doc</a> for more
  *   information.
  */
object ProgressIndicator extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/ProgressIndicator.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-progress-indicator")

  lazy val accessibleName: HtmlAttr[String] = htmlAttr("accessible-name", StringAsIsCodec)
  lazy val displayValue: HtmlAttr[String]   = htmlAttr("display-value", StringAsIsCodec)
  lazy val hideValue: HtmlAttr[Boolean]     = htmlAttr("hide-value", BooleanAsAttrPresenceCodec)
  lazy val value: HtmlAttr[Int]             = htmlAttr("value", IntAsStringCodec)
  lazy val valueState: HtmlAttr[ValueState] = ValueState.asHtmlAttr("value-state")

  @scala.annotation.compileTimeOnly("disabled property of progress indicator has been disabled")
  def disabled: HtmlAttr[Boolean] = ???

}
