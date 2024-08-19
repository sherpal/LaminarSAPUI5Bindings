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

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-segmented-button-item")

  lazy val selected: HtmlAttr[Boolean] = htmlAttr("selected", BooleanAsAttrPresenceCodec)

  @scala.annotation.compileTimeOnly("design property has been removed")
  def design: HtmlAttr[ButtonDesign] = ???

  @scala.annotation.compileTimeOnly("iconEnd property has been removed")
  def iconEnd: HtmlAttr[Boolean] = ???

  @scala.annotation.compileTimeOnly("submits property has been removed")
  def submits: HtmlAttr[Boolean] = ???

  @deprecated("pressed property of SegmentedButton items has been renamed to selected", since = "2.0.0")
  def pressed: HtmlAttr[Boolean] = selected

  @scala.annotation.compileTimeOnly("accessibilityAttributes property has been removed")
  def accessibilityAttributes: HtmlAttr[js.Object] = ???

  lazy val disabled: HtmlAttr[Boolean] = htmlAttr("disabled", BooleanAsAttrPresenceCodec)

  lazy val tooltip: HtmlAttr[String] = htmlAttr("tooltip", StringAsIsCodec)

  object slots {}

  object events {
    val onClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("click")
  }

}
