package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.ValueState
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, IntAsStringCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.{HtmlAttr, ReactiveProp, ReactiveStyle}
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

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-progress-indicator")

  lazy val disabled: HtmlAttr[Boolean] =
    customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)

  lazy val displayValue: HtmlAttr[String] =
    customHtmlAttr("display-value", StringAsIsCodec)

  lazy val hideValue: HtmlAttr[Boolean] =
    customHtmlAttr("hide-value", BooleanAsAttrPresenceCodec)

  lazy val value: HtmlAttr[Int] = customHtmlAttr("value", IntAsStringCodec)

  lazy val valueState: HtmlAttr[ValueState] =
    customHtmlAttr("value-state", ValueState.AsStringCodec)

  

}
