package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** Users can use the ui5-segmented-button-item as part of a ui5-segmented-button.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/SegmentedButton/">the doc</a> for more
  *   information.
  */
object SegmentedButtonItem extends HasAccessibleName with HasIcon {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/SegmentedButtonItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = SegmentedButtonItem.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-segmented-button-item")

  val id: ReactiveProp[String, String] = idAttr

  val design: ReactiveHtmlAttr[ButtonDesign] = customHtmlAttr("design", ButtonDesign.AsStringCodec)

  val iconEnd: ReactiveHtmlAttr[Boolean] = customHtmlAttr("icon-end", BooleanAsAttrPresenceCodec)

  val submits: ReactiveHtmlAttr[Boolean] = customHtmlAttr("submits", BooleanAsAttrPresenceCodec)

  val pressed: ReactiveHtmlAttr[Boolean] = customHtmlAttr("pressed", BooleanAsAttrPresenceCodec)

  lazy val accessibilityAttributes: ReactiveHtmlAttr[js.Object] = ??? // todo

  val disabled: ReactiveHtmlAttr[Boolean] = customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)

  val tooltip: ReactiveHtmlAttr[String] = customHtmlAttr("tooltip", StringAsIsCodec)

  object slots {}

  object events {
    val onClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("click")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(SegmentedButtonItem)): _*)

}
