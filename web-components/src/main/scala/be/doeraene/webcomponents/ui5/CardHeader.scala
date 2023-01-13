package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.{HtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** The ui5-card-header is a component, meant to be used as a header of the ui5-card component.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Card/">the doc</a> for more information.
  */
object CardHeader extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/CardHeader.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-card-header")

  lazy val interactive: HtmlAttr[Boolean] = customHtmlAttr("interactive", BooleanAsAttrPresenceCodec)

  lazy val status: HtmlAttr[String] = customHtmlAttr("status", StringAsIsCodec)

  lazy val subtitleText: HtmlAttr[String] = customHtmlAttr("subtitle-text", StringAsIsCodec)

  lazy val titleText: HtmlAttr[String] = customHtmlAttr("title-text", StringAsIsCodec)

  object slots {
    val action: Slot = new Slot("action")

    val avatar: Slot = new Slot("avatar")
  }

  object events {
    val onClick: EventProp[dom.MouseEvent] = new EventProp("click")
  }

  

}
