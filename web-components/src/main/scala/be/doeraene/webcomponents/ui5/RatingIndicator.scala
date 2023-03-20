package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.{HtmlAttr}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import com.raquo.laminar.codecs.IntAsStringCodec
import com.raquo.laminar.codecs.DoubleAsStringCodec
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.WebComponent

/** The Rating Indicator is used to display a specific number of icons that are used to rate an item. Additionally, it
  * is also used to display the average and overall ratings.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/RatingIndicator/">the doc</a> for more
  *   information.
  */
object RatingIndicator extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def value: Double = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/RatingIndicator.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = htmlTag("ui5-rating-indicator")

  lazy val accessibleName: HtmlAttr[String] = htmlAttr("accessible-name", StringAsIsCodec)
  lazy val disabled: HtmlAttr[Boolean]      = htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val max: HtmlAttr[Int]               = htmlAttr("max", IntAsStringCodec)
  lazy val readonly: HtmlAttr[Boolean]      = htmlAttr("readonly", BooleanAsAttrPresenceCodec)
  lazy val value: HtmlAttr[Double]          = htmlAttr("value", DoubleAsStringCodec)

  object slots {}

  object events {
    val onChange = new EventProp[EventWithPreciseTarget[Ref]]("change")
  }

  

}
