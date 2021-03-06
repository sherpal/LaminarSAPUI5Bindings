package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, IconName, SwitchDesign}
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom
import org.scalajs.dom.Event

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-switch component is used for changing between binary states.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Switch/">the doc</a> for more information.
  */
object Switch extends HasAccessibleName {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/Switch.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = Switch.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-switch")

  val id: ReactiveProp[String, String] = idAttr

  val textOn: ReactiveHtmlAttr[String]  = customHtmlAttr("text-on", StringAsIsCodec)
  val textOff: ReactiveHtmlAttr[String] = customHtmlAttr("text-off", StringAsIsCodec)

  val disabled: ReactiveHtmlAttr[Boolean] = customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)
  val checked: ReactiveHtmlAttr[Boolean]  = customHtmlAttr("checked", BooleanAsAttrPresenceCodec)

  val design: ReactiveHtmlAttr[SwitchDesign] = customHtmlAttr("design", SwitchDesign.AsStringCodec)

  object slots {}

  object events extends HasOnChange {
    val onCheckedChange: EventProcessor[Event, Boolean] = onChange.mapToChecked
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(Switch)): _*)

}
