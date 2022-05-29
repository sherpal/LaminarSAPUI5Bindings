package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName, LinkTarget}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-product-switch-item web component represents the items displayed in the ui5-product-switch web component.
  *
  * Note: ui5-product-switch-item is not supported when used outside of ui5-product-switch.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/ProductSwitch/">the doc</a> for more
  *   information.
  */
object ProductSwitchItem extends HasIcon {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/ProductSwitchItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = ProductSwitchItem.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-product-switch-item")

  val id: ReactiveProp[String, String] = idAttr

  val subtitleText: ReactiveHtmlAttr[String] = customHtmlAttr[String]("subtitle-text", StringAsIsCodec)

  val target: ReactiveHtmlAttr[LinkTarget] = customHtmlAttr[LinkTarget]("target", LinkTarget.AsStringCodec)

  val targetSrc: ReactiveHtmlAttr[String] = customHtmlAttr[String]("target-src", StringAsIsCodec)

  val titleText: ReactiveHtmlAttr[String] = customHtmlAttr[String]("title-text", StringAsIsCodec)

  object slots {}

  object events extends HasOnClick {}

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(ProductSwitchItem)): _*)

}
