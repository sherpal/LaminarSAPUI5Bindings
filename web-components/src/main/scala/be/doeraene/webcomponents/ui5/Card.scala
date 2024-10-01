package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent
import scala.concurrent.duration.FiniteDuration

/** The ui5-card is a component that represents information in the form of a tile with separate header and content
  * areas.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/components/Card/">the doc</a> for more information.
  */
object Card extends WebComponent with HasAccessibleName {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/Card.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-card")

  lazy val loading: HtmlAttr[Boolean]             = htmlAttr("loading", BooleanAsAttrPresenceCodec)
  lazy val loadingDelay: HtmlAttr[FiniteDuration] = htmlAttr("loading-delay", FiniteDurationCodec)

  object slots {

    /** Note: Use ui5-card-header for the intended design. */
    val header: Slot = new Slot("header")
  }

  def header: CardHeader.type = CardHeader

}
