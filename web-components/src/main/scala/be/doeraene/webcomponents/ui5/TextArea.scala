package be.doeraene.webcomponents.ui5

import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.configkeys.ValueState
import com.raquo.laminar.codecs.IntAsStringCodec
import be.doeraene.webcomponents.ui5.internal.Slot
import be.doeraene.webcomponents.WebComponent

/** The ui5-textarea component is used to enter multiple lines of text.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/TextArea/">the doc</a> for more
  *   information.
  */
object TextArea extends WebComponent with HasValue with HasAccessibleName with HasName {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/TextArea.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-textarea")

  lazy val required: HtmlAttr[Boolean] = htmlAttr("required", BooleanAsAttrPresenceCodec)
  lazy val disabled: HtmlAttr[Boolean] = htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val readonly: HtmlAttr[Boolean] = htmlAttr("readonly", BooleanAsAttrPresenceCodec)
  lazy val growing: HtmlAttr[Boolean]  = htmlAttr("growing", BooleanAsAttrPresenceCodec)
  lazy val showExceededText: HtmlAttr[Boolean] =
    htmlAttr("show-exceeded-text", BooleanAsAttrPresenceCodec)
  lazy val growingMaxLines: HtmlAttr[Int]   = htmlAttr("growing-max-lines", IntAsStringCodec)
  lazy val maxLength: HtmlAttr[Int]         = htmlAttr("maxlength", IntAsStringCodec)
  lazy val rows: HtmlAttr[Int]              = htmlAttr("rows", IntAsStringCodec)
  lazy val placeholder: HtmlAttr[String]    = htmlAttr("placeholder", StringAsIsCodec)
  lazy val valueState: HtmlAttr[ValueState] = htmlAttr("value-state", ValueState.AsStringCodec)

  val isRequired: Setter[HtmlElement] = required := true

  object slots {
    val valueStateMessage = new Slot("valueStateMessage")
  }
  object events {
    val onChange: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("change")
    val onInput: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("input")
  }

}
