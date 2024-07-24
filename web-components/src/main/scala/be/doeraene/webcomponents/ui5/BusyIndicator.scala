package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{
  BusyIndicatorSize,
  BusyIndicatorTextPlacement,
  ButtonDesign,
  ColourScheme,
  IconName
}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.concurrent.duration.FiniteDuration
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** The ui5-busy-indicator signals that some operation is going on and that the user must wait. It does not block the
  * current UI screen so other operations could be triggered in parallel. It displays 3 dots and each dot expands and
  * shrinks at a different rate, resulting in a cascading flow of animation.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/BusyIndicator/">the doc</a> for more
  *   information.
  */
object BusyIndicator extends WebComponent with HasText {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/BusyIndicator.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-busy-indicator")

  lazy val active: HtmlAttr[Boolean] = htmlAttr[Boolean]("active", BooleanAsAttrPresenceCodec)

  lazy val delay: HtmlAttr[FiniteDuration] = htmlAttr[FiniteDuration]("delay", FiniteDurationCodec)

  lazy val textPlacement: HtmlAttr[BusyIndicatorTextPlacement] = BusyIndicatorTextPlacement.asHtmlAttr("text-placement")

  lazy val size: HtmlAttr[BusyIndicatorSize] =
    htmlAttr[BusyIndicatorSize]("size", BusyIndicatorSize.AsStringCodec)

}
