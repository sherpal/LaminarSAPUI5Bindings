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
import be.doeraene.webcomponents.ui5.configkeys.ValueState

/** TextArea
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/TextArea/">the doc</a> for more information.
  */
object TextArea extends HasValue with HasAccessibleName {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/TextArea.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = TextArea.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-textarea")

  val id: ReactiveProp[String, String] = idAttr

  val required: ReactiveHtmlAttr[Boolean]  = customHtmlAttr("required", BooleanAsAttrPresenceCodec)
  val disabled: ReactiveHtmlAttr[Boolean]  = customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)
  val readonly: ReactiveHtmlAttr[Boolean]  = customHtmlAttr("readonly", BooleanAsAttrPresenceCodec)
  val growing: ReactiveHtmlAttr[Boolean]  = customHtmlAttr("growing", BooleanAsAttrPresenceCodec)
  val placeholder: ReactiveHtmlAttr[String] = customHtmlAttr("headerText", StringAsIsCodec)
  val valueState: ReactiveHtmlAttr[ValueState] = customHtmlAttr("value-state", ValueState.AsStringCodec)
  
  val isRequired: Setter[HtmlElement] = required := true
  
  object events {
    val onChange: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("change")
    val onInput: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("input")    
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(TextArea)): _*)

}
