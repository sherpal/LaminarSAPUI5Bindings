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
import be.doeraene.webcomponents.ui5.configkeys.IconDesign

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

  type Ref = dom.html.Element with RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-icon")

  lazy val name: HtmlAttr[IconName] = htmlAttr("name", IconName.AsStringCodec)

  lazy val interactive: HtmlAttr[Boolean] =
    htmlAttr("interactive", BooleanAsAttrPresenceCodec)

  lazy val showTooltip: HtmlAttr[Boolean] =
    htmlAttr("show-tooltip", BooleanAsAttrPresenceCodec)

  lazy val design: HtmlAttr[IconDesign] = htmlAttr("design", IconDesign.AsStringCodec)

}
