package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** The ui5-icon component represents an SVG icon. There are two main scenarios how the ui5-icon component is used: as a
  * purely decorative element; or as a visually appealing clickable area in the form of an icon button.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Icon/">the doc</a> for more information.
  */
object Icon extends WebComponent with HasAccessibleName {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/Icon.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = Icon.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-icon")

  val name: ReactiveHtmlAttr[IconName] = customHtmlAttr("name", IconName.AsStringCodec)

  val interactive: ReactiveHtmlAttr[Boolean] =
    customHtmlAttr("interactive", BooleanAsAttrPresenceCodec)

  val showTooltip: ReactiveHtmlAttr[Boolean] =
    customHtmlAttr("show-tooltip", BooleanAsAttrPresenceCodec)

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(Icon)): _*)

}
