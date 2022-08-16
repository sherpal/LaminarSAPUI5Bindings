package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.ValueState
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, IntAsStringCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import com.raquo.domtypes.generic.codecs.IntAsStringCodec
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

  type Ref         = dom.html.Element with RawElement
  type ModFunction = ProgressIndicator.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-progress-indicator")

  lazy val disabled: ReactiveHtmlAttr[Boolean] =
    customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)

  lazy val displayValue: ReactiveHtmlAttr[String] =
    customHtmlAttr("display-value", StringAsIsCodec)

  lazy val hideValue: ReactiveHtmlAttr[Boolean] =
    customHtmlAttr("hide-value", BooleanAsAttrPresenceCodec)

  lazy val value: ReactiveHtmlAttr[Int] = customHtmlAttr("value", IntAsStringCodec)

  lazy val valueState: ReactiveHtmlAttr[ValueState] =
    customHtmlAttr("value-state", ValueState.AsStringCodec)

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(ProgressIndicator)): _*)

}
