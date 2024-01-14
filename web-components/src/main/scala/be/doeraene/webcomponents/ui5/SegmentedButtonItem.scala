package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
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

/** Users can use the ui5-segmented-button-item as part of a ui5-segmented-button.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/SegmentedButton/">the doc</a> for more
  *   information.
  */
object SegmentedButtonItem extends WebComponent with HasAccessibleName with HasIcon {

  @js.native
  trait RawElement extends js.Object {
    def accessibleName: js.UndefOr[String] = js.native
  }

  object RawElement {
    extension (elem: RawElement) {
      def maybeAccessibleName: Option[String] = elem.accessibleName.toOption
    }
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/SegmentedButtonItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-segmented-button-item")

  lazy val design: HtmlAttr[ButtonDesign] = htmlAttr("design", ButtonDesign.AsStringCodec)

  lazy val iconEnd: HtmlAttr[Boolean] = htmlAttr("icon-end", BooleanAsAttrPresenceCodec)

  lazy val submits: HtmlAttr[Boolean] = htmlAttr("submits", BooleanAsAttrPresenceCodec)

  lazy val pressed: HtmlAttr[Boolean] = htmlAttr("pressed", BooleanAsAttrPresenceCodec)

  lazy val accessibilityAttributes: HtmlAttr[js.Object] = ??? // todo

  lazy val disabled: HtmlAttr[Boolean] = htmlAttr("disabled", BooleanAsAttrPresenceCodec)

  lazy val tooltip: HtmlAttr[String] = htmlAttr("tooltip", StringAsIsCodec)

  object slots {}

  object events {
    val onClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("click")
  }

}
