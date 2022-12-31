package be.doeraene.webcomponents.ui5

import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.eventtypes.HasTargetRef
import be.doeraene.webcomponents.WebComponent

/** The ui5-shellbar-item represents a custom item, that might be added to the ui5-shellbar.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/ShellBar/">the doc</a> for more
  *   information.
  */
object ShellBarItem extends WebComponent with HasIcon with HasText {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/ShellBarItem", JSImport.Default)
  object RawImport extends WebComponent.WithMetadata

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-shellbar-item")

  lazy val count: HtmlAttr[String] = customHtmlAttr("count", StringAsIsCodec)

  object slots {}

  object events {
    val onClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasTargetRef[dom.HTMLElement]]] =
      new EventProp("click")
  }

  

}
