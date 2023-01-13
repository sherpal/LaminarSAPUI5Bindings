package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, IconName, SwitchDesign}
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.{HtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom
import org.scalajs.dom.Event

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.WebComponent

/** The ui5-switch component is used for changing between binary states.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Switch/">the doc</a> for more information.
  */
object Switch extends WebComponent with HasAccessibleName {

  @js.native
  trait RawElement extends js.Object {
    def checked: Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/Switch.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-switch")

  lazy val textOn: HtmlAttr[String]  = customHtmlAttr("text-on", StringAsIsCodec)
  lazy val textOff: HtmlAttr[String] = customHtmlAttr("text-off", StringAsIsCodec)

  lazy val disabled: HtmlAttr[Boolean] = customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val checked: HtmlAttr[Boolean]  = customHtmlAttr("checked", BooleanAsAttrPresenceCodec)

  lazy val design: HtmlAttr[SwitchDesign] = customHtmlAttr("design", SwitchDesign.AsStringCodec)
  
  lazy val tooltip: HtmlAttr[String] = customHtmlAttr("tooltip", StringAsIsCodec)

  object slots {}

  object events {
    val onChange: EventProp[EventWithPreciseTarget[Ref]]                      = new EventProp("change")
    val onCheckedChange: EventProcessor[EventWithPreciseTarget[Ref], Boolean] = onChange.map(_.target.checked)
  }

  

}
